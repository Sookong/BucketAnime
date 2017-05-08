package com.tanwuyu.ivrtym.bucketanime.view.activity;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import com.tanwuyu.ivrty.flowlayout.FlowLayout;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.adapter.OnItemClickListener;
import com.tanwuyu.ivrtym.bucketanime.adapter.SearchHotSearchRcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.adapter.SearchInputConnectRcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.adapter.SearchRecordRcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.adapter.SearchResultRcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.HotSearch;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.SearchPresenter;
import com.tanwuyu.ivrtym.bucketanime.utils.DensityUtil;
import com.tanwuyu.ivrtym.bucketanime.view.ISearchView;
import com.tanwuyu.ivrtym.bucketanime.widget.AutoLoadMoreRecyclerView;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by ivrty on 2017/3/19.
 */

public class SearchActivity extends BaseMvpActivity<ISearchView, SearchPresenter> implements ISearchView {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.ib_hot_search_refresh)
    ImageView ibHotSearchRefresh;
    @BindView(R.id.flowlayout_hot_search)
    FlowLayout flowlayoutHotSearch;
    @BindView(R.id.ib_search_record_delete)
    ImageView ibSearchRecordDelete;
    @BindView(R.id.rcv_search_record)
    RecyclerView rcvSearchRecord;
    @BindView(R.id.part_search_prepare)
    LinearLayout partSearchPrepare;
    @BindView(R.id.rcv_search_result)
    AutoLoadMoreRecyclerView rcvSearchResult;
    @BindView(R.id.part_search_sucess)
    LinearLayout partSearchSucess;
    @BindView(R.id.tv_error_msg)
    TextView tvErrorMsg;
    @BindView(R.id.rcv_hot_search)
    RecyclerView rcvHotSearch;
    @BindView(R.id.part_search_error)
    LinearLayout partSearchError;
    @BindView(R.id.iv_key_back)
    ImageView ivKeyBack;
    @BindView(R.id.iv_input_clear)
    ImageView ivInputClear;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    List<Anime> searchResults;
    List<Anime> searchInputConnects;
    List<HotSearch> hotSearches;
    List<String> searchRecords;

    SearchInputConnectRcvAdapter searchInputConnectRcvAdapter;
    SearchRecordRcvAdapter searchRecordRcvAdapter;
    SearchResultRcvAdapter searchResultRcvAdapter;
    SearchHotSearchRcvAdapter searchHotSearchRcvAdapter;

    PopupWindow popupWindow;
    View popRootView;
    RecyclerView rcvSearchInputConnect;

    boolean isPopupWindowShowing;

    int hotSearchSkip;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initVars();
        initView();
        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public SearchPresenter creatPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void initView() {
        /**
         * 初始化各界面显隐
         */
        showPreSearchPage();
        ivInputClear.setVisibility(View.GONE);
        /**
         * 初始化Adapter
         */
        //搜索记录adapter
        searchRecordRcvAdapter = new SearchRecordRcvAdapter(searchRecords, this);
        searchRecordRcvAdapter.setOnItemClickListener(new SearchRecordRcvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                String s = (String) view.getTag();
                setSearchText(s);
                mPresenter.doSearch();
            }
        });
        //输入联想adapter
        searchInputConnectRcvAdapter = new SearchInputConnectRcvAdapter(searchInputConnects, this);
        searchInputConnectRcvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemCLick(View view) {
                String animeName = (String) view.getTag();
                setSearchText(animeName);
                mPresenter.doSearch();
            }
        });
        //热搜adapter
        searchHotSearchRcvAdapter = new SearchHotSearchRcvAdapter(hotSearches, this);
        //搜索结果adapter
        searchResultRcvAdapter = new SearchResultRcvAdapter(searchResults, this);

        /**
         * 初始化Recyclerview
         */
        //搜索记录
        rcvSearchRecord.setLayoutManager(new LinearLayoutManager(this));
        rcvSearchRecord.setAdapter(searchRecordRcvAdapter);
        //搜索结果
        rcvSearchResult.setAdapter(searchResultRcvAdapter);
        rcvHotSearch.setAdapter(searchHotSearchRcvAdapter);
        //输入联想
        popRootView = LayoutInflater.from(this).inflate(R.layout.activity_search_part_popupwindow, null);
        rcvSearchInputConnect = (RecyclerView) popRootView.findViewById(R.id.rcv_input_connect);
        rcvSearchInputConnect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvSearchInputConnect.setAdapter(searchInputConnectRcvAdapter);

        /**
         * 初始化popupWindow
         */
        popupWindow = new PopupWindow(popRootView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        ColorDrawable drawable = new ColorDrawable();
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.setOutsideTouchable(true);

        /**
         * 初始化点击事件
         */
        RxView.clicks(ivKeyBack)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        SearchActivity.this.finish();
                    }
                });

        RxView.clicks(ivSearch)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (popupWindow.isShowing()) {
                            dismissInputConnectPopWindow();
                        }
                        mPresenter.doSearch();
                    }
                });

        RxView.clicks(ivInputClear)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        setSearchText("");
                    }
                });

        RxView.clicks(ibHotSearchRefresh)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mPresenter.clearSearchRecords();
                    }
                });

        RxView.clicks(ibHotSearchRefresh)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        //如果上次热搜词刷新结果小于10条 或者 分页超过6页
                        if (hotSearches.size() < 10 || hotSearchSkip > 5) {
                            hotSearchSkip = 0;
                        } else {
                            ++hotSearchSkip;
                        }
                        mPresenter.loadHotSearchs(hotSearchSkip * 10);
                    }
                });

        RxTextView.afterTextChangeEvents(etSearch)
                .subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        String searchText = textViewAfterTextChangeEvent.editable().toString();
                        if (searchText.isEmpty()) {
                            dismissInputConnectPopWindow();
                        } else {
                            mPresenter.loadSearchInputConnect();
                        }
                    }
                });

    }

    @Override
    public void loadData() {
        mPresenter.loadSearchRecords();
        mPresenter.loadHotSearchs(0);
    }

    @Override
    public void initVars() {
        searchRecords = new ArrayList<>();
        searchResults = new ArrayList<>();
        hotSearches = new ArrayList<>();
        searchInputConnects = new ArrayList<>();

        hotSearchSkip = 0;
    }


    @Override
    public String getSearchText() {
        return etSearch.getText().toString();
    }

    @Override
    public void setSearchText(String searchText) {
        etSearch.setText(searchText);
        etSearch.setSelection(getSearchText().length());
    }

    @Override
    public void loadHotSearchToFollowLayout(List<HotSearch> hotSearches) {
        flowlayoutHotSearch.removeAllViews();
        TextView textView;
        for (final HotSearch hotSearch : hotSearches) {
            textView = new TextView(this);

            textView.setBackgroundResource(R.drawable.shape_search_background);
            textView.setText(hotSearch.getSearchText());
            textView.setTag(hotSearch);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSearchText(hotSearch.getSearchText());
                    mPresenter.doSearch();
                }
            });

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
            marginLayoutParams.setMargins(DensityUtil.dip2px(this, 5), DensityUtil.dip2px(this, 2), DensityUtil.dip2px(this, 5), DensityUtil.dip2px(this, 2));
            textView.setLayoutParams(marginLayoutParams);
            flowlayoutHotSearch.addView(textView);
        }
    }


    @Override
    public void loadSearchRecordToRcv(List<String> searchRecords) {
        this.searchRecords.clear();
        this.searchRecords.addAll(searchRecords);
        searchRecordRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadSearchInputConnectToRcv(List<Anime> animes) {
        searchInputConnects.clear();
        searchInputConnects.addAll(animes);
        searchInputConnectRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadSearchResultsToRcv(List<Anime> animes) {

    }

    @Override
    public void loadHotSearchToRcv(List<HotSearch> hotSearches) {
        this.hotSearches.clear();
        this.hotSearches.addAll(hotSearches);
        searchHotSearchRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadErrorMsgToTv(String errorMsg) {

    }

    @Override
    public void showErrorPage() {
        partSearchSucess.setVisibility(View.GONE);
        partSearchPrepare.setVisibility(View.GONE);

        partSearchError.setVisibility(View.VISIBLE);
    }


    @Override
    public void showPreSearchPage() {
        partSearchSucess.setVisibility(View.GONE);
        partSearchError.setVisibility(View.GONE);

        partSearchPrepare.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSucessPage() {
        partSearchError.setVisibility(View.GONE);
        partSearchPrepare.setVisibility(View.GONE);

        partSearchSucess.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInputConnectPopWindow() {

        popupWindow.showAsDropDown(toolBar);

    }

    @Override
    public void dismissInputConnectPopWindow() {
        popupWindow.dismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                etSearch.clearFocus();
                mPresenter.doSearch();
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}

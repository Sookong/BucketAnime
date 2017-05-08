package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.adapter.AccountInputConnectRcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.adapter.LoginPagerAdapter;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.LoginPresenter;
import com.tanwuyu.ivrtym.bucketanime.utils.StringPatternUtil;
import com.tanwuyu.ivrtym.bucketanime.view.ILoginView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ivrtym on 2017/2/24.
 */

public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView,PlatformActionListener {


    List<String> accountRecords;
    View popWindowView;
    PopupWindow inputPopWindow;

    AccountInputConnectRcvAdapter inputConnectRcvAdapter;

    boolean isSocialLoginExpand;
    @BindView(R.id.iv_logo_login)
    ImageView ivLogoLogin;
    @BindView(R.id.viewpager_login)
    ViewPager viewpagerLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_one_key_login)
    TextView tvOneKeyLogin;
    @BindView(R.id.iv_social_indicator)
    ImageView ivSocialIndicator;
    @BindView(R.id.tv_social_login)
    TextView tvSocialLogin;
    @BindView(R.id.social_indicator)
    LinearLayout socialIndicator;
    @BindView(R.id.iv_login_qq)
    ImageView ivLoginQq;

    View pageLoginDefault;
    View pageLoginOneKey;

    EditText etAccount;
    EditText etPassword;

    EditText etPhoneNumber;
    EditText etSmsCode;

    TextView tvAccountError;
    TextView tvPassWordError;

    TextView tvPhoneNumberError;
    TextView tvSmsCodeError;

    ImageView ivShowPassWord;

    ImageView ivClearAccountInput;
    ImageView ivClearPhoneNumberIput;

    Button btnGetSms;

    List<View> pages;
    @BindView(R.id.container_social)
    LinearLayout containerSocial;
    @BindView(R.id.iv_login_wechat)
    ImageView ivLoginWechat;
    @BindView(R.id.iv_login_weibo)
    ImageView ivLoginWeibo;

    Platform qqPlatform;
    Platform wechatPlatform;
    Platform weiboPlatform;

    /**
     * Override Activity
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initVars();
        initView();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Override V_interface
     */


    @Override
    public String getAccountInput() {
        return etAccount.getText().toString();
    }

    @Override
    public void setAccountInput(String account) {
        etAccount.setText(account);
    }

    @Override
    public String getPassWordInput() {
        return etPassword.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        String phoneNumber = etPhoneNumber.getText().toString();
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        etPhoneNumber.setText(phoneNumber);
    }

    @Override
    public String getSmsCode() {
        String smsCode = etSmsCode.getText().toString();
        return smsCode;
    }

    @Override
    public void setSmsCode(String smsCode) {
        etSmsCode.setText(smsCode);
    }

    @Override
    public void showAccountInputClearButton() {
        ivClearAccountInput.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAccountInputClearButton() {
        ivClearAccountInput.setVisibility(View.GONE);
    }

    @Override
    public void showPassWordVisiableButton() {
        ivShowPassWord.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePassWordVisiableButton() {
        ivShowPassWord.setVisibility(View.GONE);
    }

    @Override
    public void showPhoneNumberInputClearButton() {
        ivClearPhoneNumberIput.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePhoneNumberInputClearButton() {
        ivClearPhoneNumberIput.setVisibility(View.GONE);
    }

    @Override
    public void showSocialLoginPart() {
        int socailContainerHeight = containerSocial.getMeasuredHeight();

        final LinearLayout.LayoutParams socailContainerParams = (LinearLayout.LayoutParams) containerSocial.getLayoutParams();

        ValueAnimator valueAnimator = ValueAnimator.ofInt(-socailContainerHeight, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Log.e("VALUE_ANIMATOR", String.valueOf(value));
                socailContainerParams.setMargins(0, 0, 0, value);
                containerSocial.setLayoutParams(socailContainerParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ivSocialIndicator.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();

    }

    @Override
    public void hideSocialLoginPart() {

        int socailContainerHeight = containerSocial.getMeasuredHeight();

        final LinearLayout.LayoutParams socailContainerParams = (LinearLayout.LayoutParams) containerSocial.getLayoutParams();

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, socailContainerHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Log.e("VALUE_ANIMATOR", String.valueOf(value));
                socailContainerParams.setMargins(0, 0, 0, -value);
                containerSocial.setLayoutParams(socailContainerParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ivSocialIndicator.setImageResource(R.drawable.ic_keyboard_arrow_up_white_24dp);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }

    @Override
    public void showInputPopupWindow() {
        inputPopWindow.showAsDropDown(etAccount);
    }

    @Override
    public void hideInputPopupWindow() {
        inputPopWindow.dismiss();
    }

    @Override
    public void loadAccountRecordsToRcv(List<String> accountRecords) {
        this.accountRecords.clear();
        this.accountRecords.addAll(accountRecords);
        inputConnectRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public void enableLoginButton() {
        btnLogin.setEnabled(true);
    }

    @Override
    public void disableLoginButton() {
        btnLogin.setEnabled(false);
    }

    @Override
    public void enableGetSmsButton() {
        btnGetSms.setEnabled(true);
    }

    @Override
    public void disableGetSmsButton() {
        btnGetSms.setEnabled(false);
    }

    @Override
    public void setGetSmsButtonText(String msg) {
        btnGetSms.setText(msg);
    }

    @Override
    public void showAccoutError(String errorMsg) {
        tvAccountError.setText(errorMsg);
        tvAccountError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAccoutError() {
        tvAccountError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPassWordError(String errorMsg) {
        tvPassWordError.setText(errorMsg);
        tvPassWordError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePassWordError() {
        tvPassWordError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPhoneNumberError(String errorMsg) {
        tvPhoneNumberError.setText(errorMsg);
        tvPhoneNumberError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePhoneNumberError() {
        tvPhoneNumberError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startRequestSmsTimeDonw() {
        Flowable.interval(0,1,TimeUnit.SECONDS)
                .take(61)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        disableGetSmsButton();
                    }

                    @Override
                    public void onNext(Long aLong) {
                        setGetSmsButtonText(String.valueOf(60-aLong)+"秒后重试");
                    }

                    @Override
                    public void onError(Throwable t) {
                        enableGetSmsButton();

                    }

                    @Override
                    public void onComplete() {
                        enableGetSmsButton();
                        setGetSmsButtonText("获取验证码");
                    }
                });
    }

    /**
     * Override MVP
     */

    @Override
    public LoginPresenter creatPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {
        //初始化 viewPager
        pageLoginDefault = LayoutInflater.from(this).inflate(R.layout.page_login_default, null);
        pageLoginOneKey = LayoutInflater.from(this).inflate(R.layout.page_login_one_key, null);
        pages.add(pageLoginDefault);
        pages.add(pageLoginOneKey);
        viewpagerLogin.setAdapter(new LoginPagerAdapter(pages, this));
        viewpagerLogin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tvOneKeyLogin.setText(R.string.one_key_login);
                } else {
                    tvOneKeyLogin.setText(R.string.normal_login);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpagerLogin.setCurrentItem(0);

        etAccount = (EditText) pageLoginDefault.findViewById(R.id.et_account);
        etPassword = (EditText) pageLoginDefault.findViewById(R.id.et_password);
        ivClearAccountInput = (ImageView) pageLoginDefault.findViewById(R.id.iv_account_input_clear);
        ivShowPassWord = (ImageView) pageLoginDefault.findViewById(R.id.iv_password_show);
        tvAccountError = (TextView) pageLoginDefault.findViewById(R.id.tv_account_error);
        tvPassWordError = (TextView) pageLoginDefault.findViewById(R.id.tv_password_error);

        etPhoneNumber = (EditText) pageLoginOneKey.findViewById(R.id.et_phone_number);
        etSmsCode = (EditText) pageLoginOneKey.findViewById(R.id.et_sms_code);
        tvPhoneNumberError = (TextView) pageLoginOneKey.findViewById(R.id.tv_phone_number_error);
        tvSmsCodeError = (TextView) pageLoginOneKey.findViewById(R.id.tv_sms_code_error);
        ivClearPhoneNumberIput = (ImageView) pageLoginOneKey.findViewById(R.id.iv_phone_input_clear);
        btnGetSms = (Button) pageLoginOneKey.findViewById(R.id.btn_get_sms);


        //账号输入监听
        RxTextView.textChanges(etAccount)
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        String inputText = charSequence.toString();
                        //显隐 清除输入按钮
                        if (inputText.isEmpty()) {
                            hideAccountInputClearButton();
                        } else {
                            showAccountInputClearButton();
                        }
                        //判断 显示 账号输入错误
                        if (!inputText.isEmpty() && !StringPatternUtil.isEmail(inputText) && !StringPatternUtil.isMobile(inputText)) {
                            showAccoutError("请输入正确的手机号或邮箱");
                            disableLoginButton();
                        } else {
                            hideAccoutError();
                            enableLoginButton();
                        }
                    }
                });
        //密码输入监听
        RxTextView.textChanges(etPassword)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        String inputText = charSequence.toString();
                        //显隐 明文按钮
                        if (inputText.isEmpty()) {
                            hidePassWordVisiableButton();
                        } else {
                            showPassWordVisiableButton();
                        }
                        //判断 显示 密码格式错误
                        if (!inputText.isEmpty() && !StringPatternUtil.isPassword(inputText)) {
                            showPassWordError("请输入正确的密码格式");
                            disableLoginButton();
                        } else {
                            hidePassWordError();
                            enableLoginButton();
                        }
                    }
                });
        //手机号输入监听
        RxTextView.textChanges(etPhoneNumber)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        String inputText = charSequence.toString();
                        //显隐 清除输入按钮
                        if (inputText.isEmpty()) {
                            hidePhoneNumberInputClearButton();
                        } else {
                            showPhoneNumberInputClearButton();
                        }
                        //判断 显示 手机号输入错误
                        if (!inputText.isEmpty() && !StringPatternUtil.isMobile(inputText)) {
                            showPhoneNumberError("请输入正确的手机号");
                        } else {
                            hidePhoneNumberError();
                        }
                    }
                });
        //账号输入清除按钮 监听
        RxView.clicks(ivClearAccountInput)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        setAccountInput("");
                    }
                });
        //显示密码按钮 监听
        RxView.clicks(ivShowPassWord)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        //显示明文
                        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        etPassword.setSelection(getPassWordInput().length());
                        ivShowPassWord.setImageResource(R.drawable.ic_show_password_blue);
                        //1秒后隐藏明文
                        Flowable.timer(1, TimeUnit.SECONDS)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(@NonNull Long aLong) throws Exception {
                                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                        etPassword.setSelection(getPassWordInput().length());
                                        ivShowPassWord.setImageResource(R.drawable.ic_show_password);
                                    }
                                });
                    }
                });
        //弹出第三方登录 点击 监听
        RxView.clicks(socialIndicator)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (isSocialLoginExpand) {
                            hideSocialLoginPart();
                            isSocialLoginExpand = false;
                        } else {
                            showSocialLoginPart();
                            isSocialLoginExpand = true;
                        }
                    }
                });
        // 切换 一键登录页面 点击 监听
        RxView.clicks(tvOneKeyLogin)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        viewpagerLogin.setCurrentItem((viewpagerLogin.getCurrentItem() + 1) % 2);
                    }
                });
        //登录按钮监听
        RxView.clicks(btnLogin)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Log.e("LOGIN_GO","-----");
                        if (viewpagerLogin.getCurrentItem()==0){
                            mPresenter.doLogin();
                        }else{
                            mPresenter.doOneKeyLogin();
                        }

                    }
                });
        //获取验证码按钮 监听
        RxView.clicks(btnGetSms)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mPresenter.requestSmsCode();
                    }
                });
        // QQ 登录按钮 监听
        RxView.clicks(ivLoginQq)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        qqPlatform.SSOSetting(false);
                        qqPlatform.showUser(null);

                    }
                });
        // weChat 登录按钮 监听
        RxView.clicks(ivLoginWechat)
                .subscribe();
        //sinaWeibo 登录按钮 监听
        RxView.clicks(ivLoginWeibo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        weiboPlatform.SSOSetting(false);
                        weiboPlatform.authorize();
                        //weiboPlatform.showUser(null);
                    }
                });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initVars() {
        isSocialLoginExpand = false;
        pages = new ArrayList<>();
        //初始化第三方登录平台
        qqPlatform = ShareSDK.getPlatform(QQ.NAME);
        wechatPlatform = ShareSDK.getPlatform(Wechat.NAME);
        weiboPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);

        weiboPlatform.setPlatformActionListener(this);
        weiboPlatform.setPlatformActionListener(this);
        weiboPlatform.setPlatformActionListener(this);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}

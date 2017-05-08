package com.tanwuyu.ivrtym.bucketanime.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pili.pldroid.player.IMediaController;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.utils.NetWorkUtil;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ivrty on 2017/3/23.
 */

public class BucketMediaController extends FrameLayout implements IMediaController {
    private Context mContext;

    //奇葩接口命名,其实是关联的VideoView,抽取出来的一丢丢set,get方法
    MediaPlayerControl mPlayer;

    //底部控制栏
    private LinearLayout buttomBarContainer;  //半屏
    private LinearLayout buttomBarFullScreenContainer;  //全屏
    private SeekBar mSeekBar;
    private TextView tvCurrentTime;
    private TextView tvEndTime;
    private ImageView ivPlay;
    private ImageView ivFullScreen;
    private ImageView ivFastForward;
    private ImageView ivLock;
    //顶部控制栏
    private LinearLayout topBarContainer;
    private ImageView ivBack;
    private ImageView ivNetwork;
    private ImageView ivBattery;
    private TextView tvSystemTime;
    //中间指示器
    private LinearLayout indicatorCenterContainer;
    private ImageView ivIndicatorCenter;
    private SeekBar seekBarIndicatorCenter;
    private TextView tvIndicatorCenterLeft;
    private TextView tvIndicatorCenterRight;
    //左边锁屏指示器
    private LinearLayout indicatorLeftContainer;
    private ImageView iv_IndicatorLock;

    private boolean locked;
    private boolean controllerShowing;
    private boolean fullScreen;


    public static final int CONTROLLER_FADE_OUT = 1;
    public static final int CONTROLLER_SHOW = 2;
    public static final int INDICATOR_CENTER_SHOW = 3;
    public static final int INDICATOR_CENTER_FADE_OUT = 4;
    public static final int INDICATOR_LEFT_LOCK_SHOW = 5;
    public static final int INDICATOR_LEFT_LOCK_FAD_OUT = 6;

    //默认控制器显示时长
    public static final int DEFAULT_TIMEOUT = 3000;

    private AudioManager audioManager;

    private BatteryBroadcastReceiver batteryBroadcastReceiver;
    private NetWorkBroadcastReceiver netWorkBroadcastReceiver;
    private GestureDetectorCompat gestureDetectorCompat;

    /**
     * 构造方法
     *
     * @param context
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public BucketMediaController(Context context) {
        super(context);
        this.mContext = context;
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        gestureDetectorCompat = new GestureDetectorCompat(mContext, new SlideGestureDetectorListener());
        fullScreen = true;
        locked = false;
        setVisibility(GONE);

    }

    public BucketMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        gestureDetectorCompat = new GestureDetectorCompat(mContext, new SlideGestureDetectorListener());
        fullScreen = true;
        locked = false;
        setVisibility(GONE);


    }

    public BucketMediaController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        gestureDetectorCompat = new GestureDetectorCompat(mContext, new SlideGestureDetectorListener());
        fullScreen = true;
        locked = false;
        setVisibility(GONE);
    }


    /**
     * Handler
     */

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONTROLLER_FADE_OUT:
                    hide();
                    break;
                case INDICATOR_CENTER_FADE_OUT:
                    indicatorCenterContainer.setVisibility(View.GONE);
                    break;
                case INDICATOR_LEFT_LOCK_FAD_OUT:
                    indicatorLeftContainer.setVisibility(View.GONE);
                    break;
            }
        }
    };

    /**
     * 重写Framelayout中方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获得控制栏实例
        buttomBarContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.media_controller_bar_bottom, null);
        buttomBarFullScreenContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.media_controller_bar_bottom_full_screen, null);
        topBarContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.media_controller_bar_top, null);
        indicatorCenterContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.media_controller_indicator_center, null);
        indicatorLeftContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.media_controller_lock_indicator, null);
        //加入控制器view视图中
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.BOTTOM;
        buttomBarContainer.setLayoutParams(params1);
        buttomBarFullScreenContainer.setLayoutParams(params1);
        addView(buttomBarContainer);
        addView(buttomBarFullScreenContainer);

        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.TOP;
        topBarContainer.setLayoutParams(params2);
        addView(topBarContainer);

        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params3.gravity = Gravity.CENTER;
        indicatorCenterContainer.setLayoutParams(params3);
        addView(indicatorCenterContainer);

        FrameLayout.LayoutParams params4 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params4.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        indicatorLeftContainer.setLayoutParams(params4);
        addView(indicatorLeftContainer);

        //初始隐藏
        setViewVisible(buttomBarContainer, buttomBarFullScreenContainer, topBarContainer, indicatorCenterContainer, indicatorLeftContainer);

        //绑定控件(底部控件动态绑定)
        //顶部
        ivBack = (ImageView) topBarContainer.findViewById(R.id.iv_back);
        ivNetwork = (ImageView) topBarContainer.findViewById(R.id.iv_network);
        ivBattery = (ImageView) topBarContainer.findViewById(R.id.iv_battery);
        tvSystemTime = (TextView) topBarContainer.findViewById(R.id.tv_time);
        //中间
        seekBarIndicatorCenter = (SeekBar) indicatorCenterContainer.findViewById(R.id.seek_bar_indicator_center);
        ivIndicatorCenter = (ImageView) indicatorCenterContainer.findViewById(R.id.iv_indicator);
        tvIndicatorCenterLeft = (TextView) indicatorCenterContainer.findViewById(R.id.tv_indicator_left);
        tvIndicatorCenterRight = (TextView) indicatorCenterContainer.findViewById(R.id.tv_indicator_right);
        //左侧
        iv_IndicatorLock = (ImageView) indicatorLeftContainer.findViewById(R.id.iv_indicator_lock);

        iv_IndicatorLock.setOnClickListener(ivLockIndicatorClickListener);
        ivBack.setOnClickListener(ivBackClickListener);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        batteryBroadcastReceiver = new BatteryBroadcastReceiver();
        netWorkBroadcastReceiver = new NetWorkBroadcastReceiver();

        IntentFilter batteryIntentFliter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter netWorkIntentFliter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        mContext.registerReceiver(batteryBroadcastReceiver, batteryIntentFliter);
        mContext.registerReceiver(netWorkBroadcastReceiver, netWorkIntentFliter);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (batteryBroadcastReceiver != null)
            mContext.unregisterReceiver(batteryBroadcastReceiver);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetectorCompat.onTouchEvent(event);
    }


    /**
     * 实现IMediaControl接口
     *
     * @param
     */
    @Override
    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        mPlayer = mediaPlayerControl;
    }

    //为videoView设置setMediaCotroller后,videoView调用该方法显示控制器
    @Override
    public void show() {
        show(DEFAULT_TIMEOUT);
    }

    @Override
    public void show(int timeOut) {
        //必须视频加载成功才显示控制器
        Log.e("SHOW", "used");
        updateLockButton(locked);
        updateFullscreenButton(fullScreen);
        updatePlayButton(mPlayer.isPlaying());
        updateMediaControllerType(fullScreen);
        updateSystemTime();
        updateSeekBarProgress();

        if (mPlayer.getDuration() > 0) {
            showViewTimeOut(this, DEFAULT_TIMEOUT);
            controllerShowing = true;
        }
    }

    //为videoView设置setMediaCotroller后,videoView调用该方法显示控制器
    @Override
    public void hide() {
        setViewGone(this);
        controllerShowing = false;

    }

    @Override
    public boolean isShowing() {
        return controllerShowing;
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public void setAnchorView(View view) {

    }


    /**
     * 更新布局
     */

    //根据是否全屏绑定对应的底部控件(半/全屏控制器布局不同)
    private void bindViewByOrientation(Boolean fullScreen) {
        if (fullScreen) {
            //全屏底部
            mSeekBar = (SeekBar) buttomBarFullScreenContainer.findViewById(R.id.seek_bar);
            tvCurrentTime = (TextView) buttomBarFullScreenContainer.findViewById(R.id.tv_current_time);
            tvEndTime = (TextView) buttomBarFullScreenContainer.findViewById(R.id.tv_end_time);
            ivPlay = (ImageView) buttomBarFullScreenContainer.findViewById(R.id.iv_play);
            ivFullScreen = (ImageView) buttomBarFullScreenContainer.findViewById(R.id.iv_fullscreen);
            ivFastForward = (ImageView) buttomBarFullScreenContainer.findViewById(R.id.iv_fast_forward);
            ivLock = (ImageView) buttomBarFullScreenContainer.findViewById(R.id.iv_lock);


        } else {
            //normal底部
            mSeekBar = (SeekBar) buttomBarContainer.findViewById(R.id.seek_bar);
            tvCurrentTime = (TextView) buttomBarContainer.findViewById(R.id.tv_current_time);
            tvEndTime = (TextView) buttomBarContainer.findViewById(R.id.tv_end_time);
            ivPlay = (ImageView) buttomBarContainer.findViewById(R.id.iv_play);
            ivFullScreen = (ImageView) buttomBarContainer.findViewById(R.id.iv_fullscreen);

        }
        //设置底部栏点击监听
        if (ivPlay != null && !ivPlay.hasOnClickListeners())
            ivPlay.setOnClickListener(ivPlayOnclickListener);
        if (ivLock != null && !ivLock.hasOnClickListeners())
            ivLock.setOnClickListener(ivLockClickListener);
        if (ivFastForward != null && !ivFastForward.hasOnClickListeners())
            ivFastForward.setOnClickListener(ivFastForwardClickListener);
        if (ivFullScreen != null && !ivFullScreen.hasOnClickListeners())
            ivFullScreen.setOnClickListener(ivFullScreenClickListener);
    }

    //更新各控制条显示状态  (锁屏|切换全屏时调用)
    private void updateMediaControllerType(Boolean fullScreen) {
        bindViewByOrientation(fullScreen);
        if (!fullScreen) {
            //非全屏,显示normal底部栏
            setViewGone(buttomBarFullScreenContainer, indicatorCenterContainer, topBarContainer, indicatorLeftContainer);
            setViewVisible(buttomBarContainer);
        } else {
            if (locked) {
                //全屏锁定,显示左侧lock指示器
                setViewGone(buttomBarContainer, buttomBarFullScreenContainer, indicatorCenterContainer, topBarContainer);
                setViewVisible(indicatorLeftContainer);
            } else {
                //全屏无锁,显示顶部栏+全屏底部栏  隐藏左侧指示器+中部指示器+normal底部栏
                setViewGone(indicatorLeftContainer, indicatorCenterContainer, buttomBarContainer);
                setViewVisible(topBarContainer, buttomBarFullScreenContainer);
            }
        }

    }


    //更新按键
    private void updatePlayButton(boolean isPlaying) {
        if (isPlaying) {
            ivPlay.setImageResource(R.drawable.ic_pause_white_24dp);
        } else {
            ivPlay.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        }
    }

    //更新全屏键
    private void updateFullscreenButton(boolean isFullScreen) {
        if (isFullScreen) {
            ivFullScreen.setImageResource(R.drawable.ic_fullscreen_exit_white_24dp);
        } else {
            ivFullScreen.setImageResource(R.drawable.ic_fullscreen_white_24dp);
        }

        //切换全屏后布局发送变化,需调用updateMediaControllerType
        updateMediaControllerType(fullScreen);
        updatePlayButton(mPlayer.isPlaying());

    }

    //更新锁屏
    private void updateLockButton(boolean isLocked) {
        if (isLocked) {
            ivLock.setImageResource(R.drawable.ic_lock_open_white_24dp);
            iv_IndicatorLock.setImageResource(R.drawable.ic_lock_open_white_24dp);
            //锁定后控制器样式发生变化,必须重新设置样式

        } else {
            ivLock.setImageResource(R.drawable.ic_lock_outline_white_24dp);
            iv_IndicatorLock.setImageResource(R.drawable.ic_lock_outline_white_24dp);

        }

        updateMediaControllerType(fullScreen);
    }

    //更新电量信息
    private void updateBattery(float percent) {
        Log.e("Battery", "now:" + percent);
        if (percent > 0.98f) {
            ivBattery.setImageResource(R.drawable.ic_battery_full_green_a700_24dp);
            return;
        }
        if (percent > 0.88f) {
            ivBattery.setImageResource(R.drawable.ic_battery_90_white_24dp);
            return;
        }
        if (percent > 0.78f) {
            ivBattery.setImageResource(R.drawable.ic_battery_80_white_24dp);
            return;
        }
        if (percent > 0.58f) {
            ivBattery.setImageResource(R.drawable.ic_battery_60_white_24dp);
            return;
        }
        if (percent > 0.48f) {
            ivBattery.setImageResource(R.drawable.ic_battery_50_white_24dp);
            return;
        }
        if (percent > 0.28f) {
            ivBattery.setImageResource(R.drawable.ic_battery_30_white_24dp);
            return;
        }
        if (percent > 0.15f) {
            ivBattery.setImageResource(R.drawable.ic_battery_20_yellow_900_24dp);
            return;
        }
        if (percent > 0.05f) {
            ivBattery.setImageResource(R.drawable.ic_battery_20_red_900_24dp);
            return;
        }
        if (percent > 0f) {
            ivBattery.setImageResource(R.drawable.ic_battery_alert_red_900_24dp);
            return;
        }

    }

    //更新系统时间
    private void updateSystemTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH : mm ");
        tvSystemTime.setText(simpleDateFormat.format(new Date()));
    }

    private void updateNetWork(int apnType) {
        switch (apnType) {
            case NetWorkUtil.NETWORK_TYPE_WIFI:
                ivNetwork.setImageResource(R.drawable.ic_network_wifi_white_24dp);
                break;
            case NetWorkUtil.NETWORK_TYPE_DISABLE:
                ivNetwork.setImageResource(R.drawable.ic_signal_cellular_off_white_24dp);
                break;
            default:
                ivNetwork.setImageResource(R.drawable.ic_network_cell_white_24dp);

        }
    }


    //锁屏解锁
    private void doLockScreen() {
        if (locked) {
            locked = false;
            updateLockButton(false);
        } else {
            locked = true;
            updateLockButton(true);
        }

    }

    //播放暂停
    private void doPlayOrPause() {
        if (mPlayer.isPlaying() && mPlayer.canPause()) {
            mPlayer.pause();
            updatePlayButton(false);

        } else {
            mPlayer.start();
            updatePlayButton(true);
        }

    }

    //全屏切换
    private void doFullScreen() {
        if (fullScreen) {
            fullScreen = false;
            updateFullscreenButton(false);
        } else {
            fullScreen = true;
            updateFullscreenButton(true);
        }

    }

    //返回
    private void doBack() {

    }

    //快进
    private void doFastForward() {

    }

    //设置seekbar进度
    private long updateSeekBarProgress() {
        if (mPlayer == null)
            return 0;
        long position = mPlayer.getCurrentPosition();
        long duration = mPlayer.getDuration();

        if (duration > 0) {
            long seekBarPos = 1000L * position / duration;
            mSeekBar.setProgress((int) seekBarPos);
        }

        tvCurrentTime.setText(generateTime(position));
        tvEndTime.setText(generateTime(duration));

        return position;
    }

    //视频长度转时间格式
    private static String generateTime(long position) {
        int totalSeconds = (int) (position / 1000);

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds).toString();
        }
    }

    //超时显示
    private void showViewTimeOut(View view, int timeOut) {
        setViewVisible(view);
        if (timeOut > 0) {
            if (this == view) {
                //移除旧的延时消息
                mHandler.removeMessages(CONTROLLER_FADE_OUT);
                //重新复用消息发送延时
                mHandler.sendMessageDelayed(mHandler.obtainMessage(CONTROLLER_FADE_OUT), timeOut);
            } else {
                switch (view.getId()) {
                    case R.id.media_controller_indicator_center_container:
                        //移除旧的延时消息
                        mHandler.removeMessages(INDICATOR_CENTER_FADE_OUT);
                        //重新复用消息发送延时
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(INDICATOR_CENTER_FADE_OUT), timeOut);
                        break;
                    case R.id.media_controller_lock_indicator_container:
                        mHandler.removeMessages(INDICATOR_LEFT_LOCK_FAD_OUT);
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(INDICATOR_LEFT_LOCK_FAD_OUT), timeOut);
                }
            }
        } else {
            setViewGone(view);
        }

    }

    //滑动设置音量
    private void setVolumePercent(float percent) {
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeOffset = (int) (percent * maxVolume);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume + volumeOffset, 0);

        ivIndicatorCenter.setImageResource(R.drawable.ic_volume_up_white_24dp);

        setViewVisible(seekBarIndicatorCenter, ivIndicatorCenter);
        setViewGone(tvIndicatorCenterLeft, tvIndicatorCenterRight);


        seekBarIndicatorCenter.setMax(maxVolume);
        seekBarIndicatorCenter.setProgress(currentVolume);

        showViewTimeOut(indicatorCenterContainer, DEFAULT_TIMEOUT);
    }

    //滑动设置亮度
    private void setBrightnessPercent(float percent) {

    }

    //滑动设置进度
    // private

    /**
     * 各种监听
     */

    //按键点击监听器
    private OnClickListener ivPlayOnclickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            doPlayOrPause();
        }
    };
    private OnClickListener ivLockClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            doLockScreen();
        }
    };
    private OnClickListener ivLockIndicatorClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            doLockScreen();
        }
    };
    private OnClickListener ivFullScreenClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            doFullScreen();
        }
    };
    private OnClickListener ivBackClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            doBack();
        }
    };
    private OnClickListener ivFastForwardClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            doFastForward();
        }
    };

    //SeekBar监听器
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    //手势滑动监听  垃圾手势 垃圾!!!
    private class SlideGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e("SCROLL", "getwidth" + getWidth());
            if (fullScreen) {
                float oldX = e1.getX();
                float oldY = e1.getY();
                float newX = e2.getX();
                float newY = e2.getY();
                //如果在屏幕右1/5区域,则调整音量
                if (oldX < getWidth() && oldX > getWidth() / 5 * 4) {
                    //除以滑动百分百除以系数,防止音量一次设置太多
                    setVolumePercent(-((newY - oldY) / getHeight()) / 2f);
                    Log.e("Slide","percent:"+((newY - oldY) / getHeight()) / 2f);
                }
                //如果在屏幕左1/5区域,则调整亮度
                if (oldX > 0 && oldX < getWidth() / 5 * 1) {
                    setBrightnessPercent((newY - oldY) / getHeight() / 2f);
                }
                //如果在屏幕中部则拖拉进度
                if (oldX > getWidth() / 5 && oldX < getWidth() / 5 * 4) {

                }
            }
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            doPlayOrPause();
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }


    /**
     * BroadcastReceiver
     *
     * @param
     */
    private class BatteryBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int current = intent.getExtras().getInt("level");
            int total = intent.getExtras().getInt("scale");
            float percent = (float) current / (float) total;
            updateBattery(percent);
        }
    }

    private class NetWorkBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int apnType = NetWorkUtil.getAPNType(mContext);
            updateNetWork(apnType);
        }
    }

    //公开外部方法
    public void setFullscreenState(Boolean fullScreen) {
        updateMediaControllerType(fullScreen);
    }


    /**
     * 代码
     *
     * @param views
     */


    private void setViewVisible(View... views) {
        for (View view : views) {
            if (view != null && view.getVisibility() != View.VISIBLE)
                view.setVisibility(View.VISIBLE);
        }
    }

    private void setViewGone(View... views) {
        for (View view : views) {
            if (view != null && view.getVisibility() != View.GONE)
                view.setVisibility(View.GONE);
        }
    }

}

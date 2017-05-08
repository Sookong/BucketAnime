package com.tanwuyu.ivrtym.bucketanime.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by ivrty on 2017/3/20.
 */

public class NetWorkUtil {
    public static final int NETWORK_TYPE_DISABLE = -1;
    public static final int NETWORK_TYPE_WIFI = 0;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static final int NETWORK_TYPE_4G = 4;

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * WIFI是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 移动网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取网络类型
     * @param context
     * @return
     */
    public static int getAPNType(Context context) {
        int apnType = -1;
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //是否有网
            if (networkInfo == null) {
                apnType = NETWORK_TYPE_DISABLE;
                return apnType;
            }

            int netType = networkInfo.getType();

            if (netType == ConnectivityManager.TYPE_WIFI) {
                apnType = NETWORK_TYPE_WIFI;
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                int netSubType = networkInfo.getSubtype();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (netSubType == TelephonyManager.NETWORK_TYPE_LTE
                        && !telephonyManager.isNetworkRoaming()) {
                    apnType = NETWORK_TYPE_4G;
                } else if (netSubType == TelephonyManager.NETWORK_TYPE_UMTS
                        || netSubType == TelephonyManager.NETWORK_TYPE_HSDPA
                        || netSubType == TelephonyManager.NETWORK_TYPE_EVDO_0
                        && !telephonyManager.isNetworkRoaming()){
                    apnType = NETWORK_TYPE_3G;
                }else if (netSubType == TelephonyManager.NETWORK_TYPE_GPRS
                        || netSubType == TelephonyManager.NETWORK_TYPE_EDGE
                        || netSubType == TelephonyManager.NETWORK_TYPE_CDMA
                        && !telephonyManager.isNetworkRoaming()){
                    apnType = NETWORK_TYPE_2G;
                }
            }
        }
        return apnType;
    }
}

package com.mr235.androidcodesnippet.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/4/14.
 */
public class NetWorkUtils {

    private static NetWorkUtils ourInstance = new NetWorkUtils();

    public static NetWorkUtils getInstance() {
        return ourInstance;
    }

    private NetWorkUtils() {}


    /**
     * 检测网络是否连接
     *
     * @return
     */
    public static boolean isNetConnected() {
        ConnectivityManager cm = (ConnectivityManager) AppSystem.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] infos = cm.getAllNetworkInfo();
            if (infos != null) {
                for (NetworkInfo ni : infos) {
                    if (ni.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检测wifi是否连接
     *
     * @return
     */
    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) AppSystem.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测3G是否连接
     *
     * @return
     */
    public static boolean is3gConnected() {
        ConnectivityManager cm = (ConnectivityManager) AppSystem.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测GPS是否打开
     *
     * @return
     */
    public static boolean isGpsEnabled() {
        LocationManager lm = (LocationManager) AppSystem.getInstance().getContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleProviders = lm.getProviders(true);
        for (String name : accessibleProviders) {
            if ("gps".equals(name)) {
                return true;
            }
        }
        return false;
    }
}

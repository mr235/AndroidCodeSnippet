package com.mr235.androidcodesnippet.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/4/15.
 * 常用工具类
 */
public class CommonUtils {
    private CommonUtils(){}

    /** 没有网络 */
    public static final int NETWORKTYPE_INVALID = 0;
    /** wap网络 */
    public static final int NETWORKTYPE_WAP = 1;
    /** 2G网络 */
    public static final int NETWORKTYPE_2G = 2;
    /** 3G和3G以上网络，或统称为快速网络 */
    public static final int NETWORKTYPE_3G = 3;
    /** wifi网络 */
    public static final int NETWORKTYPE_WIFI = 4;

    public static boolean isPhone(String phone) {
        return phone !=null && phone.matches("1[0-9]{10}");
    }

    public static boolean isEmail(String email) {
        return email !=null && email.matches("\\w+@(\\w+.)+[a-z]{2,3}");
    }
    /**
     * true:是中文、大小写字母
     * 否则false
     * */
    public static boolean checkName(String name) {

        if (name==null) {
            return false;
        }
        String all  = "^[\\u4E00-\\u9FA5[A-Za-z]]+";
        Pattern pattern = Pattern.compile(all);
        return pattern.matcher(name).matches();
    }
    /**
     * 将Unicode转成汉字
     * @param str
     * @return
     */
    public static String unicode2Chinese(String str){
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while((i=str.indexOf("\\u", pos)) != -1){
            sb.append(str.substring(pos, i));
            if(i+5 < str.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(str.substring(i+2, i+6), 16));
            }
        }
        sb.append(str.substring(pos));

        return sb.toString();
    }

    public static String checkNullStr(String str) {
        if (str==null) {
            str = "";
        }
        return str;
    }

    /** 移除小数点后的0 */
    public static String removePointOfZero(double d) {
        if (d%1.0==0) {
            return String.valueOf((long) d);
        } else {
            return String.valueOf(removeExtra0(d));
        }
    }

    /** 移除小数点后的0 */
    public static String removePointOfZero(float d) {
        return removePointOfZero((double) d);
    }
    /** 去掉多余的0及 很长的小数 */
    public static double removeExtra0(double payOff) {
        return (double)(Math.round(payOff * 100)/100.0);
    }

    private static Screen screen = null;
    public static Screen getScreenSize() {
        if (screen!=null) {
            return screen;
        }
        Context context = AppSystem.getInstance().getContext();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth  = windowManager.getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
        int screenHeight = windowManager.getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）
        Screen screen = new Screen();
        screen.width = screenWidth;
        screen.height = screenHeight;
        return screen;
    }

    private static Screen realScreen = null;
    /**
     * 获取屏幕尺寸
     * @return
     */
    public static Screen getScreenRealSize() {
        if (realScreen!=null) {
            return realScreen;
        }
        Context context = AppSystem.getInstance().getContext();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, dm);
            realScreen = new Screen();
            realScreen.height = dm.heightPixels;
            realScreen.width = dm.widthPixels;

        }catch(Exception e){
            e.printStackTrace();
            realScreen = new Screen();
        }

        return realScreen;
    }
    /**
     * 获取屏幕物理尺寸
     * @return
     */
    public static double getScreenPhysicalSize() {

        Screen screenSize = getScreenRealSize();
        int width = screenSize.getWidth();
        int height = screenSize.getHeight();
        double sqrt = Math.sqrt(Math.pow(width/getDisplayMetrics().xdpi, 2) + Math.pow(height/getDisplayMetrics().ydpi, 2));

        return sqrt;
    }

    public static int getDensityDpi() {
        return getDisplayMetrics().densityDpi;
    }

    private static DisplayMetrics getDisplayMetrics() {
        return AppSystem.getInstance().getContext().getResources().getDisplayMetrics();
    }

    public static float getDensity() {
        return getDisplayMetrics().density;
    }

    public static class Screen{
        int width;
        int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    /** 隐藏键盘 */
    public static void hideSoftInputFromWindow(View view) {
        Context context = AppSystem.getInstance().getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    /** 显示键盘 */
    public static void showSoftInput(View view) {
        Context context = AppSystem.getInstance().getContext();
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }



    public static int dp2px(float dp) {
        final float scale = getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(float px) {
        final float scale = getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    public static int px2sp(float pxValue) {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取网络状态，wifi,wap,2g,3g.
     *
     * @return int 网络状态 {@link #NETWORKTYPE_2G},{@link #NETWORKTYPE_3G},
     * {@link #NETWORKTYPE_INVALID},{@link #NETWORKTYPE_WAP}
     *  <p>{@link #NETWORKTYPE_WIFI}
     */
    public static int getNetWorkType() {
        Context context = AppSystem.getInstance().getContext();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        int mNetWorkType = NETWORKTYPE_INVALID;
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = NETWORKTYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                mNetWorkType = TextUtils.isEmpty(proxyHost)
                        ? (isFastMobileNetwork(context) ? NETWORKTYPE_3G : NETWORKTYPE_2G)
                        : NETWORKTYPE_WAP;
            }
        } else {
            mNetWorkType = NETWORKTYPE_INVALID;
        }
        return mNetWorkType;
    }

    /**
     * 网速是否快
     */
    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }


    /**
     * 获取application中指定的meta-data
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        Context ctx = AppSystem.getInstance().getContext();
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }
    /**
     * 判断app是否在前台运行
     * @param con
     * @return boolean
     */
    public static boolean isRunningForeground(Context con) {
        ActivityManager am = (ActivityManager) con.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(con.getPackageName())) {
            return true;
        }
        return false;
    }

    /** 某Activity界面是否在最前 */
    public static boolean isRunningForeground(Context con,Class<?> activity) {
        ActivityManager am = (ActivityManager) con.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningTaskInfo taskInfo : am.getRunningTasks(2000)) {
            if (taskInfo.topActivity.getPackageName().equals(con.getPackageName())) {
                return taskInfo.topActivity.getClassName().equals(activity.getClass().getName());
            }
        }
        return false;
    }

    /** 获取应用打开了多少activity */
    public static int getForegroundActivityNum(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningTaskInfo taskInfo : am.getRunningTasks(2000)) {
            if (taskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                return taskInfo.numActivities;
            }
        }
        return 0;
    }

    /**
     * 复制
     * @param str
     */
    public static void copy(String str) {
        ClipboardManager copy = (ClipboardManager) AppSystem.getInstance().getContext()
                .getSystemService(Context.CLIPBOARD_SERVICE);
        copy.setText(str);
    }
}

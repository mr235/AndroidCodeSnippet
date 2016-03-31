package com.mr235.androidcodesnippet.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/4/11.
 * 常用数据保存类
 */
public class SPUtils extends BaseSP {

    private static final String SP_NAME = "config";

    /** 手机号获取验证码的时间 */
    private static final String CHECK_TIME = "check_time";
    /** 手机号获取到的验证码 */
    private static final String CHECK_CODE = "phone_check_code";
    /** 登录状态 -1未登录，0登记状态，1登录状态 */
    private static final String LOGIN_STATUS = "login_status";
    /** 当前登录的手机号 */
    private static final String LOGIN_PHONE = "login_phone";
    /** 当前登录用户的UserId */
    private static final String LOGIN_USER_ID = "login_user_id";
    /** 临时用户身份 */
    private static final String TEMP_USER_ID = "temp_user_id";
    /** 服务器时间差 */
    private static final String SERVER_TIME_DIFFERENCE = "server_time_difference";
    // 首次进入
    private static final String ENTER_FIRST = "enter_first";
    // 首页图片地址
    private static final String URL_SPLASH_IMAGE = "url_splash_image";
    // 启动页图片已经下载
    private static final String URL_SPLASH_IMAGE_HAS_DOWNLOAD = "url_splash_image_has_download";

    private SPUtils() {
        super("SP_NAME");
    }

    private static SPUtils instance = new SPUtils();

    public static SPUtils getInstance() {
        return instance;
    }

    /** 设置手机获取验证码的开始时间 */
    public void saveCheckTime(String phone, long time) {
        saveLong(CHECK_TIME + phone, time);
    }
    /** 手机获取验证码的开始时间 */
    public long getCheckTime(String phone) {
        return getLong(CHECK_TIME + phone);
    }
    /** 保存手机号获取到的code */
    public void saveCheckCode(String phone, String code) {
        saveString(CHECK_CODE + phone, code);
    }
    /** 获取手机号获取到的code */
    public String getCheckCode(String phone) {
        return getString(CHECK_CODE + phone);
    }

    /** 保存用户登录状态 */
    public void setLoginStatus(int status) {
        saveInt(LOGIN_STATUS, status);
    }

    /** 获取用户登录状态 */
    public int getLoginStatus() {
        return getInt(LOGIN_STATUS, -1);
    }

    /** 保存登录的手机号 */
    public void setLoginPhone(String phone) {
        saveString(LOGIN_PHONE, phone);
    }

    /** 获取登录的手机号 */
    public String getLoginPhone() {
        return getString(LOGIN_PHONE);
    }

    /** 保存登录用户的ID */
    public void setLoginUserId(long userId) {
        saveLong(LOGIN_USER_ID, userId);
    }

    /** 获取登录用户的ID */
    public long getLoginUserId() {
        return getLong(LOGIN_USER_ID);
    }


    /** 保存临时用户ID*/
    public void setTempUserId(long userId) {
        saveLong(TEMP_USER_ID, userId);
    }

    /** 获取临时用户ID */
    public long getTempUserId() {
        return getLong(TEMP_USER_ID, -1);
    }



   /** 设置服务器时间差（用服务器时间-手机时间） */
    public void setTimeDiff(long diff) {
        saveLong(SERVER_TIME_DIFFERENCE, diff);
    }
    /** 获取服务器时间差 */
    public long getTimeDiff() {
        return getLong(SERVER_TIME_DIFFERENCE, 0);
    }

    public void setEnterFirst(boolean isFirst) {
        saveBoolean(ENTER_FIRST, isFirst);
    }

    public boolean isEnterFirst() {
        return getBoolean(ENTER_FIRST, true);
    }

    // 设置启动页图片
    public void setUrlSplashImage(String url) {
        saveString(URL_SPLASH_IMAGE, url);
    }
    // 获取启动页图片地址
    public String getUrlSplashImage() {
        return getString(URL_SPLASH_IMAGE);
    }
    // 启动页图片已经下载
    public void setSplashImageHasDownload(boolean hasDownload) {
        saveBoolean(URL_SPLASH_IMAGE_HAS_DOWNLOAD, hasDownload);
    }
    // 启动页图片是否已经下载 false没有，true已经下载了
    public boolean isSplashImageHasDownload() {
        return getBoolean(URL_SPLASH_IMAGE_HAS_DOWNLOAD, false);
    }

}

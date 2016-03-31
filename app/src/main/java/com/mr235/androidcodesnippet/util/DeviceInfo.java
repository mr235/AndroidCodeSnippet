package com.mr235.androidcodesnippet.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * Created by Administrator on 2016/4/1.
 */
public class DeviceInfo {
	private TelephonyManager tm = null;
	private Context mContext = AppSystem.getInstance().getContext();
	private static DeviceInfo ourInstance = new DeviceInfo();

	public static DeviceInfo getInstance() {
		return ourInstance;
	}

	private DeviceInfo() {
	}

	private StringBuilder getUserAgent() {
		StringBuilder sb = new StringBuilder("Android").append(",");
		sb.append(Build.VERSION.RELEASE).append(",");
		sb.append(Build.MODEL).append(",");
		sb.append(tm.getDeviceId()).append(",");

		String subscriberId = tm.getSubscriberId();
		sb.append(subscriberId == null ? "" : subscriberId).append(",");

		CommonUtils.Screen screenSize = CommonUtils.getScreenRealSize();
		sb.append(screenSize.getWidth() + "*" + screenSize.getHeight()).append(",");

		sb.append(CommonUtils.getScreenPhysicalSize()).append(",");
		sb.append(CommonUtils.getDensityDpi()).append(",");

		int netWorkType = CommonUtils.getNetWorkType();

		String netWorkTypeStr = "";
		switch (netWorkType) {
			case CommonUtils.NETWORKTYPE_WAP:
				netWorkTypeStr = "wap";
				break;
			case CommonUtils.NETWORKTYPE_2G:
				netWorkTypeStr = "2g";
				break;
			case CommonUtils.NETWORKTYPE_3G:
				netWorkTypeStr = "3g";
				break;
			case CommonUtils.NETWORKTYPE_WIFI:
				netWorkTypeStr = "wifi";
				break;
			default:
				netWorkTypeStr = "移动网络";
		}
		sb.append(netWorkTypeStr).append(",");

		String channel = CommonUtils.getAppMetaData("UMENG_CHANNEL");
		sb.append(channel == null ? "" : channel).append(",");
		sb.append(Locale.getDefault().getLanguage()).append(",");
		return sb;
	}

	// 显示的版本号
	private String getVersionName() {
		// 获取packagemanager的实例
		PackageManager packageManager = mContext.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			return "";
		}
		String version = packInfo.versionName;
		return version;
	}
}

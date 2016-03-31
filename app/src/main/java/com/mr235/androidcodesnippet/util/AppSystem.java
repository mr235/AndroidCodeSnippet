package com.mr235.androidcodesnippet.util;

import android.content.Context;
import android.text.TextUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/9.
 */
public class AppSystem {
    private static AppSystem ourInstance = new AppSystem();

    public static AppSystem getInstance() {
        return ourInstance;
    }

    private AppSystem() {}

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    private Map<String, String> ips = new HashMap<String, String>();

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }



    private static String getMd5Str(String str) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(str.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
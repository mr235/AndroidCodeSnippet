package com.mr235.androidcodesnippet.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/4/11.
 *  对SharedPrefrence的封装类
 */
public class BaseSP {

    protected String name;
    private final SharedPreferences sp;

    protected BaseSP(String name){
        this.name = name;
        sp = AppSystem.getInstance().getContext().getSharedPreferences(name, Context.MODE_APPEND);
    }

    protected void saveString(String key, String val) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, val);
        editor.commit();
    }

    protected String getString(String key) {
        return sp.getString(key, "");
    }

    protected void saveLong(String key, long val) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, val);
        editor.commit();
    }

    protected long getLong(String key) {
        return sp.getLong(key, 0);
    }

    protected long getLong(String key, long defVal) {
        return sp.getLong(key, defVal);
    }

    protected void saveInt(String key, int val) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, val);
        editor.commit();
    }

    protected long getInt(String key) {
        return sp.getInt(key, 0);
    }

    protected int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    protected void saveBoolean(String key, boolean b) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, b);
        editor.commit();
    }

    protected boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    protected boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

}
package com.mr235.androidcodesnippet.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/4/9.
 */
public class MyToast {

    private static Context context;

    private MyToast() {}

    public static void showShortToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(String text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    private static Toast mToast = null;
    private static void showToast(String text, int duration) {
        if (mToast == null) {
            if (context==null) {
                context = AppSystem.getInstance().getContext();
            }
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }

        mToast.show();
    }
}
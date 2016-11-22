package com.example.keen.netsecnews.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by keen on 2016/6/28.
 */
public class ToastUtil {

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}

package com.cjp.smallappexample.utils;

import android.content.Context;
import android.content.Intent;

/**
 * 类名:com.cjp.smallappexample.utils
 * Created by chenjinpiao on 2017/11/13.
 * 作用：xxxxxxxx
 */

public class Utils {
    public static void startNewView(Context context,Class<?> cl){
        Intent intent=new Intent(context,cl);
        context.startActivity(intent);
    }
}

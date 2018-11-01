package com.ruiduoyi;

import android.app.Application;
import android.content.Context;

import com.ruiduoyi.utils.CrashHandler;

import java.util.Random;

/**
 * Created by DengJf on 2017/7/3.
 */

public class RdyApplication  extends Application {
    public static String delay_time  = ""+(new Random().nextInt(240)+120);

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());

    }


}

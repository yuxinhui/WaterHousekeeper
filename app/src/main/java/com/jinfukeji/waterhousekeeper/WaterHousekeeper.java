package com.jinfukeji.waterhousekeeper;

import android.app.Application;

/**
 * Created by "于志渊"
 * 时间:"11:27"
 * 包名:com.jinfukeji.waterhousekeeper
 * 描述:用于存放全局变量的类
 */

public class WaterHousekeeper extends Application{
    private static WaterHousekeeper intance;
    private static final String URL_MAIN="http://114.55.142.212:8080/yahe/";
    @Override
    public void onCreate() {
        super.onCreate();
        intance=this;
    }

    public static WaterHousekeeper getIntance() {
        return intance;
    }

    public static void setIntance(WaterHousekeeper intance) {
        WaterHousekeeper.intance = intance;
    }

    public static String getUrlMain() {
        return URL_MAIN;
    }
}

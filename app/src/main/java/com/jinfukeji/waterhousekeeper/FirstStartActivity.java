package com.jinfukeji.waterhousekeeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.jinfukeji.waterhousekeeper.yindaotu.YindaotuActivity;

/**
 * Created by "于志渊"
 * 时间:"12:00"
 * 包名:com.jinfukeji.waterhousekeeper
 * 描述:判断是否第一次启动
 */

public class FirstStartActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean mFirst=isFirstEnter(this,this.getClass().getName());
        if (mFirst){
            handler.sendEmptyMessageAtTime(switch_guideactivity,1000);
        }else {
            handler.sendEmptyMessageAtTime(switch_bufferactivity,1000);
        }
    }
    // 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
    private static final String buffer_name="activity_buffer";
    private static final String guide_activity="activity_guide";
    private boolean isFirstEnter(Context context, String s){
        if (context == null || s== null || "".equalsIgnoreCase(s)){
            return false;
        }
        String mResultStr=context.getSharedPreferences(buffer_name,Context.MODE_PRIVATE).getString(guide_activity,"");
        if (mResultStr.equalsIgnoreCase("false")){
            return false;
        }else {
            return true;
        }
    }

    //Handler:跳转至不同页面
    public static final int switch_guideactivity=1000;
    public static final int switch_bufferactivity=1001;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case switch_bufferactivity:
                    startActivity(new Intent(FirstStartActivity.this,BufferActivity.class));
                    finish();
                    break;
                case switch_guideactivity:
                    startActivity(new Intent(FirstStartActivity.this,YindaotuActivity.class));
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}

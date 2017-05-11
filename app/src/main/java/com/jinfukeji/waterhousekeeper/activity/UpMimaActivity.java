package com.jinfukeji.waterhousekeeper.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.WaterHousekeeper;
import com.jinfukeji.waterhousekeeper.been.UpmimaBeen;

import java.util.Objects;

/**
 * Created by "于志渊"
 * 时间:"11:47"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:修改密码界面
 */

public class UpMimaActivity extends AppCompatActivity{
    Dialog dialog;
    private EditText upmima_et,upmima2_et;
    String upmima,upmima2;
    View.OnClickListener listener;
    int xulie_num;
    UpmimaBeen upmimaBeen;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (WaterHousekeeper.getIntance().getSerialNumber() == null){
            Toast.makeText(UpMimaActivity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
        }
        dialog=new Dialog(this,R.style.PeizhiPopupWindow);
        dialog.setContentView(R.layout.activity_upmima);
        SharedPreferences sp=getSharedPreferences("peizhi_xulie", Context.MODE_PRIVATE);
        xulie_num=sp.getInt("xulie_num",0);
        dialog.show();
        listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.upmima_quxiao_btn:
                        finish();
                        break;
                    case R.id.upmima_baocun_btn:
                        initData();
                        if (TextUtils.isEmpty(upmima)){
                            upmima_et.setError("密码不能为空");
                            upmima_et.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(upmima2)){
                            upmima2_et.requestFocus();
                            upmima2_et.setError("确认密码不能为空");
                            return;
                        }
                        if (!(Objects.equals(upmima, upmima2))){
                            upmima_et.requestFocus();
                            Toast.makeText(UpMimaActivity.this,"俩次密码不一样",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (WaterHousekeeper.getIntance().getSerialNumber() == null){
                            Toast.makeText(UpMimaActivity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
                        }
                        String url_upmima=WaterHousekeeper.getUrlMain()+"device/updatePassword?serialNumber="+xulie_num+"&password="+upmima;
                        Log.e("url_upmima",url_upmima);
                        upMima(url_upmima);
                        finish();
                        break;
                }
                dialog.dismiss();
            }
        };
        initView();//初始化控件
        Window window=dialog.getWindow();
        assert window != null;
        window.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams params=window.getAttributes();
        params.width= LinearLayout.LayoutParams.MATCH_PARENT;
        params.gravity= Gravity.BOTTOM;
        window.setAttributes(params);
    }

    //提交数据
    private void upMima(String url_upmima) {
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, url_upmima,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            upmimaBeen=gson.fromJson(s,UpmimaBeen.class);
                            if ("ok".equals(upmimaBeen.getStatus())){
                                Toast.makeText(UpMimaActivity.this,"新密码修改成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(UpMimaActivity.this,"新密码修改失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(UpMimaActivity.this,"请检查网络连接",Toast.LENGTH_SHORT).show();
                Log.e("Upmima_VolleyError", volleyError.getMessage());
            }
        });
        queue.add(request);
    }

    //初始化控件
    private void initView() {
        upmima_et= (EditText) dialog.findViewById(R.id.upmima_et);
        upmima2_et= (EditText) dialog.findViewById(R.id.upmima2_et);
        dialog.findViewById(R.id.upmima_quxiao_btn).setOnClickListener(listener);
        dialog.findViewById(R.id.upmima_baocun_btn).setOnClickListener(listener);
    }

    //获取新填写的密码
    private void initData(){
        upmima=upmima_et.getText().toString();
        upmima2=upmima2_et.getText().toString();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}

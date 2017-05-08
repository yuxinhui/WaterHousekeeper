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
import android.widget.Button;
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
import com.jinfukeji.waterhousekeeper.been.PeiZhiBeen;

/**
 * Created by "于志渊"
 * 时间:"10:12"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:点击配置弹出配置窗口界面
 */

public class PopupWindowActivity extends AppCompatActivity{
    Dialog dialog;
    EditText chanpinhao_et,mima_et;
    Button quxiao_btn,baocun_btn;
    String chanpinhao,mima;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog=new Dialog(this,R.style.PeizhiPopupWindow);
        dialog.setContentView(R.layout.popup_window);
        dialog.show();
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.quxiao_btn:
                        //取消
                        finish();
                        break;
                    case R.id.baocun_btn:
                        initData();
                        if (TextUtils.isEmpty(chanpinhao)){
                            chanpinhao_et.setError("产品序列号不能为空");
                            chanpinhao_et.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(mima)){
                            mima_et.requestFocus();
                            mima_et.setError("密码不能为空");
                            return;
                        }
                        String url_peizhi= WaterHousekeeper.getUrlMain()+"device/addDevice?serialNumber="+chanpinhao+"&password="+mima;
                        Log.e("url_peizhi",url_peizhi);
                        peizhiurl(url_peizhi);
                        finish();
                        break;
                }
                dialog.dismiss();
            }
        };
        initView(listener);
        initData();
        Window window=dialog.getWindow();
        window.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
    }

    //配置序列号的请求
    private void peizhiurl(String url_peizhi) {
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.POST, url_peizhi, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null){
                    Gson gson=new Gson();
                    PeiZhiBeen message=gson.fromJson(s,PeiZhiBeen.class);
                    if ("ok".equals(message.getStatus())){
                        SharedPreferences sp=getSharedPreferences("peizhi_xulie", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putInt("xulie_num", Integer.parseInt(chanpinhao));
                        ed.apply();
                        Toast.makeText(PopupWindowActivity.this,"配置成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(PopupWindowActivity.this,"配置失败",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(PopupWindowActivity.this,"请检查网络连接，配置失败",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    //获取产品号与密码
    private void initData() {
        chanpinhao=chanpinhao_et.getText().toString();
        mima=mima_et.getText().toString();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    //控件初始化
    private void initView(View.OnClickListener listener) {
        chanpinhao_et= (EditText)dialog.findViewById(R.id.chanpinhao_et);
        mima_et= (EditText) dialog.findViewById(R.id.mima_et);
        quxiao_btn= (Button) dialog.findViewById(R.id.quxiao_btn);
        baocun_btn= (Button)dialog.findViewById(R.id.baocun_btn);

        quxiao_btn.setOnClickListener(listener);
        baocun_btn.setOnClickListener(listener);
    }
}

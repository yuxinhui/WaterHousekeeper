package com.jinfukeji.waterhousekeeper.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.jinfukeji.waterhousekeeper.been.GuzhangFankuiBeen;

/**
 * Created by "于志渊"
 * 时间:"10:26"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:故障反馈界面
 */

public class GuzhangFankuiActivity extends AppCompatActivity {
    private EditText lianxi_num,content_et;
    private Button guzhang_tijiao_btn;
    private ImageView fanhui_img;
    String content;
    int xulie;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guzhang);
        SharedPreferences sp=getSharedPreferences("peizhi_xulie", Context.MODE_PRIVATE);
        xulie=sp.getInt("xulie_num",1);
        initView();//控件初始化
        initData();
        initOnclick();//点击事件
    }

    //获取数据
    private void initData() {
        content=content_et.getText().toString();
    }

    //点击事件
    private void initOnclick() {
        fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuzhangFankuiActivity.this,JiqishezhiActivity.class));
                finish();
            }
        });
        guzhang_tijiao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                if (TextUtils.isEmpty(content)){
                    content_et.setError("意见不能为空哦");
                    content_et.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(lianxi_num.getText().toString())){
                    lianxi_num.requestFocus();
                    lianxi_num.setError("联系方式不能为空");
                    return;
                }
                String url_guzhang= WaterHousekeeper.getUrlMain()+"feedback/addFeedback?serialNumber="+xulie+
                        "&content="+content;
                Log.e("url_guzhang",url_guzhang);
                guzhangfankui(url_guzhang);
            }
        });
    }

    private void guzhangfankui(String url_guzhang) {
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.POST, url_guzhang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            GuzhangFankuiBeen fankuiBeen=gson.fromJson(s,GuzhangFankuiBeen.class);
                            if ("ok".equals(fankuiBeen.getStatus())){
                                Toast.makeText(GuzhangFankuiActivity.this,"提交反馈成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(GuzhangFankuiActivity.this,"提交反馈失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(GuzhangFankuiActivity.this,"请检查网络连接，提交反馈失败",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
        lianxi_num= (EditText) this.findViewById(R.id.lianxi_num);
        content_et= (EditText) this.findViewById(R.id.content_et);
        guzhang_tijiao_btn= (Button) this.findViewById(R.id.guzhang_tijiao_btn);
    }
}

package com.jinfukeji.waterhousekeeper.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.jinfukeji.waterhousekeeper.been.LvxinZhuangtaiBeen;

import java.util.Objects;

/**
 * Created by "于志渊"
 * 时间:"17:38"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:滤芯状态界面
 */

public class LvxinActivity extends AppCompatActivity{
    private ImageView fanhui_img;
    TextView lvxin1,lvxin2,lvxin3,lvxin4,lvxin5;
    private LvxinZhuangtaiBeen zhuangtaiBeen;
    String xulie_num;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvxin);
        SharedPreferences sp=getSharedPreferences(WaterHousekeeper.getFilename(), Context.MODE_PRIVATE);
        xulie_num=sp.getString("xulie_num","0");
        SharedPreferences ap=getSharedPreferences("firstnum",Context.MODE_PRIVATE);
        WaterHousekeeper.getIntance().setSerialNumber(ap.getString("waterhousenum",""));
        if (!Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(),xulie_num)){
            Toast.makeText(LvxinActivity.this,"请先配置序列号",Toast.LENGTH_SHORT).show();
        }
        String url_lvxin=WaterHousekeeper.getUrlMain()+"deviceCheck/query?serialNumber="+xulie_num;
        Log.e("url_lvxin",url_lvxin);
        initData(url_lvxin);
        initView();
        initOnclick();
    }

    //请求状态数据
    private void initData(String url_lvxin) {
        if (Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(),xulie_num)){
            RequestQueue queue= Volley.newRequestQueue(this);
            StringRequest request=new StringRequest(Request.Method.GET, url_lvxin,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s != null){
                                Gson gson=new Gson();
                                zhuangtaiBeen=gson.fromJson(s,LvxinZhuangtaiBeen.class);
                                lvxin1.setText(zhuangtaiBeen.getMessage().getFilterStatus1());
                                lvxin2.setText(zhuangtaiBeen.getMessage().getFilterStatus2());
                                lvxin3.setText(zhuangtaiBeen.getMessage().getFilterStatus3());
                                lvxin4.setText(zhuangtaiBeen.getMessage().getFilterStatus4());
                                lvxin5.setText(zhuangtaiBeen.getMessage().getFilterStatus5());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(LvxinActivity.this,"请先检查网络",Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        }
    }

    private void initOnclick() {
        fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("banner",1);
                setResult(11,intent);
                finish();
            }
        });
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
        lvxin1= (TextView) this.findViewById(R.id.lvxin_1);
        lvxin2= (TextView) this.findViewById(R.id.lvxin_2);
        lvxin3= (TextView) this.findViewById(R.id.lvxin_3);
        lvxin4= (TextView) this.findViewById(R.id.lvxin_4);
        lvxin5= (TextView) this.findViewById(R.id.lvxin_5);
    }
}

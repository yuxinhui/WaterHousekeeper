package com.jinfukeji.waterhousekeeper.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.jinfukeji.waterhousekeeper.activity.ChongzhiActivity;
import com.jinfukeji.waterhousekeeper.been.ZhangdanYueBeen;

/**
 * Created by "于志渊"
 * 时间:"9:33"
 * 包名:com.jinfukeji.waterhousekeeper.fragment
 * 描述:我的账单余额界面
 */

public class ZhangdanYueActivity extends android.support.v4.app.Fragment {
    private LinearLayout yue_chongzhi_ll;
    TextView yue_tv;
    String url_zdye,xunlie_num;
    ZhangdanYueBeen yueBeen;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zhangdan_yue,null);
        SharedPreferences sp=getActivity().getSharedPreferences("peizhi_xulie", Context.MODE_PRIVATE);
        xunlie_num=sp.getString("xulie_num","");
        if (WaterHousekeeper.getIntance().getSerialNumber() == null){
            Toast.makeText(getContext(),"请先配置序列号",Toast.LENGTH_LONG).show();
        }
        url_zdye=WaterHousekeeper.getUrlMain()+"device/query?serialNumber="+xunlie_num;
        Log.e("zdye_url",url_zdye);
        initData();
        initView(view);
        initOnclick();
        return view;
    }

    private void initData() {
        if (!(WaterHousekeeper.getIntance().getSerialNumber() == null)){
            RequestQueue queue= Volley.newRequestQueue(getContext());
            StringRequest request=new StringRequest(Request.Method.GET, url_zdye,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s != null){
                                Gson gson=new Gson();
                                yueBeen=gson.fromJson(s,ZhangdanYueBeen.class);
                                if ("ok".equals(yueBeen.getStatus())){

                                }else {
                                    Toast.makeText(getContext(),"还未充值过",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        }
    }

    private void initOnclick() {
        yue_chongzhi_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ChongzhiActivity.class));
                getActivity().finish();
            }
        });
    }

    //初始化控件
    private void initView(View view) {
        yue_chongzhi_ll= (LinearLayout) view.findViewById(R.id.yue_chongzhi_ll);
        yue_tv= (TextView) view.findViewById(R.id.yue_tv);
    }
}

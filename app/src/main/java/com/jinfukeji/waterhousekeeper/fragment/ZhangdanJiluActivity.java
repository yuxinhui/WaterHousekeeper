package com.jinfukeji.waterhousekeeper.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import com.jinfukeji.waterhousekeeper.adapter.ZhangdanJiluAdapter;
import com.jinfukeji.waterhousekeeper.been.ZhangdanJiluBeen;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by "于志渊"
 * 时间:"9:21"
 * 包名:com.jinfukeji.waterhousekeeper.fragment
 * 描述:我的账单充值记录界面
 */

public class ZhangdanJiluActivity extends android.support.v4.app.Fragment {
    String url_zdjl,xulie_num;
    ZhangdanJiluAdapter mJiluAdapter;
    private ArrayList<ZhangdanJiluBeen.MessageBean> messageBeen=new ArrayList<ZhangdanJiluBeen.MessageBean>();
    ZhangdanJiluBeen mJiluBeen=new ZhangdanJiluBeen();
    ListView zhangdan_jilu_lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zhangdan_jilu,null);
        SharedPreferences sp=getActivity().getSharedPreferences(WaterHousekeeper.getFilename(), Context.MODE_PRIVATE);
        xulie_num=sp.getString("xulie_num","0");
        SharedPreferences ap=getActivity().getSharedPreferences("firstnum",Context.MODE_PRIVATE);
        WaterHousekeeper.getIntance().setSerialNumber(ap.getString("waterhousenum",""));
        if (!Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(),xulie_num)){
            Toast.makeText(getContext(),"请先配置序列号",Toast.LENGTH_LONG).show();
        }
        url_zdjl= WaterHousekeeper.getUrlMain()+"recharge/query?serialNumber="+xulie_num;
        Log.e("url_zdjl",url_zdjl);
        initData();
        initView(view);//初始化控件
        return view;
    }

    //初始化控件
    private void initView(View view) {
        zhangdan_jilu_lv= (ListView) view.findViewById(R.id.zhangdan_jilu_lv);
        mJiluAdapter=new ZhangdanJiluAdapter(messageBeen,getContext());
        zhangdan_jilu_lv.setAdapter(mJiluAdapter);
    }

    private void initData() {
        if (Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(),xulie_num)){
            RequestQueue queue= Volley.newRequestQueue(getContext());
            StringRequest request=new StringRequest(Request.Method.POST, url_zdjl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s != null){
                                Gson gson=new Gson();
                                mJiluBeen=gson.fromJson(s,ZhangdanJiluBeen.class);
                                if ("fail".equals(mJiluBeen.getStatus())){
                                    Toast.makeText(getContext(),"目前还没有记录",Toast.LENGTH_SHORT).show();
                                }else {
                                    ZhangdanJiluBeen.MessageBean messageBeen1=mJiluBeen.getMessage();
                                    messageBeen.add(messageBeen1);
                                    mJiluAdapter.notifyDataSetChanged();
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
}

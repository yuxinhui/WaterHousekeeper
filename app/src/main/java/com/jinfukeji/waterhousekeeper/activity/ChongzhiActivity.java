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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.WaterHousekeeper;
import com.jinfukeji.waterhousekeeper.weixin.Constants;
import com.jinfukeji.waterhousekeeper.weixin.HttpUtils;
import com.jinfukeji.waterhousekeeper.weixin.StringCallback;
import com.jinfukeji.waterhousekeeper.weixin.WXPay;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by "于志渊"
 * 时间:"9:35"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:快速充值界面
 */

public class ChongzhiActivity extends AppCompatActivity{
    RelativeLayout fanhui_rl;
    Button lijichongzhi_btn;
    String jine_num,xulie_num;
    TextView zhifu_tv;
    String url_chongzhi;

    private Map<String,String> postData=new HashMap<String, String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
        SharedPreferences sp=getSharedPreferences(WaterHousekeeper.getFilename(), Context.MODE_PRIVATE);
        xulie_num=sp.getString("xulie_num","0");
        SharedPreferences ap=getSharedPreferences("firstnum",Context.MODE_PRIVATE);
        WaterHousekeeper.getIntance().setSerialNumber(ap.getString("waterhousenum",""));
        if (!Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(),xulie_num)){
            Toast.makeText(ChongzhiActivity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
        }
        initView();
        initData();
        initOncliak();
    }

    //点击事件
    private void initOncliak() {
        fanhui_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("banner",3);
                setResult(33,intent);
                finish();
            }
        });
        /**
         * 微信支付
         * 微信支付常见坑
         * 1.微信开放平台的包名和签名是否和本地的一致
         * 2.服务器能拿到prepare_id,还是返回-1，查看调起支付接口时的签名是否计算正确
         * 3.能调起支付，没有返回消息的，请查看自己项目包下是否有（wxapi.WXPayEntryActivity）
         * 4.本地调试时一定要使用正式签名文件进行调试，否则是调不起微信支付窗口的
         * 5.网络上遇到说微信缓存会影响返回-1的，目前没有遇到过
         */
        lijichongzhi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(jine_num)){
                    Toast.makeText(ChongzhiActivity.this,"充值钱数不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(),xulie_num)){
                    Toast.makeText(ChongzhiActivity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
                    return;
                }
                url_chongzhi= WaterHousekeeper.getUrlMain()+"order/pay?cashnum="+jine_num+"&serialNumber="+xulie_num;
                Log.e("url_chongzhi",url_chongzhi);
                postData.put("cashnum",jine_num);
                HttpUtils.httpPost(ChongzhiActivity.this, url_chongzhi, postData, new StringCallback() {
                    @Override
                    public void onError(String request) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.i("wechatpay", response);
                        WXPay wxpay = new WXPay(ChongzhiActivity.this, Constants.APP_ID);
                        wxpay.doPay(response, new WXPay.WXPayResultCallBack() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(ChongzhiActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
                                Log.d("Chongzhi", "支付成功");
                            }

                            @Override
                            public void onError(int error_code) {
                                Toast.makeText(ChongzhiActivity.this,"支付失败",Toast.LENGTH_SHORT).show();
                                Log.d("Chongzhi", "支付失败" + error_code);
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(ChongzhiActivity.this,"支付取消",Toast.LENGTH_SHORT).show();
                                Log.d("Chongzhi", "支付取消");
                            }
                        });
                    }
                });
            }
        });
    }

    private void initData() {
        this.findViewById(R.id.zhongzhi_5481_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChongzhiActivity.this,"该规格暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
        this.findViewById(R.id.zhongzhi_9782_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChongzhiActivity.this,"该规格暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
        this.findViewById(R.id.zhongzhi_12983_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChongzhiActivity.this,"该规格暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
        this.findViewById(R.id.zhongzhi_10881_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jine_num="1088";
                zhifu_tv.setText(jine_num+"元");
            }
        });
        this.findViewById(R.id.zhongzhi_19882_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jine_num="1988";
                zhifu_tv.setText(jine_num+"元");
            }
        });
        this.findViewById(R.id.zhongzhi_25983_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jine_num="2598";
                zhifu_tv.setText(jine_num+"元");
            }
        });
        this.findViewById(R.id.zhongzhi_13881_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChongzhiActivity.this,"该规格暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
        this.findViewById(R.id.zhongzhi_24882_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChongzhiActivity.this,"该规格暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
        this.findViewById(R.id.zhongzhi_32883_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChongzhiActivity.this,"该规格暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //控件初始化
    private void initView() {
        fanhui_rl= (RelativeLayout) this.findViewById(R.id.fanhui_rl);
        lijichongzhi_btn= (Button) this.findViewById(R.id.lijichongzhi_btn);
        zhifu_tv= (TextView) this.findViewById(R.id.zhifu_tv);
    }
}

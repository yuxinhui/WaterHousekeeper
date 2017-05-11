package com.jinfukeji.waterhousekeeper.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.WaterHousekeeper;
import com.jinfukeji.waterhousekeeper.weixin.Constants;
import com.jinfukeji.waterhousekeeper.weixin.MD5;
import com.jinfukeji.waterhousekeeper.weixin.Util;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by "于志渊"
 * 时间:"9:35"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:快速充值界面
 */

public class ChongzhiActivity extends AppCompatActivity{
    ImageView fanhui_img;
    EditText qita_et;
    Button lijichongzhi_btn;
    int xulie_num;
    String jine_num;
    TextView zhifu_tv;
    String url_chongzhi;

    private StringBuffer sb;
    private Map<String,String> resultunifiedorder;
    private PayReq req;
    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
        if (WaterHousekeeper.getIntance().getSerialNumber() == null){
            Toast.makeText(ChongzhiActivity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
        }
        SharedPreferences sp=getSharedPreferences("peizhi_xulie", Context.MODE_PRIVATE);
        xulie_num=sp.getInt("xulie_num",0);
        sb=new StringBuffer();
        req=new PayReq();
        initView();
        initOncliak();
    }

    //点击事件
    private void initOncliak() {
        fanhui_img.setOnClickListener(new View.OnClickListener() {
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
               initData();
                if (TextUtils.isEmpty(jine_num)){
                    qita_et.setError("充值钱数不能为空");
                    qita_et.requestFocus();
                    return;
                }
                if (WaterHousekeeper.getIntance().getSerialNumber() == null){
                    Toast.makeText(ChongzhiActivity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
                }
                String urlString="https://api.mch.weixin.qq.com/pay/unifiedorder";
                url_chongzhi= WaterHousekeeper.getUrlMain()+"order/pay?cashnum="+jine_num+"&serialNumber="+xulie_num;
                Log.e("url_chongzhi",url_chongzhi);
                PrePayIdAsyncTask prePayIdAsyncTask=new PrePayIdAsyncTask();
                prePayIdAsyncTask.execute(urlString);
                genPayReq();//生成签名参数
                sendPayReq();
            }
        });
    }

    @NonNull
    private void initData() {
        jine_num= qita_et.getText().toString();
        zhifu_tv.setText(jine_num+"元");
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
        qita_et= (EditText) this.findViewById(R.id.qita_et);
        lijichongzhi_btn= (Button) this.findViewById(R.id.lijichongzhi_btn);
        zhifu_tv= (TextView) this.findViewById(R.id.zhifu_tv);
    }

    /*
	 * 调起微信支付
	 */
    private void sendPayReq() {


        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
        Log.i(">>>>>", req.partnerId);
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
    private void genPayReq() {

        req.appId = Constants.APP_ID;
        req.partnerId = Constants.MCH_ID;
        if (resultunifiedorder!=null) {
            req.prepayId = resultunifiedorder.get("prepay_id");
            req.packageValue = "prepay_id="+resultunifiedorder.get("prepay_id");
        }
        else {
            Toast.makeText(ChongzhiActivity.this, "prepayid为空", Toast.LENGTH_SHORT).show();
        }
        req.nonceStr = getNonceStr();
        req.timeStamp = String.valueOf(genTimeStamp());


        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

        req.sign = genAppSign(signParams);

        Log.e("Simon", "----"+signParams.toString());

    }
    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

        this.sb.append("sign str\n"+sb.toString()+"\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        Log.e("Simon","----"+appSign);
        return appSign;
    }

    private class PrePayIdAsyncTask extends AsyncTask<String,Void, Map<String, String>>{
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(ChongzhiActivity.this, "提示", "正在提交订单");

        }
        @Override
        protected Map<String, String> doInBackground(String... params) {
            // TODO Auto-generated method stub
            String url= String.format(params[0]);
            String entity=getProductArgs();
            Log.e("Simon",">>>>"+entity);
            byte[] buf= Util.httpPost(url, entity);
            String content = new String(buf);
            Log.e("orion", "----"+content);
            Map<String,String> xml=decodeXml(content);

            return xml;
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (dialog != null) {
                dialog.dismiss();
            }
            resultunifiedorder=result;
        }
    }
    public Map<String,String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName=parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if("xml".equals(nodeName)==false){
                            //实例化student对象
                            xml.put(nodeName,parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("Simon","----"+e.toString());
        }
        return null;

    }
    private String getProductArgs() {
        // TODO Auto-generated method stub
        StringBuffer xml=new StringBuffer();
        try {
            String nonceStr=getNonceStr();
            xml.append("<xml>");
            List<NameValuePair> packageParams=new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid",Constants.APP_ID));
            packageParams.add(new BasicNameValuePair("body", "支付金额"));
            packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", url_chongzhi));//写你们的回调地址
            packageParams.add(new BasicNameValuePair("out_trade_no",genOutTradNo()));
            packageParams.add(new BasicNameValuePair("total_fee", "0.01"));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));
            packageParams.add(new BasicNameValuePair("spbill_create_ip","114.55.142.212:8080"));

            String sign=getPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));
            String xmlString=toXml(packageParams);
            return xmlString;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    //生成订单号,测试用，在客户端生成
    private String genOutTradNo() {
        Random random = new Random();
//		return "dasgfsdg1234"; //订单号写死的话只能支付一次，第二次不能生成订单
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
    //生成随机号，防重发
    private String getNonceStr() {
        // TODO Auto-generated method stub
        Random random=new Random();

        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
    /**
     生成签名
     */

    private String getPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);


        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("Simon",">>>>"+packageSign);
        return packageSign;
    }
    /**
     * 转换成xml
     */
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");


            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");

        Log.e("Simon",">>>>"+sb.toString());
        return sb.toString();
    }
}

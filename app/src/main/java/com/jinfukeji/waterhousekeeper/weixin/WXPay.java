package com.jinfukeji.waterhousekeeper.weixin;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by "于志渊"
 * 时间:"15:29"
 * 包名:com.jinfukeji.waterhousekeeper.weixin
 * 描述:微信支付
 */

public class WXPay {
    private static WXPay mWXPay;
    private IWXAPI mWXApi;
    private String mPayParam;
    private WXPayResultCallBack mCallback;

    public static final int NO_OR_LOW_WX = 1;   //未安装微信或微信版本过低
    public static final int ERROR_PAY_PARAM = 2;  //支付参数错误
    public static final int ERROR_PAY = 3;  //支付失败

    public interface WXPayResultCallBack {
        void onSuccess(); //支付成功
        void onError(int error_code);   //支付失败
        void onCancel();    //支付取消
    }

    public WXPay(Context context, String wx_appid) {
        mWXApi = WXAPIFactory.createWXAPI(context, null);
        mWXApi.registerApp(wx_appid);
    }

    public static void init(Context context, String wx_appid) {
        if(mWXPay == null) {
            mWXPay = new WXPay(context, wx_appid);
        }
    }
    public static WXPay getInstance(){
        return mWXPay;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }
    /**
     * 发起微信支付
     */
    public void doPay(String pay_param, WXPayResultCallBack callback) {
        mPayParam = pay_param;
        mCallback = callback;

        if(!check()) {
            if(mCallback != null) {
                mCallback.onError(NO_OR_LOW_WX);
            }
            return;
        }

        JSONObject param = null;
        try {
            param = new JSONObject(mPayParam).getJSONObject("msg");
        } catch (JSONException e) {
            e.printStackTrace();
            if(mCallback != null) {
                mCallback.onError(ERROR_PAY_PARAM);
            }
            return;
        }
        try{
            if(param == null || TextUtils.isEmpty(param.getString("appid")) || TextUtils.isEmpty(param.getString("partnerid"))
                    || TextUtils.isEmpty(param.getString("prepayid")) || TextUtils.isEmpty(param.getString("package")) ||
                    TextUtils.isEmpty(param.getString("noncestr")) || TextUtils.isEmpty(param.getString("timestamp")) ||
                    TextUtils.isEmpty(param.getString("sign"))) {
                if(mCallback != null) {
                    mCallback.onError(ERROR_PAY_PARAM);
                }
                return;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        PayReq req = new PayReq();

        try {
            req.appId = param.getString("appid");
            req.partnerId = param.getString("partnerid");
            req.prepayId = param.getString("prepayid");
            req.packageValue = param.getString("package");
            req.nonceStr = param.getString("noncestr");
            req.timeStamp = param.getString("timestamp");
            req.sign = param.getString("sign");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mWXApi.sendReq(req);
    }

    //支付回调响应
    public void onResp(int error_code) {
        if(mCallback == null) {
            return;
        }

        if(error_code == 0) {   //成功
            mCallback.onSuccess();
        } else if(error_code == -1) {   //错误
            mCallback.onError(ERROR_PAY);
        } else if(error_code == -2) {   //取消
            mCallback.onCancel();
        }

        mCallback = null;
    }

    //检测是否支持微信支付
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}

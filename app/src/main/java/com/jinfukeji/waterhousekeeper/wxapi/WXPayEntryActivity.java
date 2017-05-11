package com.jinfukeji.waterhousekeeper.wxapi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.jinfukeji.waterhousekeeper.weixin.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by "于志渊"
 * 时间:"14:31"
 * 包名:com.jinfukeji.waterhousekeeper.wxapi
 * 描述:调起微信支付完成支付（或取消或失败）后，再回到你的App时会调用的一个页面。
 * 页面的布局可以是你自定义的布局(一般做法是直接注释掉这个布局)
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler{
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d("WXPayEntryActivity", "onPayFinish, errCode = " + baseResp.errCode);
        Toast.makeText(WXPayEntryActivity.this, baseResp.errCode, Toast.LENGTH_SHORT).show();
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("微信支付结果" +String.valueOf(baseResp.errCode));
            builder.show();
            finish();
        }
    }
}

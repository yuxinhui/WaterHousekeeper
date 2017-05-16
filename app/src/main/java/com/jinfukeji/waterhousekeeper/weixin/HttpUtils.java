package com.jinfukeji.waterhousekeeper.weixin;

import android.content.Context;

import java.util.Map;

/**
 * Created by "于志渊"
 * 时间:"15:26"
 * 包名:com.jinfukeji.waterhousekeeper.weixin
 * 描述:使用volley进行网络访问
 */

public class HttpUtils {
    /**
     * post请求
     * @param url             地址
     * @param map             参数
     * @param mStringCallBack 回调
     */
    public static void httpPost(Context context, String url, Map<String, String> map, StringCallback mStringCallBack) {
        HttpLoader.getInstance(context).postRequest(url, map, mStringCallBack);
    }

    public static void httpGet(Context context,String url, StringCallback mStringCallBack){
        HttpLoader.getInstance(context).getRequest(url,mStringCallBack);
    }
}

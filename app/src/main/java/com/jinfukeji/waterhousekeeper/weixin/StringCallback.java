package com.jinfukeji.waterhousekeeper.weixin;

/**
 * Created by "于志渊"
 * 时间:"15:27"
 * 包名:com.jinfukeji.waterhousekeeper.weixin
 * 描述:
 */

public abstract class StringCallback {
    public abstract void onError(String request);

    public abstract void onResponse(String response);
}

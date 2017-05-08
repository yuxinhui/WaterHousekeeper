package com.jinfukeji.waterhousekeeper.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by "于志渊"
 * 时间:"9:50"
 * 包名:com.jinfukeji.waterhousekeeper.utils
 * 描述:edittext去边框加下划线
 */

public class EdittextActivity extends android.support.v7.widget.AppCompatEditText{
    Paint bi;
    public EdittextActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置画笔的属性
        bi=new Paint();
        bi.setStyle(Paint.Style.STROKE);
        //可以自定义画笔的颜色，我这里设置成灰色
        bi.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,this.getHeight()-2,this.getWidth()-2,this.getHeight()-2,bi);
    }
}

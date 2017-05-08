package com.jinfukeji.waterhousekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jinfukeji.waterhousekeeper.R;

/**
 * Created by "于志渊"
 * 时间:"9:35"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:快速充值界面
 */

public class ChongzhiActivity extends AppCompatActivity{
    ImageView fanhui_img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
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
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
    }
}
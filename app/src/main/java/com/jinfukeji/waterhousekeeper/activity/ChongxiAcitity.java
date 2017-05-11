package com.jinfukeji.waterhousekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.WaterHousekeeper;

/**
 * Created by "于志渊"
 * 时间:"14:24"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:冲洗界面
 */

public class ChongxiAcitity extends AppCompatActivity{
    ImageView fanhui_img;
    Button chongxi_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongxi);
        initView();//控件初始化
        initOnclick();//跳转事件
    }

    //跳转事件
    private void initOnclick() {
        fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("index",1);
                setResult(00,intent);
                finish();
            }
        });
        chongxi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (WaterHousekeeper.getIntance().getSerialNumber() == null){
                    Toast.makeText(ChongxiAcitity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
        chongxi_btn= (Button) this.findViewById(R.id.chongxi_btn);
    }
}

package com.jinfukeji.waterhousekeeper.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.WaterHousekeeper;

/**
 * Created by "于志渊"
 * 时间:"10:09"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:机器设置界面
 */

public class JiqishezhiActivity extends AppCompatActivity {
    ImageView fanhui_img;
    RelativeLayout peizhi_rl, aboutus_rl, fuwuphone_rl, jiqishezhi_guzhang_rl;
    TextView chanpinxuliehao_tv;
    int xulie_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiqishezhi);
        initView();
        initOnclick();
        SharedPreferences sp = getSharedPreferences("peizhi_xulie", Context.MODE_PRIVATE);
        xulie_num = sp.getInt("xulie_num", 0);

    }

    //点击事件
    private void initOnclick() {
        fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("banner", 5);
                setResult(55, intent);
                finish();
            }
        });
        fuwuphone_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL); Uri data = Uri.parse("tel:" + "4009201210");
                    intent.setData(data);
                    startActivity(intent);
                }
                /*AlertDialog.Builder builder = new AlertDialog.Builder(JiqishezhiActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否拨打服务热线");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                                + "10086"));
                        if (ActivityCompat.checkSelfPermission(JiqishezhiActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intent);


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();*/
            }
        });
        peizhi_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(JiqishezhiActivity.this,PopupWindowActivity.class);
                startActivity(intent);
            }
        });
        aboutus_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiqishezhiActivity.this,AboutUsActivity.class));
                finish();
            }
        });
        jiqishezhi_guzhang_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiqishezhiActivity.this,GuzhangFankuiActivity.class));
                finish();
            }
        });
        findViewById(R.id.jiqishezhi_wifi_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                if (Build.VERSION.SDK_INT >= 19){
                    intent.setClassName("com.android.settings","com.android.settings.Settings$WifiSettingsActivity");
                }else {
                    intent.setClassName("com.android.settings","com.android.settings.wifi.WifiSettings");
                }
                startActivity(intent);
            }
        });
        findViewById(R.id.jiqishezhi_upmima_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiqishezhiActivity.this,UpMimaActivity.class));
            }
        });
        findViewById(R.id.jiqishezhi_bangzhu_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JiqishezhiActivity.this,"暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
        peizhi_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_peizhi_rl);
        aboutus_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_aboutus_rl);
        fuwuphone_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_fuwunum_rl);
        jiqishezhi_guzhang_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_guzhang_rl);
        chanpinxuliehao_tv= (TextView) this.findViewById(R.id.chanpinxuliehao_tv);
        if (WaterHousekeeper.getIntance().getSerialNumber() != null){
            chanpinxuliehao_tv.setText(WaterHousekeeper.getIntance().getSerialNumber());
        }
    }
}

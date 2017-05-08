package com.jinfukeji.waterhousekeeper.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jinfukeji.waterhousekeeper.R;

/**
 * Created by "于志渊"
 * 时间:"10:09"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:机器设置界面
 */

public class JiqishezhiActivity extends AppCompatActivity{
    ImageView fanhui_img;
    RelativeLayout peizhi_rl, aboutus_rl, fuwuphone_rl,jiqishezhi_guzhang_rl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiqishezhi);
        initView();
        initOnclick();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(JiqishezhiActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否拨打服务热线");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        call("13552454204");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }

            private void call(String phone) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(JiqishezhiActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
        peizhi_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiqishezhiActivity.this,PopupWindowActivity.class));
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
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
        peizhi_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_peizhi_rl);
        aboutus_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_aboutus_rl);
        fuwuphone_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_fuwunum_rl);
        jiqishezhi_guzhang_rl= (RelativeLayout) this.findViewById(R.id.jiqishezhi_guzhang_rl);
    }
}

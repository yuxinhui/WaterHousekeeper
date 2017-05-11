package com.jinfukeji.waterhousekeeper.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(JiqishezhiActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否拨打服务热线");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (ActivityCompat.checkSelfPermission(JiqishezhiActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(JiqishezhiActivity.this,
                                    Manifest.permission.CALL_PHONE)) {
                                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                                // 弹窗需要解释为何需要该权限，再次请求授权
                                Toast.makeText(JiqishezhiActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                                // 帮跳转到该应用的设置界面，让用户手动授权
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            } else {
                                // 不需要解释为何需要该权限，直接请求授权
                                ActivityCompat.requestPermissions(JiqishezhiActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE},
                                        1);
                            }
                        } else {
                            callPhone();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        peizhi_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JiqishezhiActivity.this, PopupWindowActivity.class);
                startActivity(intent);
            }
        });
        aboutus_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiqishezhiActivity.this, AboutUsActivity.class));
                finish();
            }
        });
        jiqishezhi_guzhang_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiqishezhiActivity.this, GuzhangFankuiActivity.class));
                finish();
            }
        });
        findViewById(R.id.jiqishezhi_wifi_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT >= 19) {
                    intent.setClassName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
                } else {
                    intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                }
                startActivity(intent);
            }
        });
        findViewById(R.id.jiqishezhi_upmima_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiqishezhiActivity.this, UpMimaActivity.class));
            }
        });
        findViewById(R.id.jiqishezhi_bangzhu_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JiqishezhiActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                + "4009932199"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    callPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }
}

package com.jinfukeji.waterhousekeeper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jinfukeji.waterhousekeeper.activity.ChongxiAcitity;
import com.jinfukeji.waterhousekeeper.been.DeviceCheckBeen;
import com.jinfukeji.waterhousekeeper.fragment.MenuLeftFragment;
import com.nineoldandroids.view.ViewHelper;

import java.util.Objects;

/**
 *系统主界面
 */
public class MainActivity extends AppCompatActivity {
    ImageView kaiguan_img,shuaxin_img,jiqizhishui_img,jiqishuiman_img,jiqiqueshui_img,jiqiok_img,jiqiguzhang_img,jiqiyichang_img;
    TextView jiqizhishui_txt,jiqishuiman_txt,jiqiqueshui_txt,jiqiok_txt,jiqiguzhang_txt,jiqiyichang_txt;
    Button chongxi_btn;
    private DrawerLayout main_dl;
    private FragmentManager fm;
    MenuLeftFragment fg_left_menu;
    String url_deviceCheck,xulie_num;
    DeviceCheckBeen checkBeen=new DeviceCheckBeen();
    int blue=0xFF3FA8F4,hui=0xFF828282;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences ap=getSharedPreferences("firstnum",Context.MODE_PRIVATE);
        WaterHousekeeper.getIntance().setSerialNumber(ap.getString("waterhousenum",""));
        fm=getSupportFragmentManager();
        initView();//控件初始化
        initClick();//点击事件
        if (WaterHousekeeper.getIntance().getSerialNumber() != null){
            deviceCheck();//设备检测
        }
    }

    //设备检测
    public void deviceCheck() {
        SharedPreferences sp=getSharedPreferences(WaterHousekeeper.getFilename(), Context.MODE_PRIVATE);
        xulie_num=sp.getString("xulie_num","0");
        Log.e("start",xulie_num);
        url_deviceCheck=WaterHousekeeper.getUrlMain()+"deviceCheck/query?serialNumber="+xulie_num;
        Log.e("url_deviceCheck",url_deviceCheck);
        clearImgAndTxt();
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, url_deviceCheck,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            checkBeen=gson.fromJson(s,DeviceCheckBeen.class);
                            if ("ok".equals(checkBeen.getStatus())){
                                if ("0".equals(checkBeen.getMessage().getDeviceStatus())){
                                    jiqizhishui_img.setImageResource(R.mipmap.jiqizhishui_cai);
                                    jiqizhishui_txt.setTextColor(blue);
                                }
                                if ("1".equals(checkBeen.getMessage().getDeviceStatus())){
                                    clearImgAndTxt();
                                    jiqishuiman_img.setImageResource(R.mipmap.jiqishuiman_cai);
                                    jiqishuiman_txt.setTextColor(blue);
                                }
                                if ("2".equals(checkBeen.getMessage().getDeviceStatus())){
                                    clearImgAndTxt();
                                    jiqiqueshui_img.setImageResource(R.mipmap.jiqiqueshui_cai);
                                    jiqiqueshui_txt.setTextColor(blue);
                                }
                                if ("3".equals(checkBeen.getMessage().getDeviceStatus())){
                                    clearImgAndTxt();
                                    jiqiok_img.setImageResource(R.mipmap.jiqiok_cai);
                                    jiqiok_txt.setTextColor(blue);
                                }
                                if ("4".equals(checkBeen.getMessage().getDeviceStatus())){
                                    clearImgAndTxt();
                                    jiqiguzhang_img.setImageResource(R.mipmap.jiqiguzhang_cai);
                                    jiqiguzhang_txt.setTextColor(blue);
                                }
                                if ("5".equals(checkBeen.getMessage().getDeviceStatus())){
                                    clearImgAndTxt();
                                    jiqiyichang_img.setImageResource(R.mipmap.jiqiyicahng_cai);
                                    jiqiyichang_txt.setTextColor(blue);
                                }
                            }else {
                                Toast.makeText(MainActivity.this,"设备号不存在,请配置序列号",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,"请检查网络连接",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    //初始化图片与文字
    private void clearImgAndTxt() {
        jiqizhishui_txt.setTextColor(hui);
        jiqishuiman_txt.setTextColor(hui);
        jiqiqueshui_txt.setTextColor(hui);
        jiqiok_txt.setTextColor(hui);
        jiqiguzhang_txt.setTextColor(hui);
        jiqiyichang_txt.setTextColor(hui);

        jiqizhishui_img.setImageResource(R.mipmap.jiqizhishui_hui);
        jiqishuiman_img.setImageResource(R.mipmap.jiqishuiman_hui);
        jiqiqueshui_img.setImageResource(R.mipmap.jiqiqueshui_hui);
        jiqiok_img.setImageResource(R.mipmap.jiqiok_hui);
        jiqiguzhang_img.setImageResource(R.mipmap.jiqiguzhang_hui);
        jiqiyichang_img.setImageResource(R.mipmap.jiqiyichang_hui);
    }

    //控件初始化
    private void initView() {
        kaiguan_img= (ImageView) this.findViewById(R.id.kaiguan_img);
        shuaxin_img= (ImageView) this.findViewById(R.id.shuaxin_img);
        chongxi_btn= (Button) this.findViewById(R.id.chongxi_btn);
        main_dl= (DrawerLayout) this.findViewById(R.id.main_dl);
        fg_left_menu=(MenuLeftFragment)fm.findFragmentById(R.id.left_menu);
        jiqizhishui_img= (ImageView) this.findViewById(R.id.jiqizhishui_img);
        jiqishuiman_img= (ImageView) this.findViewById(R.id.jiqishuiman_img);
        jiqiqueshui_img= (ImageView) this.findViewById(R.id.jiqiqueshui_img);
        jiqiok_img= (ImageView) this.findViewById(R.id.jiqiok_img);
        jiqiguzhang_img= (ImageView) this.findViewById(R.id.jiqiguzhang_img);
        jiqiyichang_img= (ImageView) this.findViewById(R.id.jiqiyichang_img);

        jiqizhishui_txt= (TextView) this.findViewById(R.id.jiqizhishui_txt);
        jiqishuiman_txt= (TextView) this.findViewById(R.id.jiqishuiman_txt);
        jiqiqueshui_txt= (TextView) this.findViewById(R.id.jiqiqueshui_txt);
        jiqiok_txt= (TextView) this.findViewById(R.id.jiqiok_txt);
        jiqiyichang_txt= (TextView) this.findViewById(R.id.jiqiyichang_txt);
        jiqiguzhang_txt= (TextView) this.findViewById(R.id.jiqiguzhang_txt);
    }

    //点击事件
    private void initClick() {
        chongxi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_chongxi=new Intent(MainActivity.this,ChongxiAcitity.class);
                startActivity(intent_chongxi);
            }
        });
        main_dl.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = main_dl.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                if (drawerView.getTag().equals("LEFT")){
                    float leftScale = 1 - 0.3f * scale;
                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }else {
                    ViewHelper.setTranslationX(mContent,
                            -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                main_dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        fg_left_menu.setDrawerLayout(main_dl);
        kaiguan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(), xulie_num)){
                    Toast.makeText(MainActivity.this,"请先配置序列号",Toast.LENGTH_SHORT).show();
                }
            }
        });
        shuaxin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(WaterHousekeeper.getIntance().getSerialNumber(), xulie_num)){
                    Toast.makeText(MainActivity.this,"请先配置序列号",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 00:
                String message=data.getExtras().getString("index");
                break;
            default:
                break;
        }
    }

    //双击退出程序
    private long ExitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-ExitTime > 2000){
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                ExitTime=System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.jinfukeji.waterhousekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jinfukeji.waterhousekeeper.activity.ChongxiAcitity;
import com.jinfukeji.waterhousekeeper.fragment.MenuLeftFragment;
import com.nineoldandroids.view.ViewHelper;

/**
 *系统主界面
 */
public class MainActivity extends AppCompatActivity {
    ImageView kaiguan_img,shuaxin_img;
    Button chongxi_btn;
    private DrawerLayout main_dl;
    private FragmentManager fm;
    MenuLeftFragment fg_left_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (WaterHousekeeper.getIntance().getSerialNumber() == null){
            Toast.makeText(MainActivity.this,"请先配置序列号",Toast.LENGTH_LONG).show();
        }
        fm=getSupportFragmentManager();
        initView();//控件初始化
        initClick();//点击事件
    }

    //控件初始化
    private void initView() {
        kaiguan_img= (ImageView) this.findViewById(R.id.kaiguan_img);
        shuaxin_img= (ImageView) this.findViewById(R.id.shuaxin_img);
        chongxi_btn= (Button) this.findViewById(R.id.chongxi_btn);
        main_dl= (DrawerLayout) this.findViewById(R.id.main_dl);
        fg_left_menu=(MenuLeftFragment)fm.findFragmentById(R.id.left_menu);
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
                if (WaterHousekeeper.getIntance().getSerialNumber() == null){
                    Toast.makeText(MainActivity.this,"请先配置序列号",Toast.LENGTH_SHORT).show();
                }
            }
        });
        shuaxin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (WaterHousekeeper.getIntance().getSerialNumber() == null){
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

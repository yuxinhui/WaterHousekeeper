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

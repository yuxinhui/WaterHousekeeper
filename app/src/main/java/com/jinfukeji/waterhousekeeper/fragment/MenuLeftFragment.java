package com.jinfukeji.waterhousekeeper.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.activity.ChongzhiActivity;
import com.jinfukeji.waterhousekeeper.activity.JiqishezhiActivity;
import com.jinfukeji.waterhousekeeper.activity.LvxinActivity;
import com.jinfukeji.waterhousekeeper.activity.MyshezhiActivity;
import com.jinfukeji.waterhousekeeper.activity.MyzhangdanActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by "于志渊"
 * 时间:"15:27"
 * 包名:com.jinfukeji.waterhousekeeper.fragment
 * 描述:左侧侧滑菜单栏
 */

public class MenuLeftFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private LinearLayout leftmenu_lxzt_ll,leftmenu_myzd_ll,leftmenu_mysz_ll,leftmenu_jiqisz_ll,leftmenu_kscz_ll,leftmenu_yjfx_ll;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_left_menu,container,false);
        ShareSDK.initSDK(getContext());
        initView(view);//初始化控件
        initClick();//点击事件
        return view;
    }

    //点击事件
    private void initClick() {
        leftmenu_lxzt_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(),LvxinActivity.class));
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        leftmenu_myzd_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(),MyzhangdanActivity.class));
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        leftmenu_kscz_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(),ChongzhiActivity.class));
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        leftmenu_mysz_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(),MyshezhiActivity.class));
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        leftmenu_jiqisz_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(),JiqishezhiActivity.class));
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        leftmenu_yjfx_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });
    }

    //初始化控件
    private void initView(View view) {
        leftmenu_lxzt_ll= (LinearLayout) view.findViewById(R.id.leftmenu_lxzt_ll);
        leftmenu_myzd_ll= (LinearLayout) view.findViewById(R.id.leftmenu_myzd_ll);
        leftmenu_mysz_ll= (LinearLayout) view.findViewById(R.id.leftmenu_mysz_ll);
        leftmenu_jiqisz_ll= (LinearLayout) view.findViewById(R.id.leftmenu_jiqisz_ll);
        leftmenu_kscz_ll= (LinearLayout) view.findViewById(R.id.leftmenu_kscz_ll);
        leftmenu_yjfx_ll= (LinearLayout) view.findViewById(R.id.leftmenu_yjfx_ll);
    }

    //暴露给Activity，用于传入DrawerLayout，因为点击后想关掉DrawerLayout
    public void setDrawerLayout(DrawerLayout drawer_layout){
        this.drawerLayout = drawer_layout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 11:
                String s=data.getExtras().getString("banner");
                break;
            case 22:
                String s1=data.getExtras().getString("banner");
                break;
            case 33:
                String s2=data.getExtras().getString("banner");
                break;
            case 44:
                String s3=data.getExtras().getString("banner");
                break;
            case 55:
                String s4=data.getExtras().getString("banner");
                break;
            default:
                break;
        }
    }

    //分享的share
    private void showShare() {
        //ShareSDK初始化
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("畅饮360");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.yahehb.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("健康水管家您身边的净水保镖");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        Resources r=getActivity().getResources();
        InputStream is=r.openRawResource(R.drawable.wechat);
        BitmapDrawable bmpDraw=new BitmapDrawable(is);
        Bitmap bmp = bmpDraw.getBitmap();
        String path=getContext().getFilesDir()+ File.separator+"huai.png";
        try {
            OutputStream os=new FileOutputStream(path);
            bmp.compress(Bitmap.CompressFormat.PNG,100,os);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        oks.setImagePath(path);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.yahehb.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.yahehb.com");
        // 启动分享GUI
        oks.show(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止shareSDK
        ShareSDK.stopSDK(getActivity());
    }
}

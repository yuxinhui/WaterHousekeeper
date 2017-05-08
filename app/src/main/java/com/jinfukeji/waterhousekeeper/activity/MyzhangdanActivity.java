package com.jinfukeji.waterhousekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.fragment.NullActivity;
import com.jinfukeji.waterhousekeeper.fragment.ZhangdanJiluActivity;
import com.jinfukeji.waterhousekeeper.fragment.ZhangdanYueActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"9:11"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:我的账单界面
 */

public class MyzhangdanActivity extends AppCompatActivity {
    private RadioGroup zhangdan_rg;
    private RadioButton zhangdan_jilu_rb,zhangdan_yue_rb;
    HorizontalScrollView zhangdan_hsl;//上面的水平滚动控件
    private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离
    private ViewPager zhangdan_viewpager;//下方的可横向拖动的控件
    ArrayList<Fragment> mActivity;//用来存放下方滚动的layout
    ImageView fanhui_img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myzhangdan);
        initView();
        initOncliak();
        initVariable();//添加下方viewpage界面
        zhangdan_jilu_rb.setChecked(true);
        zhangdan_viewpager.setCurrentItem(1);
        mCurrentCheckedRadioLeft=getCurrentCheckedRadioLeft();
    }

    //添加下方viewpage界面
    private void initVariable() {
        mActivity=new ArrayList<Fragment>();
        mActivity.add(new NullActivity());
        mActivity.add(new ZhangdanJiluActivity());
        mActivity.add(new ZhangdanYueActivity());
        mActivity.add(new NullActivity());

        zhangdan_viewpager.setAdapter(new MyZhangdanAdapter(getSupportFragmentManager(),mActivity));
    }

    //获得当前被选中的RadioButton距离左侧的距离
    private float getCurrentCheckedRadioLeft() {
        if (zhangdan_jilu_rb.isChecked()){
            return getResources().getDimension(R.dimen.yi);
        }else if (zhangdan_yue_rb.isChecked()){
            return getResources().getDimension(R.dimen.er);
        }
        return 0f;
    }

    //点击事件
    private void initOncliak() {
        fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("banner",2);
                setResult(22,intent);
                finish();
            }
        });
        zhangdan_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //RadioGroup点击CheckedChanged监听
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                AnimationSet mAnimationSet=new AnimationSet(true);
                TranslateAnimation mTranslateAnimation;
                selectPiature();
                if (i==R.id.zhangdan_jilu_rb){
                    mTranslateAnimation=new TranslateAnimation(mCurrentCheckedRadioLeft,R.dimen.zhangdan_jilu,0f,0f);
                    mAnimationSet.addAnimation(mTranslateAnimation);
                    mAnimationSet.setFillBefore(false);
                    mAnimationSet.setFillAfter(true);
                    mAnimationSet.setDuration(100);

                    zhangdan_jilu_rb.setBackgroundResource(R.drawable.zhangdan_jilu_zhong);
                    zhangdan_viewpager.setCurrentItem(1);//让下方ViewPager跟随上面的HorizontalScrollView切换
                }else if (i== R.id.zhangdan_yue_rb){
                    mTranslateAnimation=new TranslateAnimation(mCurrentCheckedRadioLeft,R.dimen.zhangdan_yue,0f,0f);
                    mAnimationSet.addAnimation(mTranslateAnimation);
                    mAnimationSet.setFillBefore(false);
                    mAnimationSet.setFillAfter(true);
                    mAnimationSet.setDuration(100);

                    zhangdan_yue_rb.setBackgroundResource(R.drawable.zhangdan_yue_zhong);
                    zhangdan_viewpager.setCurrentItem(2);
                }
                mCurrentCheckedRadioLeft=getCurrentCheckedRadioLeft();
                zhangdan_hsl.smoothScrollTo((int)mCurrentCheckedRadioLeft-(int)R.dimen.zhangdan_yue,0);
            }
        });
        zhangdan_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //ViewPager的PageChangeListener(页面改变的监听器)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    zhangdan_viewpager.setCurrentItem(1);
                }else if (position == 1){
                    zhangdan_jilu_rb.performClick();
                }else if (position == 2){
                    zhangdan_yue_rb.performClick();
                }else if (position == 3){
                    zhangdan_viewpager.setCurrentItem(2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //控件初始化
    private void initView() {
        fanhui_img= (ImageView) this.findViewById(R.id.fanhui_img);
        zhangdan_rg= (RadioGroup) this.findViewById(R.id.zhangdan_rg);
        zhangdan_jilu_rb= (RadioButton) this.findViewById(R.id.zhangdan_jilu_rb);
        zhangdan_yue_rb= (RadioButton) this.findViewById(R.id.zhangdan_yue_rb);
        zhangdan_hsl= (HorizontalScrollView) this.findViewById(R.id.zhangdan_hsl);
        zhangdan_viewpager= (ViewPager) this.findViewById(R.id.zhangdan_viewpager);
    }

    //初始化俩张图片
    private void selectPiature(){
        zhangdan_jilu_rb.setBackgroundResource(R.drawable.zhangdan_jilu_wei);
        zhangdan_yue_rb.setBackgroundResource(R.drawable.zhangdan_yue_wei);
    }

    private class MyZhangdanAdapter extends FragmentPagerAdapter {
        List<Fragment> activityList;

        public MyZhangdanAdapter(FragmentManager fm, List<Fragment> activityList) {
            super(fm);
            this.activityList = activityList;
        }

        @Override
        public int getCount() {
            return activityList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return activityList.get(position);
        }
    }
}

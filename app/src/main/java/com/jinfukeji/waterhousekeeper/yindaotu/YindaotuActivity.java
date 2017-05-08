package com.jinfukeji.waterhousekeeper.yindaotu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfukeji.waterhousekeeper.BufferActivity;
import com.jinfukeji.waterhousekeeper.R;

/**
 * Created by "于志渊"
 * 时间:"13:38"
 * 包名:com.jinfukeji.waterhousekeeper.yindaotu
 * 描述:滑动进入主界面的引导图
 */

public class YindaotuActivity extends AppCompatActivity{
    private int[] images =new int[]{R.mipmap.yindaoye_01,R.mipmap.yindaoye_02,R.mipmap.yindaoye_03};
    private ImageAdapter imageAdapter=new ImageAdapter();
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        viewPager = (ViewPager) findViewById(R.id.view_vp);
        viewPager.setAdapter(imageAdapter);
        viewPager.addOnPageChangeListener(new ViewPagerOnPageChangeListener());
    }

    private class ViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {
        int currPosition = 0; // 当前滑动到了哪一页
        boolean canJump = false;
        boolean canLeft = true;

        boolean isObjAnmatitor = true;
        boolean isObjAnmatitor2 = false;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == (images.length-1)){
                if (positionOffset > 0.05){
                    canJump = true;
                    if (imageAdapter.arrowImage != null && imageAdapter.slideText != null){
                        if (isObjAnmatitor){
                            isObjAnmatitor = false;
                            ObjectAnimator animator=ObjectAnimator.ofFloat(imageAdapter.arrowImage,"rotation", 0f, 180f);
                            animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageAdapter.slideText.setText("");
                                    isObjAnmatitor2 = true;
                                }
                            });
                            animator.setDuration(500).start();
                        }
                    }
                }else if (positionOffset <= 0.05 && positionOffset > 0){
                    canJump = false;
                    if (imageAdapter.arrowImage != null && imageAdapter.slideText != null){
                        if (isObjAnmatitor2){
                            isObjAnmatitor2 = false;
                            ObjectAnimator animator=ObjectAnimator.ofFloat(imageAdapter.arrowImage,"rotation",180f, 360f);
                            animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageAdapter.slideText.setText("");
                                    isObjAnmatitor = true;
                                }
                            });
                            animator.setDuration(500).start();
                        }
                    }
                }
                canLeft = false;
            }else {
                canLeft = true;
            }
        }

        @Override
        public void onPageSelected(int position) {
            currPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (currPosition == (images.length-1) && !canLeft){
                if (state == ViewPager.SCROLL_STATE_SETTLING){
                    if (canJump){
                        setGuide();
                        //跳转
                        startActivity(new Intent(YindaotuActivity.this,BufferActivity.class));
                        finish();
                    }
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            // 在handler里调用setCurrentItem才有效
                            viewPager.setCurrentItem(images.length-1);
                        }
                    });
                }
            }
        }
    }

    private static final String SHAREDPREFERENCES_NAME="activity_buffer";
    private static final String KEY_GUIDE_ACTIVITY ="activity_guide";
    private void setGuide() {
        SharedPreferences settings=getSharedPreferences(SHAREDPREFERENCES_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString(KEY_GUIDE_ACTIVITY,"false");
        editor.commit();
    }

    //引导图适配器
    public class ImageAdapter extends PagerAdapter {
        private TextView slideText;
        private ImageView arrowImage;

        @Override
        public int getCount() {
            return images.length+1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position < images.length){
                ImageView imageView = new ImageView(YindaotuActivity.this);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(300));
                imageView.setLayoutParams(lp);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(images[position]);

                container.addView(imageView);
                return imageView;
            }else {
                View hintView = LayoutInflater.from(container.getContext()).inflate(R.layout.more_view, container, false);

                slideText = (TextView) hintView.findViewById(R.id.tishi_txt);
                arrowImage = (ImageView) hintView.findViewById(R.id.iv_img);

                container.addView(hintView);
                return hintView;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    private int dip2px(float dipValue) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dipValue * density + 0.5f);
    }
}

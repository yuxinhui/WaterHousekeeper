package com.jinfukeji.waterhousekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.jinfukeji.waterhousekeeper.R;

/**
 * Created by "于志渊"
 * 时间:"10:20"
 * 包名:com.jinfukeji.waterhousekeeper.activity
 * 描述:关于我们界面
 */

public class AboutUsActivity extends AppCompatActivity{
    private LinearLayout fanhui_ll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        fanhui_ll= (LinearLayout) this.findViewById(R.id.about_fanhui_ll);
        fanhui_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this,JiqishezhiActivity.class));
                finish();
            }
        });
    }
}

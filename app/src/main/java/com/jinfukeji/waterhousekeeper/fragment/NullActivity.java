package com.jinfukeji.waterhousekeeper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.waterhousekeeper.R;

/**
 * Created by "于志渊"
 * 时间:"9:20"
 * 包名:com.jinfukeji.waterhousekeeper.fragment
 * 描述:占位界面
 */

public class NullActivity extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_null,null);
        return view;
    }
}

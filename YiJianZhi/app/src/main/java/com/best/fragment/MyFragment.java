package com.best.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.best.demo.yijianzhi.R;

/**
 * FirstFragment  我的框架
 * 点击首页切换到我的的内容
 *
 * */
public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到我的的布局文件
        View v = inflater.inflate(R.layout.fragment_my,container,false);
        return v;
    }
}

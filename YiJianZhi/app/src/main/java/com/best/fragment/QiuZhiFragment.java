package com.best.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.best.demo.yijianzhi.R;

/**
 * FirstFragment  求职框架
 * 点击首页切换到求职的内容
 *
 * */
public class QiuZhiFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到求职的布局文件
        View v = inflater.inflate(R.layout.fragment_qiuzhi,container,false);
        return v;
    }
}

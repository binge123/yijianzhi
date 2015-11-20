package com.best.fragment;

/**
 * Created by dell2 on 2015/11/18.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.best.demo.yijianzhi.R;

/**
 * FirstFragment  首页框架
 * 点击首页切换到首页的内容
 *
 * */
public class FirstFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到首页的布局文件
        View v = inflater.inflate(R.layout.fragment_first,container,false);

        return v;
    }
}

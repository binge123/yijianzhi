package com.best.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.best.adapter.ShouCangAdapter;
import com.best.demo.yijianzhi.LoginActivity;
import com.best.demo.yijianzhi.MyFaBuActivity;
import com.best.demo.yijianzhi.R;
import com.best.demo.yijianzhi.ShouCangActivity;
import com.best.demo.yijianzhi.ZiLiaoActivity;

/**
 * FirstFragment  我的框架
 * 点击首页切换到我的的内容
 *
 * */
public class MyFragment extends Fragment {

    LinearLayout l1,l2,l3,l4,l5,l6;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到我的的布局文件
        View v = inflater.inflate(R.layout.fragment_my,container,false);
        l1 = (LinearLayout) v.findViewById(R.id.my_shoucang);
        l2 = (LinearLayout) v.findViewById(R.id.my_ziliao);
        l3 = (LinearLayout) v.findViewById(R.id.my_fabu);
        l4 = (LinearLayout) v.findViewById(R.id.my_fajianli);
        l5 = (LinearLayout) v.findViewById(R.id.my_fazhaopin);
        l6 = (LinearLayout) v.findViewById(R.id.my_denglu);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ShouCangActivity.class);
                i.putExtra("zhanghao",12);
                startActivity(i);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ZiLiaoActivity.class);
                i.putExtra("zhanghao",12);
                startActivity(i);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyFaBuActivity.class);
                i.putExtra("zhanghao",12);
                startActivity(i);
            }
        });


        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        return v;
    }
}

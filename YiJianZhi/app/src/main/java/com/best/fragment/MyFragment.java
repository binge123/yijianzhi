package com.best.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.best.demo.yijianzhi.LoginActivity;
import com.best.demo.yijianzhi.MoreActivity;
import com.best.demo.yijianzhi.MyFaBuActivity;
import com.best.demo.yijianzhi.QiuZhiActivity;
import com.best.demo.yijianzhi.R;
import com.best.demo.yijianzhi.RecruitActivity;
import com.best.demo.yijianzhi.ShouCangActivity;
import com.best.demo.yijianzhi.ZiLiaoActivity;

/**
 * FirstFragment  我的框架
 * 点击首页切换到我的的内容
 *
 * */
public class MyFragment extends Fragment {

    LinearLayout l1,l2,l3,l4,l5,l6;
    RelativeLayout mrl;
    String m_id = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到我的的布局文件
        View v = inflater.inflate(R.layout.fragment_my,container,false);
        m_id = getActivity().getIntent().getStringExtra("id");
        mrl = (RelativeLayout) v.findViewById(R.id.my_rl);
        l1 = (LinearLayout) v.findViewById(R.id.my_shoucang);
        l2 = (LinearLayout) v.findViewById(R.id.my_ziliao);
        l3 = (LinearLayout) v.findViewById(R.id.my_fabu);
        l4 = (LinearLayout) v.findViewById(R.id.my_fajianli);
        l5 = (LinearLayout) v.findViewById(R.id.my_denglu);
        l6 = (LinearLayout) v.findViewById(R.id.my_more);
        mrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_id == null) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getActivity(), ShouCangActivity.class);
                    i.putExtra("zhanghao",m_id);
                    startActivity(i);
                }
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_id == null) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getActivity(), ZiLiaoActivity.class);
                    i.putExtra("zhanghao", m_id);
                    startActivity(i);
                }
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_id == null) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getActivity(), MyFaBuActivity.class);
                    i.putExtra("zhanghao", m_id);
                    startActivity(i);
                }
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_id == null) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getActivity(), QiuZhiActivity.class);
                    i.putExtra("zhanghao", m_id);
                    startActivity(i);
                }
            }
        });
//        l5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), RecruitActivity.class);
//                i.putExtra("zhanghao",id);
//                startActivity(i);
//            }
//        });

        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreActivity.class);
                i.putExtra("zhanghao",m_id);
                startActivity(i);
            }
        });
        return v;
    }
}

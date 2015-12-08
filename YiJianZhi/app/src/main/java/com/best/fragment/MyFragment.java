package com.best.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.best.bean.User;
import com.best.demo.yijianzhi.LoginActivity;
import com.best.demo.yijianzhi.MoreActivity;
import com.best.demo.yijianzhi.MyFaBuActivity;
import com.best.demo.yijianzhi.PeopleDataActivity;
import com.best.demo.yijianzhi.QiuZhiActivity;
import com.best.demo.yijianzhi.R;
import com.best.demo.yijianzhi.RecruitActivity;
import com.best.demo.yijianzhi.ShouCangActivity;
import com.best.demo.yijianzhi.ZiLiaoActivity;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * FirstFragment  我的框架
 * 点击首页切换到我的的内容
 *
 * */
public class MyFragment extends Fragment {

    LinearLayout l1,l2,l3,l4,l5,l6;
    RelativeLayout mrl;
    TextView t1,t2,t3,t4;
    String m_zhuang;
    String m_id = "",m_zhuan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到我的的布局文件
        View v = inflater.inflate(R.layout.fragment_my,container,false);
        SharedPreferences sp = getActivity().getSharedPreferences("username", Context.MODE_PRIVATE);
        m_id = sp.getString("username","1");
        m_zhuang = sp.getString("zhuang","wei");
        SharedPreferences mosp = getActivity().getSharedPreferences("zhuan", Context.MODE_PRIVATE);
        m_zhuan = mosp.getString("zhuan","1");
        mrl = (RelativeLayout) v.findViewById(R.id.my_rl);
        l1 = (LinearLayout) v.findViewById(R.id.my_shoucang);
        l2 = (LinearLayout) v.findViewById(R.id.my_ziliao);
        l3 = (LinearLayout) v.findViewById(R.id.my_fabu);
        l4 = (LinearLayout) v.findViewById(R.id.my_fajianli);
        l5 = (LinearLayout) v.findViewById(R.id.my_denglu);
        l6 = (LinearLayout) v.findViewById(R.id.my_more);
        t1 = (TextView) v.findViewById(R.id.my_zhuangtai);
        t2 = (TextView) v.findViewById(R.id.my_dian);
        t3 = (TextView) v.findViewById(R.id.my_fabuz);
        t4 = (TextView) v.findViewById(R.id.my_deng);
        if ("zhao".equals(m_zhuan)){
            t3.setText("发布招聘");
        }else {
            t3.setText("发布简历");
        }
        if ("wei".equals(m_zhuang)){
            t4.setText("我要登录");
        }else {
            t4.setText("注销登录");
        }
        if (m_id != null) {
            Bmob.initialize(getActivity(), "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
            BmobQuery<User> mbq = new BmobQuery<>();
            mbq.addWhereEqualTo("username",m_id);
            mbq.findObjects(getActivity(), new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    t1.setText(list.get(0).getNick());
                    t2.setText(list.get(0).getUser_age());
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
        mrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("wei".equals(m_zhuang)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent i = new Intent(getActivity(), ZiLiaoActivity.class);
                    i.putExtra("zhanghao", m_id);
                    startActivity(i);
                }
            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("wei".equals(m_zhuang)) {
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
                if ("wei".equals(m_zhuang)) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getActivity(), PeopleDataActivity.class);
                    startActivity(i);
                }
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("wei".equals(m_zhuang)) {
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
                if ("wei".equals(m_zhuang)) {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    if("zhao".equals(m_zhuan)){
                        Intent i =new Intent(getActivity(),RecruitActivity.class);
                        i.putExtra("zhanghao", m_id);
                        startActivity(i);
                    }else {
                        Intent i = new Intent(getActivity(), QiuZhiActivity.class);
                        i.putExtra("zhanghao", m_id);
                        startActivity(i);
                    }
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
                if ("yi".equals(m_zhuang)){
                    SharedPreferences sp = getActivity().getSharedPreferences("username", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ueditor = sp.edit();
                    ueditor.putString("username", "1");
                    ueditor.putString("zhuang","wei");
                    ueditor.commit();
                }
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

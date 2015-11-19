package com.best.fragment;

/**
 * Created by dell2 on 2015/11/18.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.best.demo.yijianzhi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FirstFragment  首页框架
 * 点击首页切换到首页的内容
 *内容：
 *    1、banner轮播：ViewPager
 *    2、GridView选项
 *    3、推荐招聘：ListView选项
 *
 *
 * */
public class FirstFragment extends Fragment{
    ViewPager vp;
    View v1,v2;
    List<View> list = new ArrayList<>();
    GridView gr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到首页的布局文件
        View v = inflater.inflate(R.layout.fragment_first,container,false);
        vp =(ViewPager)v.findViewById(R.id.first_banner);
        v1 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner1_layout,null);
        v2 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner2_layout,null);
        list.add(v1);
        list.add(v2);
        vp.setAdapter(new MyViewPager());
        return v;
    }
    class MyViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v,0);
            return v;
        }
    }
}

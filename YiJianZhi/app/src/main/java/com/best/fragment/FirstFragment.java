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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.best.adapter.FirstListViewAdapter;
import android.widget.GridView;
import com.best.demo.yijianzhi.R;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

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
    ListView lv;
    PtrClassicFrameLayout ptr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到首页的布局文件
        View v = inflater.inflate(R.layout.fragment_first,container,false);
        //首页banner轮播
        vp =(ViewPager)v.findViewById(R.id.first_banner);
        v1 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner1_layout,null);
        v2 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner2_layout,null);
        list.add(v1);
        list.add(v2);
        vp.setAdapter(new MyViewPager());
        lv =(ListView)v.findViewById(R.id.first_listview);

        lv.setAdapter(new FirstListViewAdapter(getActivity()));
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });

        ptr = (PtrClassicFrameLayout) v.findViewById(R.id.rotate_header_list_view_frame);
        ptr.setLastUpdateTimeRelateObject(this);

        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        bq.findObjects(getContext(), new FindListener<Brand_child>() {
//                            @Override
//                            public void onSuccess(List<Brand_child> list) {
//                                Log.i("test", "成功");
//                                lv.setAdapter(new FirstAdapter(list, getContext()));
//                            }
//
//                            @Override
//                            public void onError(int i, String s) {
//
//                            }
//                        });
                        ptr.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        ptr.setResistance(1.7f);
        ptr.setRatioOfHeaderHeightToRefresh(1.2f);
        ptr.setDurationToClose(200);
        ptr.setDurationToCloseHeader(1000);
        // default is false
        ptr.setPullToRefresh(false);
        // default is truep
        ptr.setKeepHeaderWhenRefresh(true);
        ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptr.autoRefresh();
            }
        }, 100);
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

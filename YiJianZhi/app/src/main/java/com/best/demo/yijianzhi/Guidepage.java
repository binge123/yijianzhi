package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Guidepage extends Activity {

    ViewPager viewPager;
    List<View> list  = new ArrayList<>();
    List<RadioButton> rbs= new ArrayList<>();
    View v1,v2,v3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidepage);
        viewPager = (ViewPager) findViewById(R.id.view);
        v1 = LayoutInflater.from(this).inflate(R.layout.viewpagelayout1,null);
        v2 = LayoutInflater.from(this).inflate(R.layout.viewpagelayout2,null);
        v3 = LayoutInflater.from(this).inflate(R.layout.viewpagelayout3,null);
        ((Button)v3.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Guidepage.this,MainActivity.class);
                startActivity(i);
            }
        });
        list.add(v1);
        list.add(v2);
        list.add(v3);
        viewPager.setAdapter(new MyAdapter());
    }
    class MyAdapter extends PagerAdapter{

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
    public void tNext(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}

package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.demo.yijianzhi.R;

/**
 * Created by TaoHongTu on 2015/11/19.
 * 首页GridView项
 */
public class FirstListViewAdapter extends BaseAdapter {
    Context context;
    int[] images = {R.drawable.zhuye_remen,R.drawable.zhuye_fujin,R.drawable.zhuye_xianshi,R.drawable.zhuye_gengduo,
            R.drawable.zhuye_remen,R.drawable.zhuye_fujin,R.drawable.zhuye_xianshi,R.drawable.zhuye_gengduo};
    int[] gongneng ={R.string.first_remen,R.string.first_fujin,R.string.first_xianshi,R.string.first_gengduo,
            R.string.first_remen,R.string.first_fujin,R.string.first_xianshi,R.string.first_gengduo};
    int[] gongneng1 ={R.string.first_remen1,R.string.first_fujin1,R.string.first_xianshi1,R.string.first_gengduo1,
            R.string.first_remen1,R.string.first_fujin1,R.string.first_xianshi1,R.string.first_gengduo1};
    int[] gvadp_text ={R.color.first_gridview_remen,R.color.first_gridview_fujin,R.color.first_gridview_xianshi,R.color.first_gridview_gengduo,
            R.color.first_gridview_remen,R.color.first_gridview_fujin,R.color.first_gridview_xianshi,R.color.first_gridview_gengduo};
    public FirstListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.first_listview_adapterlayout,null);
        ImageView iv =(ImageView)convertView.findViewById(R.id.first_grvimageView);
        TextView tv =(TextView)convertView.findViewById(R.id.first_gridview_text1);
        TextView tv1 =(TextView)convertView.findViewById(R.id.first_gridview_text2);

//        iv.setLayoutParams(new GridView.LayoutParams(85,85));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setPadding(8, 8, 8, 8);
        iv.setImageResource(images[position]);
        tv.setText(gongneng[position]);
        tv1.setText(gongneng1[position]);
        tv.setTextColor(gvadp_text[position]);
        return convertView;
    }
}

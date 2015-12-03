package com.best.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.bean.CompanyPublish;
import com.best.demo.yijianzhi.R;

import java.util.List;

/**
 * Created by TaoHongTu on 2015/11/19.
 * 首页GridView项
 */
public class FirstListViewAdapter extends BaseAdapter {
    Context context;
    List list;

    public FirstListViewAdapter(Context context,List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Log.i("showtest","dasdsdsads");


        ViewConvert viewConvert = null;
        if(convertView == null){
            viewConvert = new ViewConvert();
            convertView = LayoutInflater.from(context).inflate(R.layout.first_listview_adapterlayout,null);
            viewConvert.imageView =(ImageView) convertView.findViewById(R.id.first_grvimageView);
            viewConvert.textView1 =(TextView) convertView.findViewById(R.id.first_gridview_text1);
            viewConvert.textView2 =(TextView) convertView.findViewById(R.id.first_gridview_text2);
            convertView.setTag(viewConvert);
        }else {
            viewConvert = (ViewConvert) convertView.getTag();
        }


        CompanyPublish cp = (CompanyPublish) list.get(position);
        Log.i("text", cp.getTitle());
        viewConvert.textView1.setText(cp.getTitle());
        viewConvert.textView2.setText(cp.getMoney());

//        iv.setLayoutParams(new GridView.LayoutParams(85,85));
//        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        iv.setPadding(8, 8, 8, 8);
//        iv.setImageResource(images[position]);
//        tv.setText(gongneng[position]);
//        tv1.setText(gongneng1[position]);
//        tv.setTextColor(gvadp_text[position]);
        return convertView;
    }
    class ViewConvert{
        ImageView imageView;
        TextView textView1,textView2;
    }
}

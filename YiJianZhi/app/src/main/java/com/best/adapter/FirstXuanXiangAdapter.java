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
 * Created by TaoHongTu on 2015/12/3.
 */
public class FirstXuanXiangAdapter extends BaseAdapter {

    Context context;
    List list;

    public FirstXuanXiangAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.i("trrt","list="+list.size());
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
        Log.i("showtest", "dasdsdsads");


        ViewConvert viewConvert = null;
        if(convertView == null){
            viewConvert = new ViewConvert();
            convertView = LayoutInflater.from(context).inflate(R.layout.firstxuanxiang_adapter,null);
            viewConvert.imageView =(ImageView) convertView.findViewById(R.id.firstxuanxiang_image);
            viewConvert.textView1 =(TextView) convertView.findViewById(R.id.firstxuanxiang_adapter_text1);
            viewConvert.textView2 =(TextView) convertView.findViewById(R.id.firstxuanxiang_adapter_xinzi);
            viewConvert.textView3 =(TextView) convertView.findViewById(R.id.firstxuanxiang_adapter_time);
            viewConvert.textView4 =(TextView) convertView.findViewById(R.id.firstxuanxiang_adapter_didian);
            convertView.setTag(viewConvert);
        }else {
            viewConvert = (ViewConvert) convertView.getTag();
        }


        CompanyPublish cp = (CompanyPublish) list.get(position);
        Log.i("text", cp.getTitle());
        viewConvert.textView1.setText(cp.getTitle());
        viewConvert.textView2.setText(cp.getMoney());
        viewConvert.textView3.setText(cp.getOver_time());
        viewConvert.textView4.setText(cp.getWork_address());

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
        TextView textView1,textView2,textView3,textView4;
    }
}

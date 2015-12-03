package com.best.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.best.bean.RecruitTable;
import com.best.demo.yijianzhi.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public class QiuZuiAdapter extends BaseAdapter {
    List<RecruitTable> list;
    public Context context;

    public QiuZuiAdapter(Context context, List<RecruitTable> list) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guangzhulist,null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.zhaopininfo);
            holder.address = (TextView) convertView.findViewById(R.id.list_address);
            holder.time = (TextView) convertView.findViewById(R.id.list_tiem);
            holder.money = (TextView) convertView.findViewById(R.id.list_money);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.name.setText(list.get(position).getTitle());
        holder.address.setText(list.get(position).getWork_address());
        holder.time.setText(list.get(position).getOver_time());
        holder.money.setText(list.get(position).getWork_money());
        return convertView;
    }
    class ViewHolder {
        TextView name,address,time,money;
    }
}

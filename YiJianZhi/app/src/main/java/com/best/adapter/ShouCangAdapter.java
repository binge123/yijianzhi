package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import com.best.demo.yijianzhi.R;

/**
 * Created by Administrator on 2015/11/20.
 */
public class ShouCangAdapter extends BaseAdapter {

    public Context context;
    public List list;
    public ShouCangAdapter(Context context,List list)
    {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.guangzhulist,null);
        return null;
    }
}

package com.best.bean;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by TaoHongTu on 2015/11/20.
 */
public class MyListViewForScollProbiem extends ListView {
    public MyListViewForScollProbiem(Context context){
        super(context);
    }

    public MyListViewForScollProbiem(Context context,AttributeSet attributeSet){
        super(context,attributeSet);

    }
    public MyListViewForScollProbiem(Context context,AttributeSet attributeSet, int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int endHeight =MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, endHeight);
    }
}

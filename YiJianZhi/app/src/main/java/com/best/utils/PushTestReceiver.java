package com.best.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.best.bean.CompanyPublish;
import com.best.demo.yijianzhi.JianLiXiangQingActivity;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;

/**
 * Created by dell2 on 2015/12/1.
 */
public class PushTestReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        Log.i("www","aaaa");
//        Intent intent = new Intent();
//        intent.setClass(context.getApplicationContext(),JianLiXiangQingActivity.class);
//        intent.putExtra("chanelid", channelId);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.getApplicationContext().startActivity(intent);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String s, String s1) {

    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Intent intent = new Intent();
        intent.setClass(context.getApplicationContext(),JianLiXiangQingActivity.class);
//        intent.putExtra("chanelid", channelId);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}

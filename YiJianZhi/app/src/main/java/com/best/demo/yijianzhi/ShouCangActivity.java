package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.best.adapter.ShouCangAdapter;
import com.best.bean.RecruitTable;
import com.best.bean.UserCollection;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 郑方斌 on 2015/11/20.
 * 用户收藏页
 */
public class ShouCangActivity extends Activity {

    ListView listView;
    List<UserCollection> list;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        sp = getSharedPreferences("username", Context.MODE_PRIVATE);
        listView = (ListView) findViewById(R.id.shoucang_list);
        new Thread(new Thread() {
            @Override
            public void run() {
                Bmob.initialize(ShouCangActivity.this, "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
                BmobQuery<UserCollection> bq = new BmobQuery<>();
                bq = bq.addWhereEqualTo("username",sp.getString("username","0"));
                bq.findObjects(ShouCangActivity.this, new FindListener<UserCollection>() {
                    @Override
                    public void onSuccess(List<UserCollection> list) {
                        listView.setAdapter(new ShouCangAdapter(ShouCangActivity.this,list));
                }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
            }
        }).run();

    }
}

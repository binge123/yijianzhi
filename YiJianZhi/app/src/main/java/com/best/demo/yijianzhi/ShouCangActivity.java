package com.best.demo.yijianzhi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by 郑方斌 on 2015/11/20.
 * 用户收藏页
 */
public class ShouCangActivity extends Activity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        listView = (ListView) findViewById(R.id.shoucang_list);
    }
}

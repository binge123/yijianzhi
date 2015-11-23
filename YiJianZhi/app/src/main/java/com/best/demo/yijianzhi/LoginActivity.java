package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2015/11/17.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void zhuCe(View v)
    {
        Intent i = new Intent(this,ZhuCeActivity.class);
        startActivity(i);
    }
}

package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/26.
 */
public class MoreActivity extends Activity {

    static String a = "我要招聘";
    TextView zhuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        zhuan = (TextView) findViewById(R.id.more_huan);
        zhuan.setText(a);
    }
    public void mFanHui(View v){
        this.finish();
    }
    public void zhuan(View v) {
        Intent i = new Intent(this,MainActivity.class);
        if ("我要招聘".equals(zhuan.getText())){
            a="我要求职";
            zhuan.setText(a);
            SharedPreferences mosp = getSharedPreferences("zhuan", Context.MODE_PRIVATE);
            SharedPreferences.Editor meditor = mosp.edit();
            meditor.putString("zhuan", "zhao");
            meditor.commit();
        }else{
            a="我要招聘";
            zhuan.setText(a);
            SharedPreferences mosp = getSharedPreferences("zhuan", Context.MODE_PRIVATE);
            SharedPreferences.Editor meditor = mosp.edit();
            meditor.putString("zhuan", "qiu");
            meditor.commit();
        }
        startActivity(i);
    }
    public void jianxin(View v) {
        Toast.makeText(this,"当前已是最新版本",Toast.LENGTH_SHORT).show();
    }
}

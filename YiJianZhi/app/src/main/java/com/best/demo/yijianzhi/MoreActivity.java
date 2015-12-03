package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/26.
 */
public class MoreActivity extends Activity {

    TextView zhuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        zhuan = (TextView) findViewById(R.id.more_huan);
    }
    public void mFanHui(View v){
        this.finish();
    }
    public void zhuan(View v) {
        Intent i = new Intent(this,MainActivity.class);
        if ("我要招聘".equals(zhuan.getText())){
            zhuan.setText("我要求职");
            i.putExtra("xing","qiu");
        }else{
            zhuan.setText("我要招聘");
            i.putExtra("xing","zhao");
        }
        startActivity(i);
    }
    public void jianxin(View v) {
        Toast.makeText(this,"当前已是最新版本",Toast.LENGTH_SHORT).show();
    }
}

package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class Splashscreen extends Activity {
    public static long currentTime;
    SharedPreferences fsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        fsp = getSharedPreferences("sp",MODE_PRIVATE);
        Log.i("zxcv",fsp.getInt("first",10)+"");
        new Thread(new Runnable() {
            @Override
            public void run() {
                currentTime = System.currentTimeMillis();
                while (true){
                    //加载数据
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    //跳转到MusicListActivity
                    if (fsp.getInt("first",0)==0) {
                        Intent intent = new Intent(Splashscreen.this, Guidepage.class);
                        Splashscreen.this.startActivity(intent);
                        Splashscreen.this.finish();
                    }else {
                        Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                        Splashscreen.this.startActivity(intent);
                        Splashscreen.this.finish();
                    }
                    // finish();
                    break;

                }


            }
        }).start();

    }


}

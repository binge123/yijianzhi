package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class Splashscreen extends Activity {
    public static long currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
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
                    Intent intent = new Intent(Splashscreen.this, Guidepage.class);
                    Splashscreen.this.startActivity(intent);
                    Splashscreen.this.finish();
                    // finish();
                    break;

                }


            }
        }).start();
        //启动云推送
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "e8WobuHCGgTjWGq8VEVHAHzg");
    }


}

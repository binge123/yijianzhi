package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.best.bean.User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2015/11/17.
 */
public class LoginActivity extends Activity {

    EditText zhanghao,mima;
    TextView jg,newuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        zhanghao = (EditText) findViewById(R.id.denglu_zhanghao);
        mima = (EditText) findViewById( R.id.denglu_mima);
        jg = (TextView) findViewById(R.id.jinggao);
        newuser = (TextView) findViewById(R.id.denglu_zhuce);
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ZhuCeActivity.class);
                startActivity(i);
            }
        });
    }
    public void login(View v)
    {
        if(zhanghao.getText().toString().length()!=11)
        {
            jg.setText("请输入正确的手机号");
        }
        if((mima.getText().toString().length())<=8 && (mima.getText().toString().length()>=16))
        {
            jg.setText("密码必须大于8位并且小于16位");
        }else{
            Bmob.initialize(this, "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
            BmobQuery<User> hao = new BmobQuery<>();
            hao = hao.addWhereEqualTo("username", zhanghao.getText().toString());
            hao.findObjects(this, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> object) {
                    String mima1 = object.get(0).getPassword();
                    if(mima.getText().toString().equals(mima1)) {
                        //启动云推送
                        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "e8WobuHCGgTjWGq8VEVHAHzg");
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        SharedPreferences sp = getSharedPreferences("username", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ueditor = sp.edit();
                        ueditor.putString("username", zhanghao.getText().toString());
                        ueditor.putString("zhuang","yi");
                        ueditor.commit();
                        startActivity(i);
                        LoginActivity.this.finish();
                    }else {
                        jg.setText("账号或密码不正确");
                    }
                }

                @Override
                public void onError(int code, String msg) {
                }
            });
        }
    }
}

package com.best.demo.yijianzhi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.best.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class PeopleDataActivity extends AppCompatActivity {
    TextView T1,T2,T3,T4,T5,T6,T7;
    Button md_btn,md_btn1;
    ImageView x_tu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_data);
        SharedPreferences sp = getSharedPreferences("username", Context.MODE_PRIVATE);
        T1 = (TextView) findViewById(R.id.md_nick1);
        T2 = (TextView) findViewById(R.id.md_sex1);
        T3 = (TextView) findViewById(R.id.md_age1);
        T4 = (TextView) findViewById(R.id.md_address1);
        T5 = (TextView) findViewById(R.id.md_exp1);
        T6 = (TextView) findViewById(R.id.md_zhi1);
        T7 = (TextView) findViewById(R.id.md_money1);
        md_btn = (Button) findViewById(R.id.md_btn);
        md_btn1 = (Button) findViewById(R.id.md_btn1);
        md_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeopleDataActivity.this,ZiLiaoActivity.class);
                startActivity(intent);
                PeopleDataActivity.this.finish();
            }
        });
        md_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final BmobQuery<User> bmob1 = new BmobQuery<>();
        bmob1.addWhereEqualTo("username",sp.getString("username","1"));
        bmob1.findObjects(this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> object) {
                // TODO Auto-generated method stub
                Toast.makeText(PeopleDataActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                for (User gameScore : object) {
                    T1.setText(gameScore.getNick());
                    T2.setText(gameScore.getUser_sex());
                    T3.setText(gameScore.getUser_age());
                    T4.setText(gameScore.getUser_address());
                    T5.setText(gameScore.getRenzheng());
                    T6.setText(gameScore.getPhone_number());
                    T7.setText(gameScore.getLast_login_time());
                }
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(PeopleDataActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

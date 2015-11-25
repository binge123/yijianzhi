package com.best.demo.yijianzhi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.best.bean.ResumeTable;
import com.best.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

public class ZhaoPinXiangQingActivity extends AppCompatActivity {


    List<ResumeTable> lists = new ArrayList<>();
    TextView T1,T2,T3,T4,T5,T6,T7,T8,T9,T10;
//    String name,work_exp,work_time,expect_money,resumeid,age,sex,position,address,remark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhao_pin_xiang_qing);
        String id = getIntent().getStringExtra("id");
        T1 = (TextView) findViewById(R.id.x_name1);
        T2 = (TextView) findViewById(R.id.x_sex1);
        T3 = (TextView) findViewById(R.id.x_age1);
        T4 = (TextView) findViewById(R.id.x_address1);
        T5 = (TextView) findViewById(R.id.x_exp1);
        T6 = (TextView) findViewById(R.id.x_zhi1);
        T7 = (TextView) findViewById(R.id.x_money1);
        T8 = (TextView) findViewById(R.id.x_time1);
        T9 = (TextView) findViewById(R.id.x_bei1);
        Bmob.initialize(this,"18b52c81a4fcfaf1f5cf2418f4ac9bc5");
        BmobQuery<ResumeTable> bmob = new BmobQuery<>();
        bmob = bmob.addWhereEqualTo("resumeid", id);
        bmob.getObject(this,id, new GetListener<ResumeTable>() {
            @Override
            public void onSuccess(ResumeTable res) {
//                name =bmobAdapter.getName();
                T1.setText(res.getName());//姓名
                T2.setText(res.getSex());//性别
                T3.setText(res.getAge());//年龄
                T4.setText(res.getAddress());//地址
                T5.setText(res.getWork_exp());//工作经验
                T6.setText(res.getZhiwei());//职位
                T7.setText(res.getExpect_money());//薪资
                T8.setText(res.getWork_time());//工作时间
                T9.setText(res.getRemark());//备注
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }
}

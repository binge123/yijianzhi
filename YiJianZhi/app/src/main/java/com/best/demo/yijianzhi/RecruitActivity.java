package com.best.demo.yijianzhi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.best.bean.RecruitTable;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by wen on 2015/11/20.
 */
public class RecruitActivity extends Activity {
    Button Btn;
    EditText Et1,Et2,Et3,Et4,Et5,Et6,Et7,Et8,Et9,Et10,Et11;
    String name,work_address,position,work_money,position_desc,contacts,person_number, title,over_time,recruitid;
    ImageView Iv1;
    List<RecruitTable> lists = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit);
        Btn = (Button) findViewById(R.id.re_btn);
        Et1 = (EditText) findViewById(R.id.re_title1);
        Et2 = (EditText) findViewById(R.id.re_address1);
        Et3 = (EditText) findViewById(R.id.re_position1);
        Et4 = (EditText) findViewById(R.id.re_money1);
        Et5 = (EditText) findViewById(R.id.re_contacts1);
        Et6 = (EditText) findViewById(R.id.re_name1);
        Et7 = (EditText) findViewById(R.id.re_pdesc1);
        Et8 = (EditText) findViewById(R.id.re_pnum1);
        Et9 = (EditText) findViewById(R.id.re_recruitid1);
        Et10 = (EditText) findViewById(R.id.re_time1);
        Btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bmob.initialize(getApplicationContext(), "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
                final BmobQuery<RecruitTable> bmob = new BmobQuery<>();
                title = Et1.getText().toString();//标题
                work_address = Et2.getText().toString();//地址
                position = Et3.getText().toString();//职位
                work_money = Et4.getText().toString();//薪资
                contacts = Et5.getText().toString();//联系人
                name = Et6.getText().toString();//公司名
                position_desc = Et7.getText().toString();//职位描述
//                phone_number = Et8.getText().toString();//电话号码
                person_number = Et8.getText().toString();//需求人数
                recruitid = ((int)Math.random()*10000)+"";
                over_time = Et10.getText().toString();//结束时间
                RecruitTable ra = new RecruitTable();
                ra.setWork_money(work_money);
                ra.setWork_address(work_address);
                ra.setPosition_desc(position_desc);
                ra.setRecruitid(recruitid);
                ra.setContacts(contacts);
                ra.setPerson_number(person_number);
                ra.setPosition(position);
//                ra.setPhone_number(phone_number);
                ra.setName(name);
                ra.setOver_time(over_time);
                ra.setTitle(title);
                ra.save(getApplicationContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RecruitActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(RecruitActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    public void zFanHui(View v) {
        this.finish();
    }
}

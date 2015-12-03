package com.best.demo.yijianzhi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.best.bean.ResumeTable;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class QiuZhiActivity extends Activity {

    String name,work_exp,work_time,expect_money,resumeid,age,sex,position,address,remark;
    Button Btn;
    ImageView Iv1;
    EditText E1,E2,E3,E4,E5,E6,E7,E8,E9,E10;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_qiuzhi);
        Btn = (Button) findViewById(R.id.r_btn);
        Iv1 = (ImageView) findViewById(R.id.Iv1);
        E1 = (EditText) findViewById(R.id.r_name1);
        E2 = (EditText) findViewById(R.id.r_sex1);
        E3 = (EditText) findViewById(R.id.r_age1);
        E4 = (EditText) findViewById(R.id.r_address1);
        E5 = (EditText) findViewById(R.id.r_exp1);
        E6 = (EditText) findViewById(R.id.r_zhi1);
        E7 = (EditText) findViewById(R.id.r_money1);
        E8 = (EditText) findViewById(R.id.r_time1);
        E9 = (EditText) findViewById(R.id.r_bei1);
//        E10 = (EditText) findViewById(R.id.E10);
        Iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QiuZhiActivity.this.finish();
            }
        });
        Btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bmob.initialize(getApplicationContext(), "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
                name = E1.getText().toString();//姓名
                sex = E2.getText().toString();//性别
                age = E3.getText().toString();//年龄
                address = E4.getText().toString();//地址
                work_exp = E5.getText().toString();//工作经验
                position = E6.getText().toString();//求职职位
                expect_money = E7.getText().toString();//渴望薪资
//                resumeid = E8.getText().toString();//联系方式
                remark = E9.getText().toString();//备注
                work_time = E8.getText().toString();//工作时间
                ResumeTable ba = new ResumeTable();
                ba.setName(name);
                ba.setSex(sex);
                ba.setAge(age);
                ba.setAddress(address);
                ba.setWork_exp(work_exp);
                ba.setZhiwei(position);
                ba.setExpect_money(expect_money);
//                ba.setResumeid(resumeid);
                ba.setRemark(remark);
                ba.setWork_time(work_time);
                ba.save(getApplicationContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(QiuZhiActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(QiuZhiActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

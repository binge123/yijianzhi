package com.best.demo.yijianzhi;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.best.fragment.FirstFragment;
import com.best.fragment.MyFragment;
import com.best.fragment.QiuZhiFragment;
import com.best.fragment.ZhaoPinFragment;


/**
 * MainActivity   主框架
 * 实现4个Fragment的切换
 *
 * 实现了点击事件OnClickListener的接口
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FragmentManager fm;
    FragmentTransaction ftt;
    RadioButton rbtn,rbtn1,rbtn2,rbtn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到4个radioButon
        rbtn = (RadioButton) findViewById(R.id.radioButton1);
        rbtn1 = (RadioButton) findViewById(R.id.radioButton2);
        rbtn2 = (RadioButton) findViewById(R.id.radioButton3);
        rbtn3 = (RadioButton) findViewById(R.id.radioButton4);
        //设置点击事件
        rbtn.setOnClickListener(this);
        rbtn1.setOnClickListener(this);
        rbtn2.setOnClickListener(this);
        rbtn3.setOnClickListener(this);
        //得到FragmentManager的对象
        fm = getSupportFragmentManager();
        //设置默认点击首页
        rbtn.setChecked(true);
        if(savedInstanceState == null){
            ftt = fm.beginTransaction();
            FirstFragment ff = new FirstFragment();
            ftt.add(R.id.fragment_parent,ff,"radioButton1");
            ftt.commit();
        }
    }

    @Override
    public void onClick(View v) {
        ftt = fm.beginTransaction();
        //让所有的Fragment都隐藏
        if(fm.findFragmentByTag("radioButton1") != null){
            ftt.hide(fm.findFragmentByTag("radioButton1"));
        }
        if(fm.findFragmentByTag("radioButton2") != null){
            ftt.hide(fm.findFragmentByTag("radioButton2"));
        }
        if(fm.findFragmentByTag("radioButton3") != null){
            ftt.hide(fm.findFragmentByTag("radioButton3"));
        }
        if(fm.findFragmentByTag("radioButton4") != null){
            ftt.hide(fm.findFragmentByTag("radioButton4"));
        }

        //得到每一个Fragment布局的ID
        int id = v.getId();
        if(id == R.id.radioButton1){
            if(fm.findFragmentByTag("radioButton1")!=null){
                ftt.show(fm.findFragmentByTag("radioButton1"));
            }else{
                FirstFragment ff = new FirstFragment();
                ftt.add(R.id.fragment_parent,ff,"radioButton1");
            }
        }else if(id == R.id.radioButton2){
            if(fm.findFragmentByTag("radioButton2")!=null){
                ftt.show(fm.findFragmentByTag("radioButton2"));
            }else{
                QiuZhiFragment qf = new QiuZhiFragment();
                ftt.add(R.id.fragment_parent,qf,"radioButton2");
            }
        }else if(id == R.id.radioButton3){
            if(fm.findFragmentByTag("radioButton3")!=null){
                ftt.show(fm.findFragmentByTag("radioButton3"));
            }else{
                ZhaoPinFragment zf = new ZhaoPinFragment();
                ftt.add(R.id.fragment_parent,zf,"radioButton3");
            }
        }else {
            if(fm.findFragmentByTag("radioButton4")!=null){
                ftt.show(fm.findFragmentByTag("radioButton4"));
            }else{
                MyFragment mf = new MyFragment();
                ftt.add(R.id.fragment_parent,mf,"radioButton4");
            }
        }
        ftt.commit();
    }
}

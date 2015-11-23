package com.best.demo.yijianzhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

public class ZhuCeActivity extends AppCompatActivity {
    EditText et,et1,et2;
    String number = null;
    String code = null;
    ImageButton imagebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        et = (EditText) findViewById(R.id.editText);
        et1 = (EditText) findViewById(R.id.editText2);
        et2 = (EditText) findViewById(R.id.editText1);

        imagebtn = (ImageButton) findViewById(R.id.imagebtn);

        BmobSMS.initialize(this, "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et.getText().toString();
                code = et1.getText().toString();
                //通过verifySmsCode方式可验证该短信验证码
                BmobSMS.verifySmsCode(ZhuCeActivity.this, number, code, new VerifySMSCodeListener() {

                    @Override
                    public void done(BmobException ex) {
                        // TODO Auto-generated method stub
                        if (ex == null) {//短信验证码已验证成功
                            Toast.makeText(ZhuCeActivity.this,"注册成功", Toast.LENGTH_SHORT).show();

                            ZhuCeActivity.this.finish();
                        } else {
                            Log.i("smile", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                        }
                    }
                });
            }
        });
    }
    public void sendButton(View v){
        number = et.getText().toString();
        code = et1.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendTime = format.format(new Date());
        BmobSMS.requestSMS(ZhuCeActivity.this, number,"您的验证码是%smscode%，有效期为%ttl%分钟。您正在使用%appname%的验证码。", sendTime, new RequestSMSCodeListener() {

            @Override
            public void done(Integer smsId, BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {//
                    Log.i("bmob", "短信发送成功，短信id：" + smsId);//用于查询本次短信发送详情
                } else {
                    Log.i("bmob", "errorCode = " + ex.getErrorCode() + ",errorMsg = " + ex.getLocalizedMessage());
                }
            }
        });
        //通过requestSMSCode方式给绑定手机号的该用户发送指定短信模板的短信验证码
        BmobSMS.requestSMSCode(this,number, "注册模板",new RequestSMSCodeListener() {

            @Override
            public void done(Integer smsId,BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//验证码发送成功
                    Log.i("bmob", "短信id："+smsId);//用于查询本次短信发送详情
                }
            }
        });
    }
}

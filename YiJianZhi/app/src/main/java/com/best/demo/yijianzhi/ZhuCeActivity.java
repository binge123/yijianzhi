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

import com.best.bean.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class ZhuCeActivity extends AppCompatActivity {
    EditText et,et1,et2;
    String number = null;
    String password = null;
    String code = null;
    ImageButton imagebtn;


    int biao = 0;
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
                biao = 0;
                number = et.getText().toString();
                password = et2.getText().toString();
                code = et1.getText().toString();
                //判断账号是否注册过
                BmobQuery<User> bmobs = new BmobQuery<>();
                bmobs.findObjects(ZhuCeActivity.this, new FindListener<User>() {
                    @Override
                    public void onSuccess(List<User> list) {
                        for (User u : list) {
                            if (number.equals(u.getUsername())) {
                                biao = 1;
                                Toast.makeText(ZhuCeActivity.this, "账号已被注册", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                if(biao == 0){
                    //通过verifySmsCode方式可验证该短信验证码
                    BmobSMS.verifySmsCode(ZhuCeActivity.this, number, code, new VerifySMSCodeListener() {

                        @Override
                        public void done(BmobException ex) {
                            // TODO Auto-generated method stub
                            if (ex == null) {//短信验证码已验证成功
                                if(password == null){
                                    Toast.makeText(ZhuCeActivity.this,"请输入密码", Toast.LENGTH_SHORT).show();
                                }else {
                                    User user = new User(number,password);
                                    user.save(ZhuCeActivity.this, new SaveListener() {
                                        @Override
                                        public void onSuccess() {
                                            Toast.makeText(ZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                            ZhuCeActivity.this.finish();
                                        }

                                        @Override
                                        public void onFailure(int i, String s) {

                                        }
                                    });
                                }
                            } else {
                                Log.i("smile", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                            }
                        }
                    });
                }

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

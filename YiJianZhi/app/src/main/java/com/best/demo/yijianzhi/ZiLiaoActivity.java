package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.best.bean.CompanyPublish;
import com.best.bean.ResumeTable;
import com.best.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.UpdateListener;
/**
 * Created by Administrator on 2015/11/23.
 */
public class ZiLiaoActivity extends Activity {
    ImageView x_tu;
    String mpaths = null;
    public static final int REQUEST_IMAGE = 2;
    public static final int RESULT_OK = 1;
    public String objectid;
    EditText T1,T2,T3,T4,T5,T6;
    Button md_btn;
    TextView T7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        T1 = (EditText) findViewById(R.id.md_nick1);
        T2 = (EditText) findViewById(R.id.md_sex1);
        T3 = (EditText) findViewById(R.id.md_age1);
        T4 = (EditText) findViewById(R.id.md_address1);
        T5 = (EditText) findViewById(R.id.md_exp1);
        T6 = (EditText) findViewById(R.id.md_zhi1);
        T7 = (TextView) findViewById(R.id.md_money1);
        x_tu = (ImageView) findViewById(R.id.x_tu);
        md_btn = (Button) findViewById(R.id.md_btn);
        final BmobQuery<User> bmob1 = new BmobQuery<>();
        bmob1.addWhereEqualTo("username",getIntent().getStringExtra("zhanghao"));
        bmob1.findObjects(this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> object) {
                // TODO Auto-generated method stub
                Toast.makeText(ZiLiaoActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                for (User gameScore : object) {
                    T1.setText(gameScore.getNick());
                    T2.setText(gameScore.getUser_sex());
                    T3.setText(gameScore.getUser_age());
                    T4.setText(gameScore.getUser_address());
                    T5.setText(gameScore.getRenzheng());
                    T6.setText(gameScore.getPhone_number());
                    T7.setText(gameScore.getLast_login_time());
                    objectid = gameScore.getObjectId();

                }
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
            }
        });



        md_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User gameScore = new User();
                gameScore.setNick(T1.getText().toString());
                gameScore.setUser_sex(T2.getText().toString());
                gameScore.setUser_age(T3.getText().toString());
                gameScore.setUser_address(T4.getText().toString());
                gameScore.setRenzheng(T5.getText().toString());
                gameScore.setPhone_number(T6.getText().toString());
                gameScore.setLast_login_time(T7.getText().toString());
                gameScore.update(getApplicationContext(), objectid, new UpdateListener() {

                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        Toast.makeText(ZiLiaoActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                        ZiLiaoActivity.this.finish();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        // TODO Auto-generated method stub
                        Toast.makeText(ZiLiaoActivity.this, "更新失败", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

//        x_tu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ZiLiaoActivity.this, MultiImageSelectorActivity.class);
//
//                // whether show camera
//                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
//
//                // max select image amount
//                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
//
//                // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
//                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
//
//                startActivityForResult(intent, REQUEST_IMAGE);
//            }
//        });
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE) {
//            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
//            mpaths = path.get(0);
//            x_tu.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
//        }
    }
}

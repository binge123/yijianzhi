package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.best.bean.IdentificationTable;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;

import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.ValueEventListener;
import me.nereo.imagechoose.MultiImageSelectorActivity;

/**
 * Created by dell2 on 2015/12/4.
 */
public class RenZhengActivity extends Activity {
    Button btn;
    ImageView iv;
    EditText et1,et2;
    int biao = 0;
    String mpaths = null;
    String companyname = null,phone_number = null;
    public static final int REQUEST_IMAGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mosp = getSharedPreferences("zhuan", Context.MODE_PRIVATE);
        if("qiu".equals(mosp.getString("zhuan",""))){
            setContentView(R.layout.activity_person_renzheng);
        }else {
            setContentView(R.layout.activity_company_renzheng);
        }

        btn = (Button) findViewById(R.id.btn);
        iv = (ImageView) findViewById(R.id.image);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RenZhengActivity.this, MultiImageSelectorActivity.class);

                // whether show camera
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

                // max select image amount
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

                // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });


    }
    //取消按钮
    public void changeButton(View v){
        iv.setVisibility(View.GONE);
    }
    //上传到数据库保存
    public void saveButton(View v){
        biao = 0;
        companyname = et1.getText().toString();
        phone_number = et2.getText().toString();
        if("".equals(companyname) || "".equals(phone_number) || "".equals(mpaths)){
            Toast.makeText(RenZhengActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
            biao = 1;
        }
        if(biao == 0){
//            Log.i("tests","biao");
            BmobProFile.getInstance(RenZhengActivity.this).upload(mpaths, new UploadListener() {
                @Override
                public void onSuccess(String s, String s1, BmobFile bmobFile) {
//                    Log.i("tests","biao1");
                    SharedPreferences sp = getSharedPreferences("username", Context.MODE_PRIVATE);
                    IdentificationTable it = new IdentificationTable(sp.getString("username",""),companyname,bmobFile.getUrl(),"0",phone_number);
                    it.save(RenZhengActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
//                            Log.i("tests","biao2");
                            Toast.makeText(RenZhengActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            RenZhengActivity.this.finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }

                @Override
                public void onProgress(int i) {

                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iv.setVisibility(View.VISIBLE);
        if(requestCode == REQUEST_IMAGE){
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            mpaths = path.get(0);
            iv.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final BmobRealTimeData rtd = new BmobRealTimeData();
        rtd.start(RenZhengActivity.this, new ValueEventListener() {
            @Override
            public void onConnectCompleted() {
                rtd.subTableUpdate("User");
            }

            @Override
            public void onDataChange(JSONObject jsonObject) {

            }
        });

    }
}

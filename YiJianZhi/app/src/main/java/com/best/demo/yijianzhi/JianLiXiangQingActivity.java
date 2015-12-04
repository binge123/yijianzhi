package com.best.demo.yijianzhi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.best.bean.CompanyPublish;
import com.best.bean.RecruitTable;
import com.best.bean.User;
import com.best.utils.CreateMD;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

public class JianLiXiangQingActivity extends Activity {


    List<RecruitTable> lists = new ArrayList<>();
    TextView T1,T2,T3,T4,T5,T6,T7,T8,T9,T10;
//    String name,work_exp,work_time,expect_money,resumeid,age,sex,position,address,remark;
    String channelid = null;
    public static String baseurl = "https://api.tuisong.baidu.com/rest/3.0/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianlixiangqing);
        T1 = (TextView) findViewById(R.id.x_name1);
        T2 = (TextView) findViewById(R.id.x_sex1);
        T3 = (TextView) findViewById(R.id.x_age1);
        T4 = (TextView) findViewById(R.id.x_address1);
        T5 = (TextView) findViewById(R.id.x_exp1);
        T6 = (TextView) findViewById(R.id.x_zhi1);
        T7 = (TextView) findViewById(R.id.x_money1);
        T8 = (TextView) findViewById(R.id.x_time1);
//        T9 = (TextView) findViewById(R.id.x_bei1);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String didian = intent.getStringExtra("didian");

        Bmob.initialize(this,"18b52c81a4fcfaf1f5cf2418f4ac9bc5");
        BmobQuery<RecruitTable> bmob1 = new BmobQuery<>();
//        bmob1.addWhereEqualTo("username",new LoginActivity().username);
        bmob1.addWhereEqualTo("title", title);
        bmob1.addWhereEqualTo("work_address",didian);
        bmob1.setLimit(10);
        bmob1.findObjects(this, new FindListener<RecruitTable>() {
            @Override
            public void onSuccess(List<RecruitTable> list) {
                for (RecruitTable i:list){
                    T1.setText(i.getTitle());//标题
                    T2.setText(i.getPosition());//职位
                    T3.setText(i.getWork_address());//工作地址
                    T4.setText(i.getWork_money());//工资
                    T5.setText(i.getJiesuan());//结算方式
                    T6.setText(i.getContacts());//联系人
                    T7.setText(i.getOver_time());//结束时间
                    T8.setText(i.getPosition_desc());//工作描述
                }

            }

            @Override
            public void onError(int i, String s) {

            }
        });
        //消息推送，接收channelid
        channelid = getIntent().getStringExtra("chanelid");

    }

    public void tellButton(View v){
        //把channelid保存到数据库
        User user = new User(channelid);
        user.save(JianLiXiangQingActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.i("aaaaa", "onSuccess");
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });



        Log.i("fengfeng", "sendMessage");
        //3935128846849032787
        String msg = "{\"title\":\"sssss\",\"description\":\"hello world\",\"custom_content\":{\"qid\":\"123\"}}";
        final String url = baseurl+"push/single_device";
        final HashMap<String,String> map = new HashMap<>();
        map.put("apikey","e8WobuHCGgTjWGq8VEVHAHzg");
        final long time = System.currentTimeMillis()/1000L;
        map.put("timestamp",time+"");
        map.put("channel_id","4275756795151467029");
        map.put("msg",msg);
        map.put("msg_type","1");
        String kv = "";
        Collection<String> keyset=  map.keySet();
        List<String> list = new ArrayList<String>(keyset);
        //对key键值按字典升序排序
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            kv=kv+list.get(i)+"="+map.get(list.get(i));
        }

        String res =  "POST"+url+kv+"GsyMVE38Hj3CFkosVavGeCjXNFCBn5Rv";
        Log.i("fengfeng",res);
        String  nomd5 = URLEncoder.encode(res);
        //urlencode();
        final String sign = CreateMD.getMd5(nomd5);
        map.put("sign",sign);

        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String burl = url;
                    URL httpurl = new URL(burl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) httpurl.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                    httpURLConnection.setRequestProperty("User-Agent","BCCS_SDK/3.0 (Linux; #1 SMP Fri Nov 22 03:15:09 UTC 2013; x86_64) PHP/5.3.29 (Baidu Push SDK for PHP v3.0.0) apache2handler/Unknown(Apache) ZEND/2.3.0");
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    Iterator<HashMap.Entry<String,String>> iterator2 = map.entrySet().iterator();
                    String query = "";

                    while(iterator2.hasNext()){
                        HashMap.Entry<String,String> entry = iterator2.next();
                        query = query+entry.getKey()+"="+entry.getValue()+"&";
                    }

                    String endquery = query.substring(0, query.length() - 1);
                    Log.i("fengfeng",endquery);

                    outputStream.write(endquery.getBytes());
                    httpURLConnection.connect();
                    Log.i("fengfeng", httpURLConnection.getResponseCode() + "请求返回码");
                    if (httpURLConnection.getResponseCode() == 200){
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())) ;
                        //char[] cbuf = new char[1024];
                        String line="";
                        StringBuffer responseResult = new StringBuffer();
                        while ((line = bufferedReader.readLine()) != null) {
                            responseResult.append(line);
                        }
                        //String resdata = cbuf.toString();
                        Log.i("fengfeng",responseResult.toString());
                    }else{
                        Log.i("fengfeng",burl);
                        Log.i("fengfeng", httpURLConnection.getResponseCode()+"请求返回码");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("fengfeng", "网络异常");
                }

            }
        }).start();
    }
}

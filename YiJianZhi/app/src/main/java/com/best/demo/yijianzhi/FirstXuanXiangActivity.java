package com.best.demo.yijianzhi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.best.adapter.QiuZuiAdapter;
import com.best.bean.RecruitTable;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class FirstXuanXiangActivity extends AppCompatActivity {
    LinearLayout l1,l2,l3;
    TextView t1,t2,t3;
    int b1 = 0,b2 = 0,b3 = 0;
    public ListView fx_listView;
    public CharSequence a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_xuan_xiang);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        l1 = (LinearLayout)findViewById(R.id.firstxuanxiang_didian);
        l2 = (LinearLayout)findViewById(R.id.firstxuanxiang_feilei);
        l3 = (LinearLayout)findViewById(R.id.firstxuanxiang_shi);
        t1 = (TextView) findViewById(R.id.firstxuanxiang_didian1);
        t2 = (TextView)findViewById(R.id.firstxuanxiang_feilei1);
        t3 = (TextView) findViewById(R.id.firstxuanxiang_shi1);

        fx_listView = (ListView) findViewById(R.id.firstxuanxiang_list);
        lian();
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"兰山区","河东区","罗庄区","兰陵县"};
                builder.setTitle("请选择地点");
                builder.setSingleChoiceItems(items, b1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        a = items[which];
                        b1 = which;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (a == null) {
                            t1.setText(items[0]);
                        }else {
                            t1.setText(a);
                        }
                        lian();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"服务员", "保洁员", "保姆", "保安"};
                builder.setTitle("请选择职业");
                builder.setSingleChoiceItems(items, b2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        b = items[which];
                        b2 = which;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (b == null) {
                            t2.setText(items[0]);
                        }else {
                            t2.setText(b);
                        }
                        lian();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"日结","周结","月结","年结"};
                builder.setTitle("请选择结算时间");
                builder.setSingleChoiceItems(items, b3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        c = items[which];
                        b3 = which;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (c == null) {
                            t3.setText(items[0]);
                        }else {
                            t3.setText(c);
                        }
                        lian();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }
    public void lian() {
        Bmob.initialize(this, "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
        BmobQuery<RecruitTable> bq= new BmobQuery<>();
        bq = bq.addWhereEqualTo("work_address",a);
        bq = bq.addWhereEqualTo("position",b);
        bq = bq.addWhereEqualTo("jiesuan",c);
        bq.findObjects(this, new FindListener<RecruitTable>() {
            @Override
            public void onSuccess(List<RecruitTable> list) {
                fx_listView.setAdapter(new QiuZuiAdapter(getApplicationContext(), list));
                Log.i("aaa", list.get(0).getTitle());
            }

            @Override
            public void onError(int i, String s) {
                Log.i("aaa", i + "" + s);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_xuan_xiang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

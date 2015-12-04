package com.best.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.best.adapter.QiuZuiAdapter;
import com.best.bean.RecruitTable;
import com.best.demo.yijianzhi.JianLiXiangQingActivity;
import com.best.demo.yijianzhi.R;
import com.best.demo.yijianzhi.ZhaoPinXiangQingActivity;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * FirstFragment  求职框架
 * 点击首页切换到求职的内容
 *
 * */
public class QiuZhiFragment extends Fragment{

    LinearLayout l1,l2,l3;
    TextView t1,t2,t3;
    int b1 = 0,b2 = 0,b3 = 0;
    public ListView listView;
    public CharSequence a,b,c;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到求职的布局文件
        View v = inflater.inflate(R.layout.fragment_qiuzhi,container,false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        l1 = (LinearLayout) v.findViewById(R.id.q_didian);
        l2 = (LinearLayout) v.findViewById(R.id.q_feilei);
        l3 = (LinearLayout) v.findViewById(R.id.q_shi);
        t1 = (TextView) v.findViewById(R.id.q_didian1);
        t2 = (TextView) v.findViewById(R.id.q_feilei1);
        t3 = (TextView) v.findViewById(R.id.q_shi1);

        listView = (ListView) v.findViewById(R.id.q_list);
        lian();
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"兰山区", "河东区", "罗庄区", "兰陵县"};
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
                        } else {
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(),JianLiXiangQingActivity.class);
                i.putExtra("id","001");
                startActivity(i);
            }
        });
        return v;
    }
    public void lian() {
        Bmob.initialize(getActivity(), "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
        BmobQuery<RecruitTable> bq= new BmobQuery<>();
        bq = bq.addWhereEqualTo("work_address",a);
        bq = bq.addWhereEqualTo("position",b);
        bq = bq.addWhereEqualTo("jiesuan",c);
        bq.findObjects(getActivity(), new FindListener<RecruitTable>() {
            @Override
            public void onSuccess(List<RecruitTable> list) {
                listView.setAdapter(new QiuZuiAdapter(getActivity(),list));
                Log.i("aaa",list.get(0).getTitle());
            }
            @Override
            public void onError(int i, String s) {
                Log.i("aaa",i+""+s);
            }
        });
    }
}

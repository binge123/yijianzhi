package com.best.demo.yijianzhi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.best.adapter.FirstXuanXiangAdapter;
import com.best.adapter.QiuZuiAdapter;
import com.best.bean.CompanyPublish;
import com.best.bean.RecruitTable;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class FirstXuanXiangActivity extends AppCompatActivity {
    LinearLayout l1,l2,l3;
    TextView t1,t2,t3,toolbar;
    int b1 = 0,b2 = 0,b3 = 0;
    public ListView fx_listView;
    public CharSequence a,b,c;
    PullToRefreshListView mPullToRefreshView;
    private ILoadingLayout loadingLayout;
    List<CompanyPublish> lists= new ArrayList<>();
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多

    private int limit = 10;		// 每页的数据是10条
    private int curPage = 0;		// 当前页的编号，从0开始
    int i=0;
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
        toolbar = (TextView) findViewById(R.id.xuanxiangtoolbar);
        Intent intent = getIntent();
        String s = intent.getStringExtra("toolbar");
        toolbar.setText(s);
        mPullToRefreshView = (PullToRefreshListView) findViewById(R.id.firstxuanxiang_list);
//        fx_listView = (ListView) findViewById(R.id.firstxuanxiang_list);
//        if(i==1){
        Bmob.initialize(this, "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
        initListView();

//
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
                        i=1;
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
                        i=2;
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
                        i=3;
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
        BmobQuery<CompanyPublish> bq= new BmobQuery<>();
        if (i==1){
            bq = bq.addWhereEqualTo("work_address",a);
        }
        if (i==2){
            bq = bq.addWhereEqualTo("work_address",a);
            bq = bq.addWhereEqualTo("title",b);
        }
        if (i==3){
            bq = bq.addWhereEqualTo("work_address",a);
            bq = bq.addWhereEqualTo("title",b);
            bq = bq.addWhereEqualTo("jiesuan",c);
        }


        bq.findObjects(this, new FindListener<CompanyPublish>() {
            @Override
            public void onSuccess(List<CompanyPublish> list) {
                mPullToRefreshView.setAdapter(new FirstXuanXiangAdapter(getApplicationContext(), list));
                Log.i("aaa", list.get(0).getTitle());
            }

            @Override
            public void onError(int i, String s) {
                Log.i("aaa", i + "" + s);
            }
        });
    }
    private void initListView() {
        loadingLayout = mPullToRefreshView.getLoadingLayoutProxy();
        loadingLayout.setLastUpdatedLabel("");
        loadingLayout
                .setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
        loadingLayout
                .setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
        loadingLayout
                .setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
        // //滑动监听
        mPullToRefreshView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem == 0) {
                    loadingLayout.setLastUpdatedLabel("");
                    loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_top_pull));
                    loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_top_refreshing));
                    loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_top_release));
                } else if (firstVisibleItem + visibleItemCount + 1 == totalItemCount) {
                    loadingLayout.setLastUpdatedLabel("");
                    loadingLayout.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
                    loadingLayout.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
                    loadingLayout.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
                }
            }
        });

        // 下拉刷新监听
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // 下拉刷新(从第一页开始装载数据)
                queryData(0, STATE_REFRESH);
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // 上拉加载更多(加载下一页数据)
                queryData(curPage, STATE_MORE);
            }
        });

        fx_listView = mPullToRefreshView.getRefreshableView();
        // 再设置adapter
        fx_listView.setAdapter(new FirstXuanXiangAdapter(getApplicationContext(), lists));
        queryData(0, STATE_REFRESH);
        fx_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.firstxuanxiang_adapter_text1);
                TextView textView1 = (TextView) view.findViewById(R.id.firstxuanxiang_adapter_didian);

                String title =  textView.getText().toString();
                String didian =  textView1.getText().toString();

                Log.i("sss","nameaaa"+title);
                Intent intent = new Intent(getApplicationContext(),JianLiXiangQingActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("didian",didian);
                startActivity(intent);
            }
        });
    }
    /**
     * 分页获取数据
     * @param page	页码
     * @param actionType	ListView的操作类型（下拉刷新、上拉加载更多）
     */
    private void queryData(final int page, final int actionType){
        Log.i("bmob", "pageN:"+page+" limit:"+limit+" actionType:"+actionType);

        BmobQuery<CompanyPublish> query = new BmobQuery<CompanyPublish>();
        query.setLimit(8);			// 设置每页多少条数据
        query.setSkip(page*limit);		// 从第几条数据开始，
        query.findObjects(this, new FindListener<CompanyPublish>() {

            @Override
            public void onSuccess(List<CompanyPublish> list) {
                if (list.size() > 0) {
                    if (actionType == STATE_REFRESH) {
                        // 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
                        curPage = 0;
                        lists.clear();
                    }

                    // 将本次查询的数据添加到bankCards中
                    for (CompanyPublish td : list) {
                        lists.add(td);
                    }

                    // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
                    curPage++;
                    showToast("第" + (page + 1) + "页数据加载完成");
                } else if (actionType == STATE_MORE) {
                    showToast("没有更多数据了");
                } else if (actionType == STATE_REFRESH) {
                    showToast("没有数据");
                }
                mPullToRefreshView.onRefreshComplete();
//                mPullToRefreshView.setAdapter(new FirstXuanXiangAdapter(getApplicationContext(), list));

            }



            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                showToast("查询失败:" + arg1);
                mPullToRefreshView.onRefreshComplete();
            }
        });
    }
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

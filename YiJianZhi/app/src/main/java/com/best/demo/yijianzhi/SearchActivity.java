package com.best.demo.yijianzhi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.best.adapter.FirstListViewAdapter;
import com.best.bean.RecruitTable;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class SearchActivity extends Activity {

    private GridView gv,gv2;
    private ImageView img;
    public EditText sk;
    RelativeLayout rl;
    ListView sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        img = (ImageView)findViewById(R.id.s_image);
        gv2 = (GridView)findViewById(R.id.gridview2);
        gv2.setAdapter(new GridViewAdapter2());
        gv = (GridView)findViewById(R.id.gridview1);
        gv.setAdapter(new GridViewAdapter());
        sk = (EditText) findViewById(R.id.s_kuang);
        rl = (RelativeLayout) findViewById(R.id.s_rl);
        sl = (ListView) findViewById(R.id.s_list);
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchActivity.this, position + "", Toast.LENGTH_LONG).show();
            }
        });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchActivity.this, position + "", Toast.LENGTH_LONG).show();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
    }
    class GridViewAdapter extends BaseAdapter{
        private int[]images={R.drawable.datang,R.drawable.yuesao,R.drawable.fachuandan,R.drawable.wuliu,
                R.drawable.chushi,R.drawable.it,R.drawable.fuwuyuan,R.drawable.baoan,R.drawable.kuaican};

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv = null;
            if (convertView == null){
                System.out.println("contentview==null");
                iv = new ImageView(SearchActivity.this);
                iv.setLayoutParams(new GridView.LayoutParams(110,65));
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setPadding(2,2,2,2);
            }else{
                iv = (ImageView)convertView;
            }
            iv.setImageResource(images[position]);
            return iv;
        }
    }


    class GridViewAdapter2 extends BaseAdapter{
        private int[]images={R.drawable.bei_jing,R.drawable.fachuandan,R.drawable.wu,
                R.drawable.chushi,R.drawable.kaifa,R.drawable.php};

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv = null;
            if (convertView == null){
                System.out.println("contentview==null");
                iv = new ImageView(SearchActivity.this);
                iv.setLayoutParams(new GridView.LayoutParams(110,65));
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setPadding(2,2,2,2);
            }else{
                iv = (ImageView)convertView;
            }
            iv.setImageResource(images[position]);
            return iv;
        }
    }
    public void sousuo(View v){
        String sa = sk.getText().toString();
        rl.setVisibility(View.GONE);
        BmobQuery<RecruitTable> sbq = new BmobQuery<>();
        sbq.addWhereMatches("name", sa);
        sbq.findObjects(this, new FindListener<RecruitTable>() {
            @Override
            public void onSuccess(List<RecruitTable> list) {
                sl.setAdapter(new FirstListViewAdapter(SearchActivity.this,list));
            }
            @Override
            public void onError(int i, String s) {
                Log.i("asdz",s+i);
            }
        });
    }
}

package com.best.demo.yijianzhi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {

    private GridView gv,gv2;
    private ImageView img;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        img = (ImageView)findViewById(R.id.s_image);
        text = (TextView)findViewById(R.id.s_text);
        gv2 = (GridView)findViewById(R.id.gridview2);
        gv2.setAdapter(new GridViewAdapter2());
        gv = (GridView)findViewById(R.id.gridview1);
        gv.setAdapter(new GridViewAdapter());
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
}

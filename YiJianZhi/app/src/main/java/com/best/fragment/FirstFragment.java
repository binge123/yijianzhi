package com.best.fragment;

/**
 * Created by dell2 on 2015/11/18.
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.best.adapter.FirstListViewAdapter;

import android.widget.TextView;

import com.best.bean.CompanyPublish;

import com.best.bean.RecruitTable;
import com.best.demo.yijianzhi.CityActivity;
import com.best.demo.yijianzhi.FirstXuanXiangActivity;
import com.best.demo.yijianzhi.JianLiXiangQingActivity;
import com.best.demo.yijianzhi.R;
import com.best.demo.yijianzhi.SearchActivity;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * FirstFragment  首页框架
 * 点击首页切换到首页的内容
 *内容：
 *    1、banner轮播：ViewPager
 *    2、GridView选项
 *    3、推荐招聘：ListView选项
 *
 *
 * */
public class FirstFragment extends Fragment implements View.OnClickListener{
    ViewPager vp;
    View v1,v2;
    List<View> list = new ArrayList<>();
    ListView lv;
    PtrClassicFrameLayout ptr;
    LinearLayout remen,fujin,xianshi,gengduo;
    TextView  imageView;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private ImageHandler handler = new ImageHandler(new WeakReference<FirstFragment>(this));
    List<RecruitTable> listviews = new ArrayList<RecruitTable>();
    TextView textView;
    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听
    AMapLocationListener mLocationListener = null;
    LinearLayout line;

    //定义一个标识
    int i = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建视图v，找到首页的布局文件
        View v = inflater.inflate(R.layout.fragment_first,container,false);
        //首页banner轮播
        vp =(ViewPager)v.findViewById(R.id.first_banner);
        vp.setCurrentItem(Integer.MAX_VALUE / 2);//默认在中间，使用户看不到边界
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);

        v1 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner1_layout,null);
        v2 =LayoutInflater.from(getActivity()).inflate(R.layout.first_banner2_layout,null);
        list.add(v1);
        list.add(v2);
        vp.setAdapter(new MyViewPager());


        remen = (LinearLayout)v.findViewById(R.id.first_remen);
        fujin = (LinearLayout)v.findViewById(R.id.first_fujin);
        xianshi = (LinearLayout)v.findViewById(R.id.first_xianshi);
        gengduo = (LinearLayout)v.findViewById(R.id.first_gengduo);
        remen.setOnClickListener(this);
        fujin.setOnClickListener(this);
        xianshi.setOnClickListener(this);
        gengduo.setOnClickListener(this);


        lv =(ListView)v.findViewById(R.id.first_listview);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                RecruitTable companyPublish = listviews.get(position);

                String title =companyPublish.getTitle();
                String didian =companyPublish.getWork_address();
                Intent i = new Intent(getActivity(), JianLiXiangQingActivity.class);
                i.putExtra("title", title);
                i.putExtra("didian",didian);
                startActivity(i);
            }
        });





        Bmob.initialize(getActivity(), "18b52c81a4fcfaf1f5cf2418f4ac9bc5");
        final BmobQuery<RecruitTable> bq= new BmobQuery<>();
        ptr = (PtrClassicFrameLayout) v.findViewById(R.id.rotate_header_list_view_frame);
        ptr.setLastUpdateTimeRelateObject(this);

        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bq.findObjects(getContext(), new FindListener<RecruitTable>() {
                            @Override
                            public void onSuccess(List<RecruitTable> lists) {
                                Log.i("test", "成功");
                                lv.setAdapter(new FirstListViewAdapter( getContext(),lists));
                                listviews=lists;
                            }

                            @Override
                            public void onError(int i, String s) {

                            }
                        });
                        ptr.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        ptr.setResistance(1.7f);
        ptr.setRatioOfHeaderHeightToRefresh(1.2f);
        ptr.setDurationToClose(200);
        ptr.setDurationToCloseHeader(1000);
        // default is false
        ptr.setPullToRefresh(false);
        // default is truep
        ptr.setKeepHeaderWhenRefresh(true);
        ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptr.autoRefresh();
            }
        }, 100);


        imageView = (TextView)v.findViewById(R.id.t_sousuo);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        //toolbar上的定位TextView
        textView = (TextView) v.findViewById(R.id.t_textView);
        textView.setText("临沂");
        //接收选择的城市
        Intent intent1 = getActivity().getIntent();
        i = intent1.getIntExtra("index",0);
        textView.setText(intent1.getStringExtra("test"));

        if(i == 0){
            //声明定位回调监听器
            mLocationListener = new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation amapLocation) {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息
                            amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                            amapLocation.getLatitude();//获取经度
                            amapLocation.getLongitude();//获取纬度
                            amapLocation.getAccuracy();//获取精度信息
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(amapLocation.getTime());
                            df.format(date);//定位时间
                            amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                            amapLocation.getCountry();//国家信息
                            amapLocation.getProvince();//省信息
                            amapLocation.getDistrict();//城区信息
                            amapLocation.getRoad();//街道信息
                            amapLocation.getCityCode();//城市编码
                            amapLocation.getAdCode();//地区编码
                            textView.setText(amapLocation.getCity());//城市信息
                        } else {
                            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + amapLocation.getErrorCode() + ", errInfo:"
                                    + amapLocation.getErrorInfo());
                        }
                    }
                }
            };
        }

        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();


        //toolbar上定位的点击事件
        line = (LinearLayout) v.findViewById(R.id.line);
        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CityActivity.class);
                startActivity(intent);
            }
        });





        return v;
    }

    @Override
    public void onClick(View v) {
        int i =v.getId();
        Intent intent = new Intent(getActivity(), FirstXuanXiangActivity.class);
        TextView textView ;
        if(i==R.id.first_remen){
            textView = (TextView) v.findViewById(R.id.remenzhaopin);
            intent.putExtra("toolbar", textView.getText());
        }else if(i==R.id.first_fujin){
            textView = (TextView) v.findViewById(R.id.fujinzhaopin);
            intent.putExtra("toolbar", textView.getText());
        }else if(i==R.id.first_xianshi){
            textView = (TextView) v.findViewById(R.id.xianshizhaopin);
            intent.putExtra("toolbar", textView.getText());
        }else if(i==R.id.first_gengduo){
            textView = (TextView) v.findViewById(R.id.genduozhaopin);
            intent.putExtra("toolbar", textView.getText());
        }
        startActivity(intent);

    }



    class MyViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v,0);
            return v;
        }
    }

    /**
     * 下面是ViewPager的轮播效果
     */
    private static class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;
        /**
         * 轮播间隔时间
         */
        protected static final long MSG_DELAY = 3000;

        /**
         * 使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
         */
        private WeakReference<FirstFragment> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<FirstFragment> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("LOG_TAG", "receive message " + msg.what);
            FirstFragment activity = weakReference.get();
            if (activity == null) {
                /**
                 *  Activity已经回收，无需再处理UI了
                 * */
                return;
            }
            /**
             * 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
             * */
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    if (currentItem > 2) {
                        currentItem = 0;
                    }
                    activity.vp.setCurrentItem(currentItem);
                    /**
                     * 准备下次播放
                     * */
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    /**
                     * 只要不发送消息就暂停了
                     * */
                    break;
                case MSG_BREAK_SILENT:
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    /**
                     * 记录当前的页号，避免播放的时候页面显示不正确。
                     * */
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }


}

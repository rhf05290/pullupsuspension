package com.runtai.pullupsuspension;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.runtai.pullupsuspension.adapter.CommonTabPagerAdapter;
import com.runtai.pullupsuspension.bean.ADInfo;
import com.runtai.pullupsuspension.fragment.HomeTabViewFragment;
import com.runtai.pullupsuspension.utils.ViewFactory;
import com.runtai.pullupsuspension.view.CycleViewPager;
import com.runtai.pullupsuspension.view.MyGridView;
import com.runtai.pullupsuspension.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CommonTabPagerAdapter.TabPagerListener, CycleViewPager.ImageCycleViewListener {

    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private List<ImageView> views = new ArrayList<ImageView>();

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
    private CycleViewPager cycleViewPager;
    private CommonTabPagerAdapter adapter;
    private SlidingTabLayout sliding_tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        sliding_tabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        setSlideTabLayout();

        List<String> title_data = new ArrayList<>();
        title_data.clear();
        for (int i = 0; i < 8; i++) {
//            String menuName = data.get(i).getMenuName();
            title_data.add(i + "段");
        }
        if (title_data.size() > 0) {
            adapter = new CommonTabPagerAdapter(getSupportFragmentManager()
                    , title_data.size(), title_data, this);
        }

        adapter.setListener(this);
        viewpager.setAdapter(adapter);
        sliding_tabs.setViewPager(viewpager);//最后调用此方法
        // 放置广告的位置
        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.cycleViewPager);
        initcycleViewPager();

//        MyGridView  gv_homepage_sort = (MyGridView) findViewById(R.id.gv_homepage_sort);
//        List<String> sortdata = new ArrayList<>();
//        sortdata.add("恩恩好的");
//        sortdata.add("恩恩好的");
//        sortdata.add("恩恩好的");
//        gv_homepage_sort.setAdapter(new HomeActiveSortAdapter(getActivity(), sortdata));

    }

    /**
     * 设置首页底部的slideTablayout
     */
    private void setSlideTabLayout() {
        sliding_tabs.setSelectedIndicatorColors(Color.parseColor("#FE6672"));//滑动条颜色
        sliding_tabs.setTabTitleTextSize(16);
        sliding_tabs.setDividerColors(Color.parseColor("#ffececec"));//设置分割线颜色
        sliding_tabs.setTitleTextColor(Color.parseColor("#FE6672"), Color.parseColor("#ff4d4b4d"));//标题字体颜色
        sliding_tabs.setDistributeEvenly(true); //均匀平铺选项卡
//        sliding_tabs.setViewPager(viewpager);//最后调用此方法
    }


    /**
     * 轮播图设置
     */
    protected void initcycleViewPager() {
//        this.dtLunBoAD = dtLunBoAD;
//        if (views.size() > 0) {
//            return;
//        }
//
//        for (int i = 0; i < dtLunBoAD.size(); i++) {
//            ADInfo info = new ADInfo();
//            info.setUrl(dtLunBoAD.get(i).getMenuImg());
//            info.setContent("图片-->" + i);
//            infos.add(info);
//        }

        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i);
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this,
                infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i)
                    .getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory
                .getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, this);
        // 设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(5000);
        // 设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    @Override
    public Fragment getFragment(int position) {
        return HomeTabViewFragment.newInstance(position);
    }

    @Override
    public void onImageClick(ADInfo info, int position, View imageView) {
        
    }
}

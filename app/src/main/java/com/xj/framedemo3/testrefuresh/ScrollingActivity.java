package com.xj.framedemo3.testrefuresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.xj.framedemo3.R;
import com.xj.framedemo3.fragment.mFragmentPagerAdapter;
import com.xj.mainframe.configer.APPLog;
import com.xj.mainframe.utils.StatusBarUtil;
import com.xj.mainframe.view.otherView.TitleView;

public class ScrollingActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private mFragmentPagerAdapter mFragPAdapter;
    private TitleView title_view;
private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initView();
    }

    private void initView() {
        handler.sendEmptyMessageDelayed(1,10000);
        title_view = (TitleView) findViewById(R.id.title_view);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mFragPAdapter = new mFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mFragPAdapter);
        tabLayout.setupWithViewPager(viewPager);

        title_view=(TitleView)findViewById(R.id.title_view);
        new TitleView.TitleModel().setTitle("测试布局").Builder(title_view);

        APPLog.e("mMainThreadid 11",android.os.Process.myTid());
        StatusBarUtil.darkMode(this);
        StatusBarUtil.setPaddingSmart(this,title_view);
    }
}

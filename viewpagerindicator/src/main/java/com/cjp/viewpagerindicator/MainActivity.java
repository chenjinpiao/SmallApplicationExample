package com.cjp.viewpagerindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.cjp.viewpagerindicator.fragment.VpSimpleFragment;
import com.cjp.viewpagerindicator.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private List<String> mTitles = Arrays.asList("短信1","推荐2","收藏3","短信4","推荐5","收藏6","短信7","推荐8","收藏9");
    private List<VpSimpleFragment> mContents = new ArrayList<VpSimpleFragment>();
    private FragmentPagerAdapter fragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();

        mIndicator.setVisibleTabCount(5);
        mIndicator.setTabItemTitles(mTitles);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mIndicator.setViewPager(mViewPager,0);


    }

    private void initDatas() {
        for (String title :mTitles){
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(title);
            mContents.add(fragment);
        }
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };
    }

    private void initViews() {
        mViewPager = findViewById(R.id.id_viewpager);
        mIndicator = findViewById(R.id.id_indicator);
    }
}

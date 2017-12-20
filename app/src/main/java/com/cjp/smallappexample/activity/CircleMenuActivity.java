package com.cjp.smallappexample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cjp.smallappexample.R;
import com.cjp.smallappexample.widgets.CircleMenuLayout;

/**
 * 类名:com.cjp.smallappexample.activity
 * Created by chenjinpiao on 2017/11/14.
 * 作用：xxxxxxxx
 */

public class CircleMenuActivity extends AppCompatActivity {
    private CircleMenuLayout mCircleMenuLayout;//自定义圆盘菜单
    private String[] mItemTexts = new String[]{"放大镜 ", "尺子", "分贝测试仪", "手电筒",
            "计算器", "SOS"};//圆盘菜单显示文字
    private int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal,
            R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
            R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
            R.drawable.home_mbank_6_normal};//圆盘菜单显示图片
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_activity_main);
        //初始化圆盘控件
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        //初始化圆盘控件菜单
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

    }
}

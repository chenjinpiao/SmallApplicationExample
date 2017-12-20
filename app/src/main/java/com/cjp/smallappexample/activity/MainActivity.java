package com.cjp.smallappexample.activity;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import com.cjp.smallappexample.R;
import com.cjp.smallappexample.base.BaseFragment;
import com.cjp.smallappexample.fragment.CommonFrameFragment;
import com.cjp.smallappexample.fragment.CustomFragment;
import com.cjp.smallappexample.fragment.OtherFragment;
import com.cjp.smallappexample.fragment.ThirdPartyFragment;

import java.util.ArrayList;
import java.util.List;
/**
 * 作者：cjp on 2017/11/13 20:37511
 * 作用：主页面
 */
public class MainActivity  extends FragmentActivity{
    final int NOTIFYID = 0x123;            //通知的ID

    private Button button_determine, button_cancel;      //定义对话框按钮
    private AlertDialog dlg;                               //定义对话框

    private RadioGroup mRg_main;
    private List<BaseFragment> mBaseFragment;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
        //发送消息
        setMessage();
    }

    private void setMessage() {
        //获取通知管理器，用于发送通知
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this); // 创建一个Notification对象
        // 设置打开该通知，该通知自动消失
        notification.setAutoCancel(true);
        // 设置显示在状态栏的通知提示信息
        notification.setTicker("安卓课程第一季上线啦！");
        // 设置通知的小图标
        notification.setSmallIcon(R.mipmap.ic_launcher);
        //设置下拉列表中的大图标
        notification.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        // 设置通知内容的标题
        notification.setContentTitle("Android入门第一季！");
        // 设置通知内容
        notification.setContentText("点击查看详情！");
        //设置发送时间
        notification.setWhen(System.currentTimeMillis());
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent(MainActivity.this
                , MessageActivity.class);
        PendingIntent pi = PendingIntent.getActivity(
                MainActivity.this, 0, intent, 0);
        //设置通知栏点击跳转
        notification.setContentIntent(pi);
        //发送通知
        notificationManager.notify(NOTIFYID, notification.build());

    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中常用框架
        mRg_main.check(R.id.rb_common_frame);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_common_frame://常用框架
                    position = 0;
                    break;
                case R.id.rb_thirdparty://第三方
                    position = 1;
                    break;
                case R.id.rb_custom://自定义
                    position = 2;
                    break;
                case R.id.rb_other://其他
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFrament(mContent,to);

        }
    }


    /**
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from,Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }

    }

//    private void switchFrament(BaseFragment fragment) {
//        //1.得到FragmentManger
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //3.替换
//        transaction.replace(R.id.fl_content, fragment);
//        //4.提交事务
//        transaction.commit();
//    }

    /**
     * 根据位置得到对应的Fragment
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new CommonFrameFragment());//常用框架Fragment
        mBaseFragment.add(new ThirdPartyFragment());//第三方Fragment
        mBaseFragment.add(new CustomFragment());//自定义控件Fragment
        mBaseFragment.add(new OtherFragment());//其他Fragment
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);

    }

    /**
     * 判断当单击手机返回按钮时，从手机顶部向下移动对话
     * 再次单击返回按钮,对话框将从中间向底部移动消失对话框
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断如果单击了返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //创建对话框实例
            dlg = new AlertDialog.Builder(this).create();
            dlg.show();                             //显示对话框
            Window window = dlg.getWindow();        //获取对话框窗口
            window.setGravity(Gravity.CENTER);     //此处设置dialog显示在中心位置
            window.setWindowAnimations(R.style.mystyle);      //添加动画
            window.setContentView(R.layout.dialog_layout);   //设置对话框布局文件
            //获取对话框确定按钮
            button_determine = (Button) window.findViewById(R.id.btn_determine);
            //获取对话框取消按钮
            button_cancel = (Button) window.findViewById(R.id.btn_cancel);
            initEvent();                                       //调用初始化事件方法
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 该方法出事对话框中按钮的事件，单击确定按钮退出该应用
     * 单击取消按钮，对话框将移动至底部消失
     */
    private void initEvent() {
        button_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();          //对话框移动到底部消失
                finish();               //关闭当前应用
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();          //对话框移动到底部消失
            }
        });
    }
}

package com.cjp.smallappexample.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cjp.smallappexample.R;
import com.cjp.smallappexample.activity.CircleMenuActivity;
import com.cjp.smallappexample.activity.DrawerAnnoucement;
import com.cjp.smallappexample.activity.QQMenu;
import com.cjp.smallappexample.activity.RainBowActivity;
import com.cjp.smallappexample.activity.Satellite;
import com.cjp.smallappexample.activity.WeiXinPopWindow;
import com.cjp.smallappexample.adapter.ReusableAdapter;
import com.cjp.smallappexample.base.BaseFragment;
import com.cjp.smallappexample.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：cjp on 2017/11/13 20:37
 * 作用：常用框架Fragment
 */
public class CommonFrameFragment extends BaseFragment {

    private ListView mListView;

    private List<String> datas;

    private ReusableAdapter<String> adapter;


    private static final String TAG = CommonFrameFragment.class.getSimpleName();//"CommonFrameFragment"

    @Override
    protected View initView() {
        Log.e(TAG,"常用框架Fragment页面被初始化了...");
        View view = View.inflate(mContext, R.layout.fragment_common_frame,null);
        mListView = (ListView) view.findViewById(R.id.listview);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String data =  datas.get(position);
                switch (data){
                    case "微信左上角弹出菜单":
                        Utils.startNewView(getContext(), WeiXinPopWindow.class);
                        break;
                    case "抽屉式公告":
                        Utils.startNewView(getContext(),DrawerAnnoucement.class);
                        break;
                    case "仿QQ侧滑菜单":
                        Utils.startNewView(getContext(),QQMenu.class);
                        break;
                    case "可收放旋转菜单":
                        Utils.startNewView(getContext(),Satellite.class);
                        break;
                    case "圆盘式菜单":
                        Utils.startNewView(getContext(), CircleMenuActivity.class);
                        break;
                    case "彩虹菜单":
                        Utils.startNewView(getContext(), RainBowActivity.class);
                        break;

                }
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "常用框架Fragment数据被初始化了...");
        //准备数据
        datas =new ArrayList<>();
//        datas.add("微信左上角弹出菜单");
//        datas.add("抽屉式公告");
//        datas.add("仿QQ侧滑菜单");
        datas.add("可收放旋转菜单");
        datas.add("圆盘式菜单");
        datas.add("彩虹菜单");
        //设置适配器
        adapter = new ReusableAdapter((ArrayList) datas,R.layout.reusable_item) {
            @Override
            public void bindView(ViewHolder holder, Object obj) {
                holder.setText(R.id.textView, (String) obj);
            }
        };
        mListView.setAdapter(adapter);
    }
}

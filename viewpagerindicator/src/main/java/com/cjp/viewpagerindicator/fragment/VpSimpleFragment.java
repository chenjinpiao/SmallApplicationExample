package com.cjp.viewpagerindicator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 类名:com.cjp.viewpagerindicator.fragment
 * Created by chenjinpiao on 2017/12/21.
 * 作用：xxxxxxxx
 */

public class VpSimpleFragment extends Fragment {
    private String mTitle;
    public  static final String BUNDLE_TITLE="title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle =getArguments();
        if (bundle != null){
            mTitle =bundle.getString(BUNDLE_TITLE);
        }
        TextView textView = new TextView(getActivity());
        textView.setText(mTitle);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    public static VpSimpleFragment newInstance(String title){

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE,title);
        VpSimpleFragment vpSimpleFragment = new VpSimpleFragment();
        vpSimpleFragment.setArguments(bundle);
        return vpSimpleFragment;
    }
}

package com.cjp.mymvp.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.cjp.mymvp.R;

/**
 * 类名:com.cjp.mymvp.base
 * Created by chenjinpiao on 2018/2/23.
 * 作用：xxxxxxxx
 */

public abstract class BaseActivity extends Activity implements BaseView{

    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }
    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }
    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showErr() {
        showToast(getResources().getString(R.string.api_error_msg));
    }
    @Override
    public Context getContext() {
        return BaseActivity.this;
    }

}

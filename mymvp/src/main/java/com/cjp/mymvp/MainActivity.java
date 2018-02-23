package com.cjp.mymvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cjp.mymvp.base.BaseActivity;

public class MainActivity extends BaseActivity implements MvpView{

    TextView textView;
    MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);


        presenter =new MvpPresenter();
        presenter.attachView(this);
    }

    // button 点击事件调用方法
    public void getData(View view){
        presenter.getData("normal");
    }
    // button 点击事件调用方法
    public void getDataForFailure(View view){
        presenter.getData("failure");
    }
    // button 点击事件调用方法
    public void getDataForError(View view){
        presenter.getData("error");
    }

    @Override
    public void showData(String data) {
        textView.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}

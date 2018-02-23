package com.cjp.mymvp;

import com.cjp.mymvp.base.BasePresenter;

/**
 * 类名:com.cjp.mymvp
 * Created by chenjinpiao on 2018/2/23.
 * 作用：xxxxxxxx
 */

public class MvpPresenter extends BasePresenter<MvpView>{
    /**
     * 获取网络数据
     * @param params
     */
    public  void getData(String params) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        MvpModel.getNetData(params, new MvpCallback<String>() {
        @Override
        public void onSuccess (String data){
            if (isViewAttached()) {
                getView().showData(data);
            }
        }

        @Override
        public void onFailure (String msg){
            if (isViewAttached()) {
                getView().showToast(msg);
            }
        }

        @Override
        public void onError () {
            if (isViewAttached()) {
                getView().showErr();
            }
        }

        @Override
        public void onComplete () {
            if (isViewAttached()) {
                getView().hideLoading();
            }
        }
        });
    }
}

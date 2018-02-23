package com.cjp.mymvp;

import com.cjp.mymvp.base.BaseView;

/**
 * 类名:com.cjp.mymvp
 * Created by chenjinpiao on 2018/2/23.
 * 作用：xxxxxxxx
 */

public interface MvpView extends BaseView{
    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void showData(String data);
}

package com.cjp.mymvp.base;

import android.content.Context;

/**
 * 类名:com.cjp.mymvp.base
 * Created by chenjinpiao on 2018/2/23.
 * 作用：xxxxxxxx
 */

public interface BaseView {
    /**
     * 显示正在加载view
     */
    void showLoading();
    /**
     * 关闭正在加载view
     */
    void hideLoading();
    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);
    /**
     * 显示请求错误提示
     */
    void showErr();
    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();
}

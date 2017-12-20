package com.cjp.smallappexample.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * 类名:com.cjp.smallappexample.animation
 * Created by chenjinpiao on 2017/11/28.
 * 作用：动画实现
 */

public class MyAnimation {
    //菜单进入动画
    public static  void animationIN(ViewGroup viewGroup,int duration){
        for (int i =0;i < viewGroup.getChildCount();i++){
            viewGroup.getChildAt(i).setVisibility(View.VISIBLE);
            viewGroup.getChildAt(i).setFocusable(true);
            viewGroup.getChildAt(i).setClickable(true);
        }
        Animation animation;
        /**
         * 旋转动画
         * RotateAnimation(fromDegrees, toDegrees,pivotXType,, pivotXValue, pivotYType, pivotYValue)
         * fromDegrees 开始旋转角度
         * toDegrees 旋转到的角度
         * pivotXType x轴 参照物
         * pivotXValue x轴 旋转的参考点
         * pivotYTpey Y轴 参照物
         * pivotYValue Y轴 旋转的参考点
         */
        animation = new RotateAnimation(-180,0,Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,1.0f);
        animation.setFillAfter(true);
        animation.setDuration(duration);
        viewGroup.startAnimation(animation);

    }

    //菜单退出动画
    public static  void animationOUT(final ViewGroup viewGroup,int duration,int startOffSet){
        Animation animation;
        animation = new RotateAnimation(0,-180,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,1.0f);
        animation.setFillAfter(true);
        animation.setDuration(duration);
        animation.setStartOffset(startOffSet);
        //设置动画监听事件
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                for (int i =0; i < viewGroup.getChildCount();i++){
                    viewGroup.getChildAt(i).setVisibility(View.GONE);
                    viewGroup.getChildAt(i).setFocusable(false);
                    viewGroup.getChildAt(i).setClickable(false);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewGroup.startAnimation(animation);
    }
}

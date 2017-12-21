package com.cjp.viewpagerindicator.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjp.viewpagerindicator.R;

import java.util.List;

/**
 * 类名:com.cjp.viewpagerindicator.view
 * Created by chenjinpiao on 2017/12/21.
 * 作用：xxxxxxxx
 */

public class ViewPagerIndicator extends LinearLayout {
    private Paint mPaint;
    private Path mPath;
    //三角形宽度
    private int mTriangleWidth;
    //三角形高度
    private int mTriangleHeight;
    //三角形占texteview的比例
    private static final float RADIO_TRIGLE_WIDTH=1/6F;
    //三角形的最大宽度
    private  final int DIMENSION_TRIGLE_WIDTH_MAX= (int) (getScreenWidth()/3*RADIO_TRIGLE_WIDTH);
    //三角形起始位置
    private int mInitTranslationX;
    //三角形偏移位置
    private int mTranslationX;

    //显示tab的数量
    private int mTabVisibleCount;
    //默认显示tab的数量
    private static  final  int COUNT_DEFAULT_TAB=4;

    //保存title
    private List<String> mTitles;

    private static  final  int COLOR_NORMAL=0x77FFFFFF;
    private static  final  int COLOR_HIGHLIGHT=0xFFFFFFFF;


    public ViewPagerIndicator(Context context) {
        this(context,null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array =context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mTabVisibleCount =array.getInt(R.styleable.ViewPagerIndicator_visible_tab_count,COUNT_DEFAULT_TAB);
        if (mTabVisibleCount<0){
            mTabVisibleCount =COUNT_DEFAULT_TAB;
        }
        array.recycle();
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTranslationX+mTranslationX,getHeight()+2);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w/mTabVisibleCount*RADIO_TRIGLE_WIDTH);
        mTriangleWidth = Math.min(mTriangleWidth,DIMENSION_TRIGLE_WIDTH_MAX);
        mInitTranslationX = w/mTabVisibleCount/2 - mTriangleWidth/2;

        initTrigle();
    }

    private void initTrigle() {
        mTriangleHeight = mTriangleWidth/2;

        mPath = new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth/2,-mTriangleHeight);
        mPath.close();
    }

    /**
     *
     * 指示器根据手指进行滚动
     * @param position
     * @param offset
     */
    public void sroll(int position,float offset) {
        int tabWidth = getWidth()/mTabVisibleCount;
        mTranslationX = (int) (position+offset)*tabWidth;

        //容器移动，在tab处于移动至最后一个时
        if (position>(mTabVisibleCount-2) && offset>0 && getChildCount()>mTabVisibleCount && position<getChildCount()-2){
            if (mTabVisibleCount!=1){
                this.scrollTo((int) ((position-(mTabVisibleCount-2))*tabWidth+(tabWidth*offset)),0);
            }else{
                this.scrollTo((int) (position*tabWidth+(tabWidth*offset)),0);
            }
        }
        invalidate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cCount = getChildCount();
        if (cCount==0) return;

        for (int i= 0; i<cCount;i++){
            View view = getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.weight=0;
            layoutParams.width=getScreenWidth()/mTabVisibleCount;
            view.setLayoutParams(layoutParams);
        }
        onItemClickEvent();
    }

    /**
     * 获取屏幕宽
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics metrics =new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 动态添加item
     */
    public void setTabItemTitles(List<String> titles){
        if (titles !=null && titles.size()>0){
            this.removeAllViews();
            mTitles = titles;
            for (String title :titles){
                addView(generateTextView(title));
            }
            onItemClickEvent();
        }
    }

    public  void  setVisibleTabCount(int count){
        mTabVisibleCount = count;
    }

    /**
     * 根据title生成textview
     * @param title
     * @return
     */
    private View generateTextView(String title) {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        layoutParams.width=getScreenWidth()/mTabVisibleCount;
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        textView.setTextColor(COLOR_NORMAL);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
    private OnPageChangeListener onPageChangeListener;
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener){
        onPageChangeListener = onPageChangeListener;
    }
    public interface OnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        void onPageSelected(int position);
        void onPageScrollStateChanged(int state);
    }
    public void setViewPager(ViewPager viewPager, final int pos){
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (onPageChangeListener!=null){
                    onPageChangeListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
                sroll(position,positionOffset);

            }

            @Override
            public void onPageSelected(int position) {
                if (onPageChangeListener != null){
                    onPageChangeListener.onPageSelected(position);
                }
                setColorHighlight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (onPageChangeListener != null){
                    onPageChangeListener.onPageScrollStateChanged(state);
                }

            }
        });
        viewPager.setCurrentItem(pos);
        setColorHighlight(pos);

    }


    /**
     * 重置文本颜色
     */
    private void resetTextColor(){
        for (int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if (view instanceof TextView){
                ((TextView) view).setTextColor(COLOR_NORMAL);
            }
        }
    }
    /**
     * 高亮某个tab文本
     * @param pos
     */
    private void setColorHighlight(int pos){
        resetTextColor();
        View view = getChildAt(pos);
        if (view instanceof TextView){
            ((TextView) view).setTextColor(COLOR_HIGHLIGHT);
        }
    }

    private ViewPager mViewPager;
    /**
     * tab点击事件
     */
    private void onItemClickEvent(){
        int cCount = getChildCount();
        for (int i=0;i<cCount;i++){
            final int j=i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }
}

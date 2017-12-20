package com.cjp.smallappexample.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 类名:com.cjp.smallappexample.adapter
 * Created by chenjinpiao on 2017/12/1.
 * 作用：xxxxxxxx
 */

public abstract class ReusableAdapter<T> extends BaseAdapter {
    private ArrayList<T> mData;
    private int mLayoutRes;           //布局id

    public ReusableAdapter(ArrayList<T> mData , int mLayoutRes){
        this.mLayoutRes = mLayoutRes;
        this.mData = mData;
    }
    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=ViewHolder.bind(viewGroup.getContext(),view,viewGroup, mLayoutRes,i);
        bindView(holder,mData.get(i));
        return holder.getItemView();
    }

    /**
     * 添加一个元素
     */
    public void addItem(T data){
        if (mData==null){
            mData = new ArrayList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    /**
     * 往特定位置添加一个元素
     * @param position
     * @param data
     */
    public void addItem(int position,T data){
        if (mData == null){
            mData = new ArrayList<>();
        }
        mData.add(position,data);
        notifyDataSetChanged();
    }

    /**
     * 删除一个元素
     * @param data
     */
    public void remove(T data) {
        if(mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除指定位置的一个元素
     * @param position
     */
    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        if(mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder{
        private SparseArray<View> mViews; //存储ListView的 item中的View
        private View item; //存放convertView
        private int position; //游标
        private Context mContext; //Context 上下文

        //构造方法，完成相关初始化
        private ViewHolder(Context context,ViewGroup parent,int layoutRes){
            mViews = new SparseArray<>();
            this.mContext = context;
            View convertView = LayoutInflater.from(mContext).inflate(layoutRes,parent,false);
            convertView.setTag(this);
            item = convertView;
        }
        //绑定ViewHolder与item
        public static ViewHolder bind(Context context,View convertView,ViewGroup parent,int laoutRes,int position){
            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder(context,parent,laoutRes);
            }else {
                holder = (ViewHolder) convertView.getTag();
                holder.item = convertView;
            }
            holder.position = position;
            return holder;

        }
        //根据id获取集合中保存的控件
        public <T extends  View> T getView(int id){
            T t = (T) mViews.get(id);
            if (t == null){
                t = item.findViewById(id);
                mViews.put(id,t);
            }
            return t;
        }

        /**
         * 获取当前条目
         */
        public View getItemView() {
            return item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return position;
        }

        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        /**
         * 设置图片
         */
        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }


        /**
         * 设置点击监听
         */
        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        /**
         * 设置可见
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }

        /**
         * 设置标签
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }
    }

    public abstract void bindView(ViewHolder holder,T obj);


}

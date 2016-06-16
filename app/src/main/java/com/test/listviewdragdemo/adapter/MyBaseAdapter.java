package com.test.listviewdragdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 13798 on 2016/6/10.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter{
    protected Context mContext;
    protected List<T> mList;

    public MyBaseAdapter(Context context, List<T> lists){
        mContext = context;
        mList = lists;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getView(position,convertView);
    }

    public abstract View getView(int position,View convertView);

}

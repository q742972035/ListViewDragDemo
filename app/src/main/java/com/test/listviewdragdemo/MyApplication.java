package com.test.listviewdragdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 13798 on 2016/6/13.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}

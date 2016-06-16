package com.test.listviewdragdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.test.listviewdragdemo.adapter.MyAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.lv)
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        initString();
        MyAdapter my = new MyAdapter(this,mLists,lv);
        lv.setAdapter(my);
    }


    private List<String> mLists;
    private void initString() {
        mLists = new ArrayList<>();
        for (int i = 1; i <= 30; i++)
            mLists.add("第" + i + "项");
    }
}

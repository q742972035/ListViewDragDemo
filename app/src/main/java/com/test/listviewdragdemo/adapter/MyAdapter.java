package com.test.listviewdragdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.listviewdragdemo.R;
import com.test.listviewdragdemo.X.X;
import com.test.listviewdragdemo.view.ViewWithDraged;

import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 13798 on 2016/6/10.
 */
public class MyAdapter extends MyBaseAdapter<String> implements AbsListView.OnScrollListener{
    /**
     * 设置开关状态，默认为关闭
     */
    private HashMap<Integer,Boolean> state;
    /**
     * 判断是否在滑动
     */
    private ListView mListView;

    public MyAdapter(Context context, List<String> lists, ListView listView) {
        super(context, lists);
        state = new HashMap<>();
        for (int i = 0; i < lists.size(); i++) {
            state.put(i, false);
        }
        listView.setOnScrollListener(this);
        mListView = listView;
    }

    @Override
    public View getView(final int position, View convertView) {
        final String item = getItem(position);
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(mContext,R.layout.item_list,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (state.get(position)){
            viewHolder.tv_content.setText((CharSequence) item+"打开。");
            viewHolder.vwd.setOpen();
        }else {
            viewHolder.tv_content.setText((CharSequence) item+"关闭。");
            viewHolder.vwd.setClose();
        }

        viewHolder.vwd.setStateListener(new ViewWithDraged.StateListener() {
            @Override
            public void open() {
                state.put(position,true);
                viewHolder.tv_content.setText((CharSequence) item+"打开。");
            }

            @Override
            public void close() {
                state.put(position,false);
                viewHolder.tv_content.setText((CharSequence) item+"关闭。");
            }

            @Override
            public void sliding() {
            }
        });


        if (isScrolling ){
            Animation animation = new ScaleAnimation(0,1.0f,0,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(700);
            convertView.startAnimation(animation);
        }

        viewHolder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state.remove(position);
                HashMap<Integer, Boolean> newState = new HashMap<Integer, Boolean>();
                int count = state.size();
                for (int i = 0; i < count; i++) {
                    for (int j = i; j < mList.size(); j++) {
                        if (state.get(j) == null)
                            continue;
                        newState.put(i, state.get(j));
                        state.remove(j);
                        break;
                    }
                }
                state = newState;
                mList.remove(position);

                notifyDataSetChanged();
            }
        });
        viewHolder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, (CharSequence) mList.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    private boolean isScrolling = false;
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL)
            isScrolling = true;
        else if (scrollState == SCROLL_STATE_IDLE)
            isScrolling = false;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    private static final class ViewHolder {
        @ViewInject(R.id.tv_content)
        TextView tv_content;
        @ViewInject(R.id.vwd)
        ViewWithDraged vwd;
        @ViewInject(R.id.btn_1)
        Button btn1;
        @ViewInject(R.id.btn_2)
        Button btn2;

        public ViewHolder(View view) {
            X.inject(this,view);
        }
    }
}

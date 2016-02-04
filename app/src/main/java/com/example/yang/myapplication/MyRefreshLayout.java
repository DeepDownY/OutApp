package com.example.yang.myapplication;

import android.content.Context;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;

/**
 * Created by Yang on 2016/1/31.
 */
public class MyRefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener{

    private RecyclerView recyclerView = null;

    private int touchSlop;



    public MyRefreshLayout(Context context) {
        super(context);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}

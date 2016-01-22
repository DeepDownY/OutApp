package com.example.yang.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yang on 2016/1/22.
 */
public class MyAdapter extends BaseAdapter {
    private Context context = null;
    private int resources;
    private ArrayList<HashMap<String,Object>> list=null;
    private String[] from;
    private int[] to;

    public MyAdapter(Context context, int resources,
                     ArrayList<HashMap<String, Object>> list, String[] from, int[] to) {
        super();
        this.context = context;
        this.resources = resources;
        this.list = list;
        this.from = from;
        this.to = to;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

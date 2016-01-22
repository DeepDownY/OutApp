package com.example.yang.myapplication;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


    class ViewHolder{
        public ImageView img = null;
        public TextView name=null,weight=null,time=null;
        public ViewHolder(View convertView){
            img = (ImageView)convertView.findViewById(to[0]);
            name = (TextView)convertView.findViewById(to[1]);
            weight= (TextView)convertView.findViewById(to[2]);
            time = (TextView)convertView.findViewById(to[3]);
        }
    }
    class ImageListener implements View.OnClickListener{
        private int position;

        public ImageListener(int position){
            this.position=position;
        }
        public void onClick(View v){
            String str=list.get(position).get(from[1]).toString();
            Toast.makeText(context,str+" is Clicked" , Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView=inflater.inflate(resources,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.img.setBackground((Drawable)list.get(position).get(from[0]));
        viewHolder.name.setText((String) (list.get(position).get(from[1])));
        viewHolder.weight.setText((String)(list.get(position).get(from[2])));
        viewHolder.time.setText((String)(list.get(position).get(from[3])));
        viewHolder.img.setOnClickListener(new ImageListener(position));
        return convertView;
    }
}

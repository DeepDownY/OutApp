package com.example.yang.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

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
    private ImageLoader loader;
    String url = "http://7vijy3.com1.z0.glb.clouddn.com/u%3D510514586%2C3474110816%26fm%3D11%26gp%3D0.jpg";


    public MyAdapter(Context context, int resources,
                     ArrayList<HashMap<String, Object>> list, String[] from, int[] to) {
        super();
        this.context = context;
        this.resources = resources;
        this.list = list;
        this.from = from;
        this.to = to;

        loader = new ImageLoader(MyAppliction.getHttpQueues()
                ,new BitmapCache());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder{
        public NetworkImageView img = null;
        public TextView name=null,weight=null,time=null;
        public ViewHolder(View convertView){
            img = (NetworkImageView)convertView.findViewById(to[0]);
            name = (TextView)convertView.findViewById(to[1]);
            weight= (TextView)convertView.findViewById(to[2]);
            time = (TextView)convertView.findViewById(to[3]);
        }
    }
    class ImageListener implements ImageLoader.ImageListener{


        @Override
        public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {

        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    }

    class ViewListener implements View.OnClickListener{
        private int position;

        public ViewListener(int position){
            this.position = position;
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
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.img.setImageUrl(String.valueOf(list.get(position).get(from[0])),loader);
        viewHolder.img.setErrorImageResId(R.drawable.ic_menu_camera);
        viewHolder.img.setDefaultImageResId(R.drawable.ic_menu_gallery);

        viewHolder.name.setText((String) (list.get(position).get(from[1])));
        viewHolder.weight.setText((String)(list.get(position).get(from[2])));


        convertView.setOnClickListener(new ViewListener(position));
        return convertView;
    }

}

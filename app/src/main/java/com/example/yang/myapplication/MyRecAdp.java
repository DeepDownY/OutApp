package com.example.yang.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by Yang on 2016/1/28.
 */
public class MyRecAdp extends RecyclerView.Adapter<MyViewHolder>  {

    private int resources;
    private ArrayList<HashMap<String,Object>> list=null;
    private String[] from = null;
    private int[] to = null;
    private ImageLoader loader;
    LayoutInflater inflater = null;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public MyRecAdp(Context context,ArrayList<HashMap<String,Object>> list,
                    int resources,String[] from,int[] to) {

        this.resources = resources;
        this.list = list;
        this.from = from;
        this.to=to;

        loader = new ImageLoader(MyAppliction.getHttpQueues()
                ,new BitmapCache());
        inflater = LayoutInflater.from(context);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(resources,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view,to);

        return viewHolder;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (holder.img.getTag()==list.get(position).get(from[0])){

        }
        else{
                holder.img.setImageUrl(String.valueOf(list.get(position).get(from[0])), loader);
                holder.img.setErrorImageResId(R.drawable.ic_menu_camera);
                holder.img.setDefaultImageResId(R.drawable.ic_menu_gallery);
                holder.img.setTag(list.get(position).get(from[0]));
        }
        holder.name.setText((String) list.get(position).get(from[1]));
        holder.weight.setText((String) list.get(position).get(from[2]));
        holder.time.setText((String) list.get(position).get(from[3]));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    NetworkImageView img = null;
    TextView name = null,weight = null,time = null;

    public MyViewHolder(View itemView,int[] to) {
        super(itemView);
        img = (NetworkImageView) itemView.findViewById(to[0]);
        name = (TextView) itemView.findViewById(to[1]);
        weight = (TextView) itemView.findViewById(to[2]);
        time = (TextView) itemView.findViewById(to[3]);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();



    }
}

package com.example.yang.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    String[] from={"Photo","Name","weight","time"};
    int[] to={R.id.img,R.id.name,R.id.weight,R.id.time};
    int[] photoRes={R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    String[] strName={"暗夜之殇","街角的幸福","静悄悄","愤怒的小胖"};
    String[] strWeight={"5000kg","200kg","403kg","4t"};
    String[] time={"9月30日","10月20日","8月15日","1月1日"};
    ArrayList<HashMap<String,Object>> list=null;
    HashMap<String,Object> map=null;

    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = new ListView(this);
        list=new ArrayList<HashMap<String, Object>>();

        for(int i = 0;i<4;i++){
            map=new HashMap<String,Object>();
            map.put("Photo",getResources().getDrawable(photoRes[i]));
            map.put("Name",strName[i]);
            map.put("weight",strWeight[i]);
            map.put("time",time[i]);
            list.add(map);
        }
        MyAdapter adapter=new MyAdapter(this,R.layout.item_layout,list,from,to);
        listView.setAdapter(adapter);


    }
}

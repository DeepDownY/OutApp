package com.example.yang.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SwipeRefreshLayout.OnRefreshListener{

    String[] from = {"Photo", "Name", "weight", "time"};
    int[] to = {R.id.img, R.id.name, R.id.weight, R.id.textTime};
    int x = 1;
    String[] photoRes = {"http://www.ttoou.com/qqtouxiang/allimg/131007/co13100F93S3-4.jpg",
            "http://img1.touxiang.cn/uploads/20120825/25-020324_39.jpg",
            "http://bbs.51job.com/image.php?uid=UWNXaFVsADdTNQczUWA=",
            "http://assets0.tianmaying.com/img/students/3.jpg"};
    String url = "http://7vijy3.com1.z0.glb.clouddn.com/u%3D510514586%2C3474110816%26fm%3D11%26gp%3D0.jpg";
    String[] strName = {"暗夜之殇", "街角的幸福", "静悄悄", "愤怒的小胖"};
    String[] strWeight = {"5000kg", "200kg", "403kg", "4t"};
    String[] time = {"9月30日", "10月20日", "8月15日", "1月1日"};
    ArrayList<HashMap<String, Object>> list = null;
    HashMap<String, Object> map = null;
    ListView listView = null;
    MyHandler handler = new MyHandler();
    SwipeRefreshLayout swipeRefreshLayout = null;
    MyRecAdp myRecAdp = null ;
    RecyclerView recyclerView = null;
    int menuStatus = 0;
    int lastVisibleItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //选择NavigationView 自动生成
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (menuStatus){
            case 1:
                menu.clear();
                getMenuInflater().inflate(R.menu.menu,menu);
                break;
            case 0:
                menu.clear();
                getMenuInflater().inflate(R.menu.main, menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id){
            case  R.id.action_gribview:
//                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case  R.id.action_listView:
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case  R.id.action_staggered:
                break;
            case  R.id.action_hor_grabview:
//                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,
//                        StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case  R.id.action_settings:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //点击NavigationView按钮时的操作
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent send = new Intent();
        if (id == R.id.nav_Index) {
            ChangeToIndex();

        } else if (id == R.id.nav_Personal) {
            ChangeToPerson();
        } else if (id == R.id.nav_List) {
            ChangeToList();
        } else if (id == R.id.nav_search) {


        } else if (id == R.id.nav_test) {
            send.setClass(MainActivity.this, TestActivity.class);
            send.putExtra("id", "Show Search Activity");
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //点击首页按钮时
    private void ChangeToIndex() {
        menuStatus = 0;
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout1);
        relativeLayout.removeAllViews();
        TextView _tt = new TextView(this);
        _tt.setText("Index");
        relativeLayout.addView(_tt);
    }

//    点击list按钮时
    public void ChangeToList() {
        menuStatus = 0;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout.removeAllViews();
        listView = new ListView(this);
        list = new ArrayList<HashMap<String, Object>>();

        //添加测试使用数据
        for (int i = 0; i < 4; i++) {
            map = new HashMap<String, Object>();
            map.put("Photo", photoRes[i]);
            map.put("Name", strName[i]);
            map.put("weight", strWeight[i]);
            map.put("time", time[i]);
            list.add(map);
        }
        for (int i = 0; i < 4; i++) {
            map = new HashMap<String, Object>();
            map.put("Photo", photoRes[i]);
            map.put("Name", strName[i]);
            map.put("weight", strWeight[i]);
            map.put("time", time[i]);
            list.add(map);
        }
        for (int i = 0; i < 4; i++) {
            map = new HashMap<String, Object>();
            map.put("Photo", photoRes[i]);
            map.put("Name", strName[i]);
            map.put("weight", strWeight[i]);
            map.put("time", time[i]);
            list.add(map);
        }
        //实例化Adapter
        final MyAdapter adapter = new MyAdapter(this, R.layout.item_layout, list, from, to);
        //绑定Adapter与ListView
        listView.setAdapter(adapter);
        //添加点击监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id < 0) {
                    return;
                }
                String tem = (String) list.get(position).get("Name");
                Toast.makeText(MainActivity.this, tem, Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayout.addView(listView);


    }

//    点击persion按钮时
//    这里暂时用来调试recylerView 和 SwipRefreshLayout
    public void ChangeToPerson() {
        menuStatus = 1;
        //实例化数据源和所需的控件
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout.removeAllViews();
        list = new ArrayList<HashMap<String, Object>>();
        recyclerView = new RecyclerView(this);
        myRecAdp = new MyRecAdp(this,list,R.layout.item_layout,from,to);
        swipeRefreshLayout = new SwipeRefreshLayout(this);



        //设置下拉进度色彩
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置下拉监听
        swipeRefreshLayout.setOnRefreshListener(this);

        for (int i = 0; i < 4; i++) {
            map = new HashMap<String, Object>();
            map.put("Photo", photoRes[i]);
            map.put("Name", strName[i]);
            map.put("weight", strWeight[i]);
            map.put("time", time[i]);
            list.add(map);
        }
        for (int i = 0; i < 4; i++) {
            map = new HashMap<String, Object>();
            map.put("Photo", photoRes[i]);
            map.put("Name", strName[i]);
            map.put("weight", strWeight[i]);
            map.put("time", time[i]);
            list.add(map);
        }
        for (int i = 0; i < 4; i++) {
            map = new HashMap<String, Object>();
            map.put("Photo", photoRes[i]);
            map.put("Name", strName[i]);
            map.put("weight", strWeight[i]);
            map.put("time", time[i]);
            list.add(map);
        }

        //列表组件监听
        myRecAdp.setOnItemClickListener(new MyRecAdp.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String temp = (String) list.get(position).get("Name");
                Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(myRecAdp);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));


        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem+1 == myRecAdp.getItemCount()){
                    onRefresh();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                onRefresh();
            }
        });
//        recyclerView.addOnScrollListener(new );
        relativeLayout.addView(swipeRefreshLayout);

        swipeRefreshLayout.addView(recyclerView);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map = new HashMap<String, Object>();
                map.put("Photo", url);
                map.put("Name", String.valueOf(x++));
                map.put("weight", "temp");
                map.put("time", "DEC 20");
                list.add(map);
                handler.sendEmptyMessage(0);
            }
        }).start();

    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    swipeRefreshLayout.setRefreshing(false);
                    myRecAdp.notifyItemRangeInserted(myRecAdp.getItemCount()-1,1);
                    break;
                default:
                    break;
            }
        }
    }
}


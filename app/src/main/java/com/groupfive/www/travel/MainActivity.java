package com.groupfive.www.travel;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import view.MyListView;
import view.PullToRefreshListView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_fresh);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mySwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
   /*     PullToRefreshListView PullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        final List list = new ArrayList();
        for (int i=0;i<20;++i)
            list.add("ke"+i);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        PullToRefreshListView.setAdapter(adapter);*/

        MyListView ListView = (MyListView) findViewById(R.id.list);

        final List list = new ArrayList();

        for (int i=0;i<20;++i)
            list.add("ke"+i);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView.setAdapter(adapter);

        ListView.setListViewScrollListener(new MyListView.ListViewScrollListener() {
            @Override
            public void scrollLast() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=21;i<40;++i)
                            list.add("ke"+i);
                       // isload = false;
                        adapter.notifyDataSetChanged();
                    }
                },2000);

            }
        });



    }
}

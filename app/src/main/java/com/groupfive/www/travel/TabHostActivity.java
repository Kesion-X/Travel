package com.groupfive.www.travel;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;

import java.util.ArrayList;
import java.util.List;


import fg.BusLineFragment;
import fg.HomeFragment;
import fg.NearByFragment;
import fg.UserInFoFragment;

import tool.TabFragmentManagerUtil;


public class TabHostActivity extends FragmentActivity {

    private FragmentTabHost fragmentTabHost;
    //�ײ�����
    private List<String> TabStr= null;
    //Ĭ��ͼ��
    private List<Integer> DrawableId = null;
    //fragment list
    private List<Class> FragmentArray = null;

    private   TabFragmentManagerUtil tabFragmentManagerUtil = new TabFragmentManagerUtil() {
        @Override
        public void initTabStrList(List<String> list) {
        	list.add("首页");
        	list.add("附近");
        	list.add("线路");
        	list.add("我的");
        }

        @Override
        public void initDefaultDrawableIdList(List<Integer> list) {
        	list.add(R.drawable.tab_home_selector);
        	list.add(R.drawable.tab_nearby_selector);
        	list.add(R.drawable.tab_busline_selector);
        	list.add(R.drawable.tab_userinfo_selector);
        }

        @Override
        public void initChoiceDrawableIdList(List<Integer> list) {

        }

        @Override
        public void initFragmentList(List<Class> list) {
        	list.add(HomeFragment.class);
        	list.add(NearByFragment.class);
        	list.add(BusLineFragment.class);
        	list.add(UserInFoFragment.class);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_layout);
        InitData();
        InitTabHost();

    }

    private void InitData() {


        TabStr = tabFragmentManagerUtil.getTabStrList();
        DrawableId = tabFragmentManagerUtil.getDefaultDrawableIdList();
        FragmentArray = tabFragmentManagerUtil.getFragmentList();


    }



    /**
     *��ʼ��fragmentTabHost
     **/
    private void InitTabHost() {
        fragmentTabHost= (FragmentTabHost) findViewById(R.id.tab_host);
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.main_content);
        for(int i=0,n = TabStr.size();i<n;++i){
            fragmentTabHost.addTab(fragmentTabHost.newTabSpec(TabStr.get(i)).setIndicator(getIndicatorView(i)),
                    FragmentArray.get(i),null);

        }

    }

    private View getIndicatorView(int i) {
        View view= getLayoutInflater().inflate(R.layout.tabcontent,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.content_image);
        TextView textView  = (TextView) view.findViewById(R.id.content_tx);

        imageView.setBackgroundResource(DrawableId.get(i));
        textView.setText(TabStr.get(i));

        return view;
    }


    @Override
    public void finish() {
        super.finish();

    }
}

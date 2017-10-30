package com.groupfive.www.travel;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tool.TabFragmentManagerUtil;


public class TabHostActivity extends FragmentActivity {

    private FragmentTabHost fragmentTabHost;
    //底部标题
    private List<String> TabStr= null;
    //默认图标
    private List<Integer> DrawableId = null;
    //fragment list
    private List<Class> FragmentArray = null;

    private   TabFragmentManagerUtil tabFragmentManagerUtil = new TabFragmentManagerUtil() {
        @Override
        public void initTabStrList(List<String> list) {

        }

        @Override
        public void initDefaultDrawableIdList(List<Integer> list) {

        }

        @Override
        public void initChoiceDrawableIdList(List<Integer> list) {

        }

        @Override
        public void initFragmentList(List<Class> list) {

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
     *初始化fragmentTabHost
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

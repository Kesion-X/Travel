package view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Scroller;

import com.groupfive.www.travel.R;

/**
 * Created by Administrator on 2017/10/30.
 */

public class MyListView extends ListView implements AbsListView.OnScrollListener {

    private View footerView;
    private boolean isLoading = false;
    private ListViewScrollListener ListViewScrollListener;

/*    final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_fresh);
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
    });*/


    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        footerView = ((Activity)context).getLayoutInflater().inflate(R.layout.listview_footer,null);
        footerView.setVisibility(View.GONE);


        this.addFooterView(footerView);
        this.setOnScrollListener(this);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
       // this.scrollTo(0,headView.getHeight());
    }




    @Override
    public void onScrollStateChanged(AbsListView absListView, int state) {




    }



    @Override
    public void onScroll(AbsListView liview, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        //滚到最后一行
        if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0){
            //判断是否正在加载
            if (isLoading==false) {
                Log.d("TAG", "last item");
                footerView.setVisibility(View.VISIBLE);
                isLoading = true;
                this.ListViewScrollListener.scrollLast();
            }
        }else{
            isLoading = false;
        }

        //如果不是正在加载且footer是可见的，那么说明已经加载完成，将footer设为不可见
        if(!isLoading&&footerView.getVisibility()==View.VISIBLE){
            footerView.setVisibility(View.GONE);
        }


    }

    public void setListViewScrollListener(ListViewScrollListener ListViewScrollListener){
        this.ListViewScrollListener = ListViewScrollListener;
    }

    public static interface ListViewScrollListener{

        void scrollLast();

    }
}

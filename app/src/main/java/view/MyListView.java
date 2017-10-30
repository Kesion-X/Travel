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
    private View headView;

    private boolean isLoading = false;

    private ListViewScrollListener ListViewScrollListener;

    private int headDust;
    private int preY;

    private Scroller Scroller;

    private int firstVisibleItem ;


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

        headView = ((Activity)context).getLayoutInflater().inflate(R.layout.listview_head,null);
        headView.setVisibility(View.GONE);

        headDust = headView.getHeight();
        preY = -headDust;

        this.addHeaderView(headView);

        this.addFooterView(footerView);

        Scroller = new Scroller(context);

        this.setOnScrollListener(this);
    }

    private void smoothScrollTo(int destY,int time){

        int scrollY = getScrollY();
        int deltaY = destY - scrollY;

        Scroller.startScroll(0,scrollY,0,deltaY,time);

        invalidate();
    }

    @Override
    public void computeScroll() {
        if(Scroller.computeScrollOffset()){
            scrollTo(Scroller.getCurrX(),Scroller.getCurrY());
            postInvalidate();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        this.scrollTo(0,headView.getHeight());
    }




    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        Log.d("TAG","head"+headView.getY());
        int headY = (int) headView.getY();
        if(this.firstVisibleItem==0){


            int d = preY-headY;
            smoothScrollTo(headY,d);
          //  this.scrollBy(0,-d);
            preY = headY;
        }





        return super.onTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }



    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
       // Log.d("TAG","fir"+firstVisibleItem+" ");




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

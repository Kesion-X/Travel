package view;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListView;


/**
 * 支持下拉刷新的的listView
 * Created by Ivan on 16/2/14.
 */
public class PullToRefreshListView extends ListView {

    private final String TAG = "PullToRefreshListView";
    private final int DEFAULT_BASE_ANIMATING_TIME_PER_100DP = 150;
    public static final int DEFAULT_WHERE_TO_LOAD = 80;
    private int lastAction = -1;
    private float pullStartY = -1;
    private boolean isTop = true;
    private float distanceY = 0;
    private boolean isPulling = false;
    private ValueAnimator pullCancelAnimator;
    private Context context;
    private Drawable refreshDrawable;
    private OnLoadCallBack onLoadCallBack = new OnLoadCallBack() {
        @Override
        public int whereToLoad() {
            return DEFAULT_WHERE_TO_LOAD;
        }

        @Override
        public void onLoad() {

        }

        @Override
        public void cancelLoad() {

        }

        @Override
        public Drawable refreshDrawable() {
            return null;
        }
    };

    public PullToRefreshListView(Context context) {
        super(context);
        initView(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 没有子view的时候（没有数据，或者被拉到看不到子view），意味着该listView滚动到顶部
                if (getChildCount() == 0) {
                    isTop = true;
                    return;
                }
                if (firstVisibleItem == 0) {
                    View firstView = getChildAt(0);
                    if (firstView.getTop() + distanceY >= 0) {
                        // 第一个view可见且其相对parent（该listView）的顶部距离大于等于0，意味着该listView也是滚动到顶部
                        isTop = true;
                        return;
                    }
                }
                isTop = false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (lastAction == -1 && ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            // 按下的时候
            lastAction = MotionEvent.ACTION_DOWN;
            cancelAnimating();
            Log.d(TAG, "touch down");
        } else if (lastAction == MotionEvent.ACTION_MOVE && ev.getActionMasked() == MotionEvent.ACTION_UP) {
            // 放开手指，开始回滚
            isPulling = false;
            lastAction = -1;
            startAnimating();
            Log.d(TAG, "touch up");
        } else if (lastAction == MotionEvent.ACTION_DOWN) {
            if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
                // 在按下手指的基础上，开始滑动
                if (isTop && !isPulling) {
                    // listView在顶部而且不处于下拉刷新状态，开始下拉
                    pullStartY = ev.getY();
                    lastAction = MotionEvent.ACTION_MOVE;
                    isPulling = true;
                }
            }
        } else if (lastAction == MotionEvent.ACTION_MOVE) {
            if (isTop) {
                // 下拉
                distanceY = ev.getY() - pullStartY;
                Log.d(TAG, distanceY + "");
                if (distanceY > 0) {
                    distanceY = (float) (Math.exp(-ev.getY() / pullStartY / 40) * distanceY);
                    // 在下拉状态时取消系统对move动作的响应，完全由本类响应
                    ev.setAction(MotionEvent.ACTION_DOWN);
                } else {
                    distanceY = 0;
                    // 在下拉过程中往上拉动该listView使得其回到顶部位置，则将该move动作交由系统进行响应
                    ev.setAction(MotionEvent.ACTION_MOVE);
                }
            } else {
                // 在下拉过程中往上拉动listView使listView往下滚动到其没有滚动到顶部，则取消其下拉状态，回到手指按下的初始状态
                lastAction = MotionEvent.ACTION_DOWN;
                isPulling = false;
                distanceY = 0;
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (distanceY > 0) {
            if (refreshDrawable == null) {
                refreshDrawable = onLoadCallBack.refreshDrawable();
            }
            if (refreshDrawable == null) {
                canvas.drawColor(Color.GRAY);
            } else {
                int left = getPaddingLeft();
                int top = getPaddingTop();
                refreshDrawable.setBounds(left, top, getWidth()+left, getHeight()+top);
                refreshDrawable.draw(canvas);
            }

            canvas.save();
            canvas.translate(getPaddingLeft(), getPaddingTop() + distanceY);
            for (int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                drawChild(canvas, child, getDrawingTime());
            }
            canvas.restore();
        }
    }

    /**
     * 下拉结束时进行回滚动画并执行刷新动作
     */
    private void startAnimating() {
        int whereToLoad = dp2px(onLoadCallBack.whereToLoad());
        final boolean toLoad;
        if (distanceY <= whereToLoad) {
            pullCancelAnimator = ValueAnimator.ofFloat(distanceY, 0);
            toLoad = false;
        } else {
            pullCancelAnimator = ValueAnimator.ofFloat(distanceY, whereToLoad);
            toLoad = true;
        }
        pullCancelAnimator.setDuration((long) (DEFAULT_BASE_ANIMATING_TIME_PER_100DP*px2dp(distanceY)/100));
        pullCancelAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        pullCancelAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                distanceY = (float) animation.getAnimatedValue();
                ViewCompat.postInvalidateOnAnimation(PullToRefreshListView.this);
            }
        });
        pullCancelAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        pullCancelAnimator = null;
                        if (toLoad) {
                            onLoadCallBack.onLoad();
                        }
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        pullCancelAnimator = null;
                        if (toLoad) {
                            onLoadCallBack.cancelLoad();
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        pullCancelAnimator.start();
    }

    private void cancelAnimating() {
        if (pullCancelAnimator != null) {
            pullCancelAnimator.cancel();
        }
    }

    private float px2dp(float pxvalue) {
        return (pxvalue - 0.5f) /context.getResources().getDisplayMetrics().density;
    }

    private int dp2px(float dpvalue) {
        return (int) (dpvalue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 下拉刷新的回调
     */
    public interface OnLoadCallBack {
        /**
         * 下拉结束后将listView定位到哪个位置等待刷新完成
         * @return listView的定位y坐标值，in dp
         */
        int whereToLoad();

        /**
         * 下拉结束后进行刷新的回调
         */
        void onLoad();

        /**
         * 取消刷新
         */
        void cancelLoad();

        /**
         * 下拉刷新的背景
         * @return 背景drawable
         */
        Drawable refreshDrawable();
    }

    /**
     * 设置下拉刷新回调
     * @param cb 回调
     */
    public void setOnLoadCallBack(OnLoadCallBack cb) {
        this.onLoadCallBack = cb;
    }

    /**
     * 刷新动作结束后调用该方法结束刷新，使得listView回滚到顶部
     */
    public void setLoadingFinish() {
        startAnimating();
    }
}

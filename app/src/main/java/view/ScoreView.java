package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/11/2.
 */

public class ScoreView extends LinearLayout {

    private final static int START_COLOR = 0x9903a9f4;
    private int width;
    private int heigth;
    private float percent;
    private Paint paint;
    private Path Path;

    public ScoreView(Context context) {
        this(context,null);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(START_COLOR);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width=0;
        int heigth=0;
        for(int i=0;i<getChildCount();++i){
            View view = getChildAt(i);
            width+=view.getMeasuredWidth();
            heigth = view.getMeasuredHeight();
        }
        Log.d("TAG","w"+width+"h"+heigth);
        setMeasuredDimension(width,heigth);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        this.width = r-l;
        this.heigth = b-t;

    }

    //lay

    public void setScore(float socre){
        percent = (socre)/5;
        Path = new Path();
        Log.d("TAG","score"+width+" "+heigth+" "+percent);
        Path.moveTo(0,0);
        Path.lineTo(width*percent,0);
        Path.lineTo(width*percent,heigth);
        Path.lineTo(0,heigth);
        Path.lineTo(0,0);
        Path.close();

        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("TAG","dddraw");
        canvas.save();
        if(Path!=null){
            Log.d("TAG","draw");
            canvas.drawPath(Path,paint);
        }
    }


}

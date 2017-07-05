package push.app.com.juxingdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 功能描述  :
 * 创建时间 : 2017/7/5 11:17
 * 编写人  :  王成哲
 */

public class Juxing extends View {

    private static final int WIDTH =100 ;
    private Rect mRect =new Rect(0,0,WIDTH,WIDTH);
    private  Paint paint=new Paint();

    private int dataX,dataY;

    public Juxing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setColor(Color.RED);
    }

    public Juxing(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Juxing(Context context) {
        this(context,null);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRect(mRect,paint);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int  x = (int) event.getX();
        int  y = (int) event.getY();

        switch (event.getAction()){

            case  MotionEvent.ACTION_DOWN://按下
                    if(!mRect.contains(x,y)){

                        return false;
                    }
                        dataX = x- mRect.left;
                        dataY = y- mRect.top;

                break;

            case  MotionEvent.ACTION_MOVE://移动


                break;
            case  MotionEvent.ACTION_UP://松开
                Rect old=new Rect(mRect);
                mRect.left=x-dataX;
                mRect.top=x-dataY;
                mRect.right= mRect.left-WIDTH;
                mRect.left=mRect.top-WIDTH;
                old.union(mRect);//要刷新的区域，求新矩形区域与旧形区域的并集

                invalidate(old);//出于效率考虑，设定脏区域，只进行局部刷新，不是刷新整个View


                break;


        }
        return  true;
    }
}

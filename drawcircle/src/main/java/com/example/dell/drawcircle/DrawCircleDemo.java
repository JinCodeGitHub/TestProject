package com.example.dell.drawcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * <pre>
 *     author : dell
 *     time   : 2019/01/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DrawCircleDemo extends View {

   private Paint circlePaint; //实现来画圆
   private int   screenWith;
   private int   screenHeight;
   private int    radial   = 50;

   private Paint pointPaint;//实现画点


    private  Context  mContext;

    public DrawCircleDemo(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public DrawCircleDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public DrawCircleDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public  void init(){
        circlePaint  = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);


        //画点的画笔

        pointPaint  = new Paint();

        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.BLUE);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(5);







    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        screenHeight   =  getMeasuredHeight();

        Log.d("DrawCircle", String.valueOf(screenHeight));

        screenWith     =  getMeasuredWidth();

        Log.d("DrawCircle",String.valueOf(screenWith));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(screenWith/2,screenHeight/2,screenWith/3,circlePaint);


        /**
         * 需要拿到圆心坐标
         * 相对圆心坐标计算相对应的位置
         * 判断是否超过了圆的半径
         * 最后描点
         */
        canvas.drawPoint(screenWith/2,screenHeight/2,pointPaint);//x，y是坐标(需要计算x,y坐标)

    }
}

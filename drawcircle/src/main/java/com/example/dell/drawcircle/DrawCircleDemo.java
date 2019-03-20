package com.example.dell.drawcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
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

    private Paint linesPaint;
    private Paint textPaint;
    private Paint recPaint;

    private  Context  mContext;

    private  int  border;

    public DrawCircleDemo(Context context) {
        super(context);
        this.mContext = context;
        init();
        border   = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin);
        Log.i("border",String.valueOf(border));
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
        circlePaint.setColor(Color.WHITE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);


        //画点的画笔

        pointPaint  = new Paint();

        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(5);

        //画线的画笔

        linesPaint  = new Paint();
        linesPaint.setAntiAlias(true);
        linesPaint.setColor(Color.BLUE);
        linesPaint.setStyle(Paint.Style.STROKE);
        linesPaint.setStrokeWidth(3);
        linesPaint.setPathEffect(new DashPathEffect(new float[]{5,5,5,5},1));

        //写文字的画笔

        textPaint  = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(50);

        //画矩形的画笔
        recPaint   = new Paint();
        recPaint.setAntiAlias(true);
        recPaint.setColor(Color.GREEN);
        recPaint.setStyle(Paint.Style.STROKE);
        recPaint.setStrokeWidth(5);





    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        screenHeight   =  getMeasuredHeight();

        Log.d("DrawCircle", String.valueOf(screenHeight));

        screenWith     =  getMeasuredWidth();

        Log.d("DrawCircle",String.valueOf(screenWith));

        Log.d("DrawCircle","screenHeight = " + screenHeight + " screenWith = " + screenWith);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



       /* for (int i = 0 ; i < 3; i++){

            canvas.drawCircle(screenWith/2,screenHeight/2,screenWith/2-screenWith/6*i,circlePaint);
        }*/

        canvas.drawCircle(screenWith/2,screenHeight/2,screenWith/3,circlePaint);
        canvas.drawCircle(screenWith/2,screenHeight/2,screenWith/2-200,circlePaint);
        canvas.drawCircle(screenWith/2,screenHeight/2,screenWith/2-20,circlePaint);
        canvas.drawCircle(screenWith/2,screenHeight/2,screenWith/2-400,circlePaint);
        canvas.drawLine(0,screenHeight/2,screenWith,screenHeight/2,linesPaint);
        canvas.drawLine(screenWith/2,screenHeight/2-screenWith/2,screenWith/2,screenHeight/2+screenWith/2,linesPaint);
        drawData(canvas);
        canvas.drawText(String.valueOf(0),screenWith/2-2,screenHeight/2-screenWith/2,textPaint);
        canvas.drawRect(new Rect(200, 200, 400, 400),recPaint);


        /**
         * 需要拿到圆心坐标
         * 相对圆心坐标计算相对应的位置
         * 判断是否超过了圆的半径
         * 最后描点
         */
//        canvas.drawPoint(screenWith/2,screenHeight/2,pointPaint);//x，y是坐标(需要计算x,y坐标)



    }


    //画圆弧上的数字；

    public void drawData(Canvas canvas){

       for (int i = 0 ; i < 12 ; i ++ ){
           canvas.drawText(String.valueOf(30*i),screenWith/2,screenHeight/2-screenWith/2,textPaint);
//           canvas.drawLine(screenWith/2,screenHeight/2,screenWith,screenHeight/2,linesPaint);
           canvas.rotate(30,screenWith/2,screenHeight/2);
       }


    }

    public  interface  SetData{

        int  initdata(int a ,int b);
        void getdata();

    }

  private  SetData  mSetData;

    public  void   DataListener(SetData data){
        this.mSetData   = data;

    }




}

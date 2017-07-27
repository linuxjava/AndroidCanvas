package com.example.robincxiao.androidcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/5/27.
 * 测试save和restore的使用
 */
public class MyView2 extends View{
    private Paint mPaint;

    public MyView2(Context context) {
        this(context, null);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画完向右的箭头后，再画圆，并保证圆在左上角
        canvas.save();
        canvas.rotate(90, getWidth()/2, getHeight()/2);
        canvas.drawLine(0, getWidth()/2 , getHeight()/2, 0, mPaint);
        canvas.drawLine(getHeight()/2, getWidth() , getHeight()/2, 0, mPaint);
        canvas.drawLine(getHeight(), getWidth()/2 , getHeight()/2, 0, mPaint);
        canvas.restore();

        canvas.drawCircle(20, 20, 20, mPaint);
    }
}

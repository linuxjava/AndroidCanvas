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
 * 测试translate的使用
 */
public class MyView1 extends View{
    private Paint mPaint;

    public MyView1(Context context) {
        this(context, null);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        testTranslate(canvas);
        testRotate(canvas);
    }

    private void testTranslate(Canvas canvas){
        canvas.translate(getWidth()/2, getHeight()/2);
        canvas.drawRect(0, 0, 150, 150, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(-100, 0, 0, 150, mPaint);
    }

    private void testRotate(Canvas canvas){
        canvas.translate(getWidth()/2, getHeight()/2);
        canvas.drawRect(0, 0, 150, 150, mPaint);
        canvas.rotate(90);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, 150, 150, mPaint);
    }
}

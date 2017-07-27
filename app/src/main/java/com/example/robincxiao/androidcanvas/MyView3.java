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
 * 测试saveLayer和restore的使用
 */
public class MyView3 extends View{
    private Paint mPaint;

    public MyView3(Context context) {
        this(context, null);
    }

    public MyView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        canvas.drawCircle(50, 50, 50, mPaint);

        int layer1 = canvas.saveLayerAlpha(0, 0, getWidth(), getHeight(), 127, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(70, 70, 50, mPaint);

        int layer2 = canvas.saveLayerAlpha(0, 0, getWidth(), getHeight(), 127, Canvas.ALL_SAVE_FLAG);
        canvas.translate(90, 90);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(0, 0, 50, mPaint);

        canvas.restoreToCount(layer2);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 100, 20, mPaint);
    }
}

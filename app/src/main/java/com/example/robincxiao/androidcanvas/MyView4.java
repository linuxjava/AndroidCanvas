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
 * 测试ShadowLayer的使用
 */
public class MyView4 extends View{
    private Paint mPaint;

    public MyView4(Context context) {
        this(context, null);
    }

    public MyView4(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        //这个方法不支持硬件加速，所以我们要测试时必须先关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        canvas.translate(getWidth()/2, getHeight()/2);
        mPaint.setShadowLayer(30, 0, 10, Color.BLUE);
        canvas.drawCircle(0, 0, 100, mPaint);
    }
}

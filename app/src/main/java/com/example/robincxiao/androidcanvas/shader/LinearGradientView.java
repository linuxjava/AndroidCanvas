package com.example.robincxiao.androidcanvas.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/6/23.
 */

public class LinearGradientView extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradient;

    public LinearGradientView(Context context) {
        this(context, null);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        /**
         * 1.通过LinearGradient的前面四个参数可以控制渐变的方向和区域
         * 2.如果LinearGradient设置的渲染区域小于绘制的实际大小，那么绘制图形的其他区域将按照LinearGradient设置的模式进行渲染
         * 3.LinearGradient的前四个参数的坐标系是以所绘制的Canvas坐标系作为参考的，而不是本例子中drawRect绘制的矩形作为参考的
         */
        //mLinearGradient = new LinearGradient(getWidth() / 2, getHeight() / 4, getWidth() / 2, getHeight() / 2, Color.BLUE, Color.YELLOW, Shader.TileMode.REPEAT);
        mLinearGradient = new LinearGradient(getWidth() / 2, 0, getWidth() / 2, getHeight(), Color.BLUE, Color.YELLOW, Shader.TileMode.REPEAT);
//        mLinearGradient = new LinearGradient(getWidth() / 2, 0, getWidth() / 2, getHeight(), new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE}
//                , new float[]{0, 0.1F, 0.5F, 0.7F, 0.8F}, Shader.TileMode.CLAMP);

        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(150, 300);
        path.close();

        canvas.drawPath(path, mPaint);

        Path path1 = new Path();
        path1.moveTo(100, 300);
        path1.lineTo(200, 300);
        path1.lineTo(150, 500);
        path1.close();

        //mLinearGradient = new LinearGradient(150, 100, 150, getHeight(), Color.BLUE, Color.YELLOW, Shader.TileMode.REPEAT);
        canvas.drawPath(path1, mPaint);

        //绘制的矩形区域大于LinearGradient设置的渲染区域
        //canvas.drawRect(0, 0, getWidth(), getHeight() / 2, mPaint);
    }
}

package com.example.robincxiao.androidcanvas.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/6/26.
 */

public class SweepGradientView extends View{
    private Paint mPaint;
    private SweepGradient mSweepGradient;
    private int mWidth;
    private int mHeight;

    public SweepGradientView(Context context) {
        this(context, null);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context){
        mWidth = 500;
        mHeight = 500;

        mPaint = new Paint();
        //mSweepGradient默认的起点是从x的正方向开始（定义中心为圆的中心），不过通过matrix可以改变起点绘制方向
        Matrix matrix = new Matrix();
        //注意：设置Matrix的Rotate时，它也有中心点的位置的
//        matrix.setRotate(30, mWidth/2, mHeight/2);
//        mSweepGradient = new SweepGradient(mWidth/2, mHeight/2, Color.RED, Color.BLUE);

        //注意：通过translate改变坐标系之后，setRotate和SweepGradient的中心点参考坐标系也随之改变
        //matrix.setRotate(30, 0, 0);
        mSweepGradient = new SweepGradient(0, 0, Color.RED, Color.BLUE);

        //mSweepGradient.setLocalMatrix(matrix);
        mPaint.setShader(mSweepGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.translate(mWidth/2, mHeight/2);
//        canvas.drawCircle(0, 0, mWidth/2, mPaint);
        canvas.drawCircle(mWidth/2, mHeight/2, mWidth/2, mPaint);

    }
}

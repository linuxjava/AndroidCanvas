package com.example.robincxiao.androidcanvas.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/6/26.
 * RadialGradient测试
 */

public class RadialGradientView extends View{
    private Paint mPaint;
    private RadialGradient mRadialGradient;
    private Bitmap mSrcBitmap;
    private int mWidth;
    private int mHeight;

    public RadialGradientView(Context context) {
        this(context, null);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context){
        mWidth = 300;
        mHeight = 500;

        mPaint = new Paint();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test);
        mWidth = mSrcBitmap.getWidth();
        mHeight = mSrcBitmap.getHeight();

        mRadialGradient = new RadialGradient(mWidth/2, mHeight/2, mHeight, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //使用RadialGradient画一个遮罩层，实现图片四个角变暗
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawRect(new RectF(0, 0, mWidth, mHeight), mPaint);
    }
}

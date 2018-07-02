package com.example.robincxiao.androidcanvas.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/6/23.
 */

public class BitmapShaderView extends View {
    private Paint mPaint;
    private BitmapShader mShader;
    private int mWidth;
    private int mHeight;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private Matrix mMatrix;
    private int mViewMin;
    private int mBitmapMin;

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test);

        mBitmapWidth = bitmap.getWidth();
        mBitmapHeight = bitmap.getHeight();

        mMatrix = new Matrix();
        mShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        test0(canvas);
    }

    private void test0(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        mPaint.setShader(mShader);
        canvas.translate(mWidth/2, mHeight/2);
        canvas.drawRect(-mBitmapWidth/2, -mBitmapHeight/2, mBitmapWidth/2, mBitmapHeight/2, mPaint);
    }

    private void test1(Canvas canvas) {
        mViewMin = Math.min(mWidth, mHeight);
        mBitmapMin = Math.min(mBitmapWidth, mBitmapHeight);

        float ratio = mViewMin * 1.0f / mBitmapMin;
        mMatrix.setScale(ratio, ratio);
        mShader.setLocalMatrix(mMatrix);

        canvas.drawColor(Color.GRAY);
        mPaint.setShader(mShader);

        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }

    private void test2(Canvas canvas) {
        mViewMin = Math.min(mWidth, mHeight);
        mBitmapMin = Math.min(mBitmapWidth, mBitmapHeight);

        float ratio = mViewMin * 1.0f / mBitmapMin;
        mMatrix.setScale(ratio, ratio);
        mShader.setLocalMatrix(mMatrix);

        canvas.drawColor(Color.GRAY);
        mPaint.setShader(mShader);

        canvas.drawCircle(mViewMin / 2, mViewMin / 2, mViewMin / 2, mPaint);
    }

    private void test3(Canvas canvas) {
        mViewMin = Math.min(mWidth, mHeight);
        mBitmapMin = Math.min(mBitmapWidth, mBitmapHeight);

        float ratio = mViewMin * 1.0f / mBitmapMin;
        mMatrix.setScale(ratio, ratio);
        mShader.setLocalMatrix(mMatrix);

        canvas.drawColor(Color.GRAY);
        mPaint.setShader(mShader);

        canvas.translate(mViewMin / 2, mViewMin / 2);
        //canvas.drawCircle(0, 0, mViewMin / 2, mPaint);
        canvas.drawRect(0, 0, mViewMin / 2, mViewMin / 2, mPaint);
    }
}

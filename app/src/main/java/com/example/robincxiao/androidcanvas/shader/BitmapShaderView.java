package com.example.robincxiao.androidcanvas.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/6/23.
 *
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

    /**
     * canvas.translate后再使用mShader去画圆是有问题的，暂不清楚为什么
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        test3(canvas);

        /**
         * 使用如下方式有问题，问题原因如下:
         * BitmapShader默认是是从画布的坐标原点（左上角）开始绘制bitmap图像的，因为将translate移动到中心后，它就开始从中心点开始
         * 绘制图像，所以导致只有圆的右下角有正确的图像，其它三块都是图像边缘像素的拉升
         */
//        canvas.translate(mViewMin / 2, mViewMin / 2);
//        canvas.drawCircle(0, 0, mViewMin / 2, mPaint);
    }

    private void test0(Canvas canvas){
        canvas.drawColor(Color.GRAY);
        mPaint.setShader(mShader);
        canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mPaint);
    }

    private void test1(Canvas canvas){
        mViewMin = Math.min(mWidth, mHeight);
        mBitmapMin = Math.min(mBitmapWidth, mBitmapHeight);

        float ratio = mViewMin * 1.0f / mBitmapMin;
        mMatrix.setScale(ratio, ratio);
        mShader.setLocalMatrix(mMatrix);

        canvas.drawColor(Color.GRAY);
        mPaint.setShader(mShader);

        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }

    private void test2(Canvas canvas){
        mViewMin = Math.min(mWidth, mHeight);
        mBitmapMin = Math.min(mBitmapWidth, mBitmapHeight);

        float ratio = mViewMin * 1.0f / mBitmapMin;
        mMatrix.setScale(ratio, ratio);
        mShader.setLocalMatrix(mMatrix);

        canvas.drawColor(Color.GRAY);
        mPaint.setShader(mShader);

        canvas.drawCircle(mViewMin / 2, mViewMin / 2, mViewMin / 2, mPaint);
    }

    private void test3(Canvas canvas){
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


    /**
     * 使用BitmapShader去渲染时，需要考虑一个问题，view的大小和bitmap并不匹配时，如何处理？
     * 1.获取view宽高的最小值
     * 2.获取bitmap宽高的最小值
     * 3.根据要绘制的图形以及1、2中的两个值，计算bitmap与view缩放比率
     */
}

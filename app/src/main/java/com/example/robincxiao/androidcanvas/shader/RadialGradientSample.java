package com.example.robincxiao.androidcanvas.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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

public class RadialGradientSample extends View{
    private Paint mPaint;
    private RadialGradient mRadialGradient;
    private Bitmap mSrcBitmap;
    private int mWidth;
    private int mHeight;

    public RadialGradientSample(Context context) {
        this(context, null);
    }

    public RadialGradientSample(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientSample(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context){
        mWidth = 300;
        mHeight = 500;

        mPaint = new Paint();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test1);
        mWidth = mSrcBitmap.getWidth();
        mHeight = mSrcBitmap.getHeight();

        // 计算径向渐变半径
        float radiu = mHeight * (2F / 3F);

        // 实例化径向渐变
        mRadialGradient = new RadialGradient(mWidth / 2F, mHeight / 2F, radiu, new int[] { 0, 0, 0xAA000000 }, new float[] { 0F, 0.7F, 1.0F }, Shader.TileMode.CLAMP);
        // 实例化一个矩阵
        Matrix matrix = new Matrix();
        // 设置矩阵的缩放(实质上时缩小宽的比率)
        matrix.setScale(mWidth / (radiu * 2F), 1.0F);
        // 设置矩阵的预平移(设置缩放的中心点)
        matrix.preTranslate(((radiu * 2F) - mWidth) / 2F, 0);

        // 将该矩阵注入径向渐变
        mRadialGradient.setLocalMatrix(matrix);
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

        //canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        //画遮罩层
        canvas.drawRect(new RectF(0, 0, mWidth, mHeight), mPaint);
    }
}

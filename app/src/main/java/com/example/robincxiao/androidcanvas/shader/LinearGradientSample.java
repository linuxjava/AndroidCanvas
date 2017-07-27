package com.example.robincxiao.androidcanvas.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/6/23.
 */

public class LinearGradientSample extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Bitmap mSrcBitmap;
    private Bitmap mRefBitmap;
    private PorterDuffXfermode mPorterDuffXfermode;
    private int GAP = 30;

    public LinearGradientSample(Context context) {
        this(context, null);
    }

    public LinearGradientSample(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGradientSample(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        Matrix matrix = new Matrix();

        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test);
        //使用matri创建倒影bitmap
        matrix.setScale(1, -1);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, mSrcBitmap.getHeight()/3, mSrcBitmap.getWidth(), mSrcBitmap.getHeight()/3, matrix, true);

        mPaint.setShader(new LinearGradient(mSrcBitmap.getWidth()/2, mSrcBitmap.getHeight() + GAP, mSrcBitmap.getWidth()/2, mSrcBitmap.getHeight() * 4/3 + GAP,
                0x00ffffff, 0xddffffff, Shader.TileMode.CLAMP));

        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mSrcBitmap.getWidth(), mSrcBitmap.getHeight() + mSrcBitmap.getHeight() * 1 / 3 + GAP);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mSrcBitmap, 0, 0, null);

        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight() + GAP, null);
        canvas.drawRect(0, mSrcBitmap.getHeight() + GAP, mSrcBitmap.getWidth(), mSrcBitmap.getHeight() * 4 / 3 + GAP, mPaint);
    }
}

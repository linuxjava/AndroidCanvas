package com.example.robincxiao.androidcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/6/19.
 * 测试SweepGradient的使用，以及Matrix在SweepGradient应用
 */

public class SweepGradientView extends View {
    private static final int COLOR_100 = Color.WHITE;
    private static final int COLOR_0 = 0x00ffffff;
    private SweepGradient mSweepGradient;
    private Paint mPaint;
    private Matrix mMatrix;

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

    private void init(Context context) {
        mPaint = new Paint();
        mMatrix = new Matrix();

        int[] colors = {Color.GREEN, Color.RED, Color.BLUE};
        float[] positions = {0, 0.2f, 1};
        mSweepGradient = new SweepGradient(0, 0, colors, positions);
        mMatrix.setRotate(30);
        mSweepGradient.setLocalMatrix(mMatrix);

        mPaint.setAntiAlias(true);
        mPaint.setShader(mSweepGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(0, 0, getHeight() / 2, mPaint);
    }
}

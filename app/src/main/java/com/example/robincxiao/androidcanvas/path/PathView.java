package com.example.robincxiao.androidcanvas.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/7/25.
 */

public class PathView extends View {
    private Paint mPaint;
    private Paint mNonePaint;
    private int marging = 82;
    private PathEffect mPathEffect1;
    private PathEffect mPathEffect2;
    private PathEffect mPathEffect3;
    private Path mPath;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.RED);

        mNonePaint = new Paint(mPaint);

        mPath = new Path();
        mPath.moveTo(100, marging);
//        mPath.lineTo(300, 180);
//        mPath.lineTo(400, 600);
//        mPath.lineTo(200, 1000);
//        mPath.lineTo(800, 1200);
        mPath.lineTo(100, 500);
        Path p = new Path();
        p.addRect(0, 0, 64, 12, Path.Direction.CCW);
        mPathEffect1 = new PathDashPathEffect(p, 128, 64,
                android.graphics.PathDashPathEffect.Style.MORPH);
        mPathEffect2 = new PathDashPathEffect(p, 128, 0,
                PathDashPathEffect.Style.ROTATE);
        mPathEffect3 = new PathDashPathEffect(p, 128, 0,
                android.graphics.PathDashPathEffect.Style.TRANSLATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath, mNonePaint);
        canvas.translate(100, 0);
        mPaint.setPathEffect(mPathEffect1);
        canvas.drawPath(mPath, mPaint);
        canvas.translate(100, 0);
        mPaint.setPathEffect(mPathEffect2);
        canvas.drawPath(mPath, mPaint);
        canvas.translate(100, 0);
        mPaint.setPathEffect(mPathEffect3);
        canvas.drawPath(mPath, mPaint);
    }
}

package com.example.robincxiao.androidcanvas.curve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by robincxiao on 2017/8/1.
 */

public class Bezier extends View {
    private Paint paint;
    private int centerX, centerY;
    private PointF startPonit, endPonit, controlPoint;
    private Path path;

    public Bezier(Context context) {
        this(context, null);
    }

    public Bezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        startPonit = new PointF();
        endPonit = new PointF();
        controlPoint = new PointF();

        path = new Path();


        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        startPonit.set(centerX - 200, centerY);
        endPonit.set(centerX + 200, centerY);

        controlPoint.set(centerX, 200);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controlPoint.x = event.getX();
        controlPoint.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(startPonit.x, startPonit.y, 10, paint);
        canvas.drawCircle(endPonit.x, endPonit.y, 10, paint);

        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(startPonit.x, startPonit.y, controlPoint.x, controlPoint.y, paint);
        canvas.drawLine(endPonit.x, endPonit.y, controlPoint.x, controlPoint.y, paint);

        paint.setColor(Color.RED);
        path.reset();
        path.moveTo(startPonit.x, startPonit.y);
        path.quadTo(controlPoint.x, controlPoint.y, endPonit.x, endPonit.y);

        canvas.drawPath(path, paint);
    }
}

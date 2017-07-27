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

public class PathView1 extends View {
    Paint paint;
    Path pathBorder, pathCircle;

    Path pathShape;
    float phase;
    float advance;
    PathDashPathEffect.Style style;

    public PathView1(Context context) {
        super(context);
        init();
    }

    public PathView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        pathBorder = new Path();
        pathCircle = new Path();

        pathShape = new Path();
        pathShape.moveTo(0, 0);
        pathShape.lineTo(7, 20);
        pathShape.lineTo(14, 0);
        pathShape.close();

        phase = 30.0f;
        advance = 30.0f;
        style = PathDashPathEffect.Style.MORPH;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        pathBorder.reset();
        pathBorder.moveTo(50,  50);
        pathBorder.lineTo(50, getHeight()-50);
        pathBorder.lineTo(getWidth()-50, getHeight()-50);
        pathBorder.lineTo(getWidth()-50, 50);
        pathBorder.close();

        float radius;
        pathCircle.reset();
        if(getWidth()>getHeight()){
            radius = getHeight()/4;
        }else{
            radius = getWidth()/4;
        }
        pathCircle.addCircle(getWidth()/2, getHeight()/2, radius, Path.Direction.CCW);

        PathDashPathEffect pathDashPathEffect =
                new PathDashPathEffect(pathShape, advance, phase, style);
        paint.setPathEffect(pathDashPathEffect);

        canvas.drawPath(pathCircle, paint);
        canvas.drawPath(pathBorder, paint);
    }

    public void setAdvance(int adv){
        advance = (float)adv;
        invalidate();
    }

    public void setPhase(int ph){
        phase = (float)ph;
        invalidate();
    }

    public void setStype(PathDashPathEffect.Style sty){
        style = sty;
        invalidate();
    }
}

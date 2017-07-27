package com.example.robincxiao.androidcanvas.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/7/27.
 */

public class PathMeasureView extends View {
    private Paint paint;
    private Path path;
    private PathMeasure pathMeasure;
    private Bitmap bitmap;
    private float distance;
    private float pathLenght;
    private Matrix matrix;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        path = new Path();
        pathMeasure = new PathMeasure();
        matrix = new Matrix();

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_jiantou);
        Matrix tmpMatrix = new Matrix();
        tmpMatrix.setScale(0.2f, 0.2f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), tmpMatrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int radiu = (getWidth() - 50) / 2;

        path.reset();
        path.addCircle(0, 0, radiu, Path.Direction.CW);
        pathMeasure.setPath(path, true);

        pathLenght = pathMeasure.getLength();

        if (pathMeasure.getMatrix(distance, matrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG)) {
            matrix.preTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);

            canvas.translate(getWidth() / 2, getHeight() / 2);
            canvas.drawPath(path, paint);
            canvas.drawBitmap(bitmap, matrix, paint);

            if ((distance += pathLenght / 360) > pathLenght) {
                distance = 0;
            }

            postInvalidateDelayed(30);
        }
    }
}

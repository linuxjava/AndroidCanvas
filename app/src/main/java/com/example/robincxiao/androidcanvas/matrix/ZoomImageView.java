package com.example.robincxiao.androidcanvas.matrix;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by robincxiao on 2017/8/9.
 */

public class ZoomImageView extends android.support.v7.widget.AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    public static final float MAX_SCALE = 4.0f;
    private Matrix matrix;
    private ScaleGestureDetector gestureDetector;
    private float initScale;
    private final float[] matrixValues = new float[9];

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }

    private void init(Context context) {
        setScaleType(ScaleType.MATRIX);//注：必须要设置
        matrix = new Matrix();
        gestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                initImageSize();
            }
        });
    }

    private void initImageSize() {
        Drawable drawable = getDrawable();
        int srcWidth = drawable.getIntrinsicWidth();
        int srcHeight = drawable.getIntrinsicHeight();

        int width = getWidth();
        int height = getHeight();

        initScale = Math.min(width * 1.0f / srcWidth, height * 1.0f / srcHeight);
        matrix.postTranslate((width - srcWidth) / 2, (height - srcHeight) / 2);

        matrix.postScale(initScale, initScale, getWidth() / 2, getHeight() / 2);

        setImageMatrix(matrix);
    }

    /**
     * 获得当前的缩放比例
     *
     * @return
     */
    public final float getScale() {
        matrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (getDrawable() == null)
            return true;

        float currentScaleFactor = getScale();
        float scaleFactor = detector.getScaleFactor();
        Log.d("xiao1", "scaleFactor=" + scaleFactor);

        if ((currentScaleFactor < MAX_SCALE && scaleFactor > 1.0f)
                || (currentScaleFactor > initScale && scaleFactor < 1.0f)) {

            //缩小检测
            if (scaleFactor * currentScaleFactor < initScale) {
                //计算还能继续缩小的比率
                scaleFactor = initScale / currentScaleFactor;
            }
            //放大检测
            if (scaleFactor * currentScaleFactor > MAX_SCALE) {
                //计算还能继续放大的比率
                scaleFactor = MAX_SCALE / currentScaleFactor;
            }
            /**
             * 设置缩放比例
             */
            matrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
            checkBorderAndCenterWhenScale();
            setImageMatrix(matrix);
        }

        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void checkBorderAndCenterWhenScale() {
        RectF drawableRect = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

//        if (drawableRect.width() > getWidth()) {
//            if (drawableRect.left > getLeft()) {
//                deltaX = getLeft() - drawableRect.left;
//            } else if (drawableRect.right < getRight()) {
//                deltaX = getRight() - drawableRect.right;
//            }
//        }
//
//        if (drawableRect.height() > getHeight()) {
//            if (drawableRect.top > getTop()) {
//                deltaY = getTop() - drawableRect.top;
//            } else if (drawableRect.bottom < getBottom()) {
//                deltaY = getBottom() - drawableRect.bottom;
//            }
//        }

        if (drawableRect.width() >= getWidth()) {
            if (drawableRect.left > 0) {
                deltaX = -drawableRect.left;
            }
            if (drawableRect.right < getWidth()) {
                deltaX = getWidth() - drawableRect.right;
            }
        }

        if (drawableRect.height() >= getHeight()) {
            if (drawableRect.top > 0) {
                deltaY = -drawableRect.top;
            }
            if (drawableRect.bottom < getHeight()) {
                deltaY = getHeight() - drawableRect.bottom;
            }
        }

        // 如果宽或高小于屏幕，则让其居中
        if (drawableRect.width() < getWidth()) {
            deltaX = getWidth() * 0.5f - (0.5f * drawableRect.width() + drawableRect.left);
        }
        if (drawableRect.height() < getHeight()) {
            deltaY = getHeight() * 0.5f - (0.5f * drawableRect.height() + drawableRect.top);
        }

        matrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 根据当前图片坐标值
     *
     * @return
     */
    private RectF getMatrixRectF() {
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (null != d) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }
}

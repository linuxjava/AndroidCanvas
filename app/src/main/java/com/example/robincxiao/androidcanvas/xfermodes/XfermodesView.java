package com.example.robincxiao.androidcanvas.xfermodes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/6/23.
 */

public class XfermodesView extends View{
    private static final Xfermode[] sModes = {
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),//取两层绘制交集。显示SRC。
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),//取两层绘制交集。显示DST。
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),//取上层绘制非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),//取下层绘制非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN)
    };
    private int W = 516;
    private int H = 516;
    private Paint mPaint;
    private int mIndex = 0;

    public XfermodesView(Context context) {
        this(context, null);
    }

    public XfermodesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context){
        mPaint = new Paint();
    }

    public void setIndex(int index){
        mIndex = index;
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }

    static Bitmap makeTest() {
        Bitmap bm = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, 200, 200), p);
        return bm;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        W = getWidth();
        H = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        int sc = canvas.saveLayer(0, 0, W, H, null, Canvas.MATRIX_SAVE_FLAG |
                Canvas.CLIP_SAVE_FLAG |
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                Canvas.CLIP_TO_LAYER_SAVE_FLAG);


        /**
         * Xfermode使用注意：Dst可以是bitmap或直接用canvas绘制图像，但是Src必须为Bitmap，否则Xfermode无效果
         */
        canvas.drawBitmap(makeDst(W, H), 0, 0, mPaint);
//        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p.setStyle(Paint.Style.FILL);
//        p.setColor(0xFFFFCC44);
//        canvas.drawOval(new RectF(0, 0, W * 3 / 4, W * 3 / 4), p);


        mPaint.setXfermode(sModes[mIndex]);
        canvas.drawBitmap(makeSrc(W, H), 0, 0, mPaint);
//        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p.setStyle(Paint.Style.FILL);
//        p.setColor(0xFF66AAFF);
//        canvas.drawRect(W / 3, H / 3, W * 19 / 20, H * 19 / 20, p);

        mPaint.setXfermode(null);

        canvas.restoreToCount(sc);
    }
}

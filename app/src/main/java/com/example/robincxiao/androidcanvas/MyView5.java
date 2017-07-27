package com.example.robincxiao.androidcanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/5/27.
 * 测试PorterDuffXfermode的使用
 */
public class MyView5 extends View{
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Bitmap mMaskBmp;
    private PorterDuffXfermode porterDuffXfermode;

    public MyView5(Context context) {
        this(context, null);
    }

    public MyView5(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        //将drawable绘制到创建的mMaskBmp
//        Drawable drawable = context.getResources().getDrawable(R.mipmap.health_score_mask);
//        drawable.setBounds(0, 0, mWidth, mHeight);
//        mMaskBmp = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(mMaskBmp);
//        drawable.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.MATRIX_SAVE_FLAG |
                Canvas.CLIP_SAVE_FLAG |
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                Canvas.CLIP_TO_LAYER_SAVE_FLAG);

        canvas.translate(getWidth()/2, getHeight()/2);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setXfermode(porterDuffXfermode);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, 300, 50, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(sc);
    }
}

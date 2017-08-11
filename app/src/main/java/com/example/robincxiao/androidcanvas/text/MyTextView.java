package com.example.robincxiao.androidcanvas.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robincxiao on 2017/8/10.
 */

public class MyTextView extends View {
    private Paint paint;
    ;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setTextSize(45);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        test1(canvas);
    }

    /**
     * 测试功能点：测试行距(getFontSpacing两行文字的 baseline 的距离)
     *
     * @param canvas
     */
    private void test1(Canvas canvas) {
        String str1 = "测试";
        canvas.drawText(str1, 0, str1.length(), 100, 100, paint);
        String str2 = "Test g";
        canvas.drawText(str2, 0, str2.length(), 100, 100 + paint.getFontSpacing(), paint);

    }
}

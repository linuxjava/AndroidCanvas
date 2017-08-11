package com.example.robincxiao.androidcanvas.clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/8/10.
 */

public class ClipView1 extends View {
    private Paint paint;
    ;

    public ClipView1(Context context) {
        this(context, null);
    }

    public ClipView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        test2(canvas);
    }

    /**
     * 测试功能点：超出裁剪区域的绘制不会被显示
     * @param canvas
     */
    private void test1(Canvas canvas){
        canvas.drawColor(getResources().getColor(R.color.colorPrimaryDark));
        canvas.clipRect(new Rect(100, 100, 300, 300));
        canvas.drawColor(Color.BLUE);//裁剪区域的rect变为蓝色
        canvas.drawRect(new Rect(0, 0, 100, 100), paint);//在裁剪的区域之外，不能显示
        canvas.drawCircle(150, 150, 50, paint);//在裁剪区域之内，能显示
    }

    /**
     *测试功能点：canvas的matrix对clipRegion没有影响
     * @param canvas
     */
    private void test2(Canvas canvas){
        canvas.scale(0.5f, 0.5f);
        canvas.save();
        canvas.clipRect(new Rect(100,100,200,200));//裁剪区域实际大小为50*50
        canvas.drawColor(Color.RED);
        canvas.restore();

        canvas.drawRect(new Rect(0,0,100,100), paint);//矩形实际大小为50*50

        canvas.clipRegion(new Region(new Rect(300,300,400,400)));//裁剪区域实际大小为100*100
        canvas.drawColor(Color.BLACK);
    }
}

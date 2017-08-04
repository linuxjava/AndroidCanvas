package com.example.robincxiao.androidcanvas.svg;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.widget.ImageView;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/8/1.
 */

public class SVGActivity extends Activity {
    private ImageView mImg;
    private ImageView mAnimatorImg;
    private ImageView mAnimatorImg1;
    private AnimatedVectorDrawableCompat animatedVectorDrawableCompat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_svg);

        //使用兼容库实现animated-vector
        mAnimatorImg = (ImageView) findViewById(R.id.img_animator);
        animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(
                this, R.drawable.animated_vector_android
        );
        mAnimatorImg.setImageDrawable(animatedVectorDrawableCompat);
        animatedVectorDrawableCompat.start();

        //从正方形到三角形变化的过程，使用兼容性的库还是有问题的
        mAnimatorImg1 = (ImageView) findViewById(R.id.img_animator1);
        AnimatedVectorDrawableCompat drawableCompat = AnimatedVectorDrawableCompat.create(
                this, R.drawable.animated_square
        );
        mAnimatorImg1.setImageDrawable(drawableCompat);
        drawableCompat.start();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}

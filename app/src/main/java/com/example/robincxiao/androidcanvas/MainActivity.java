package com.example.robincxiao.androidcanvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.robincxiao.androidcanvas.clip.ClipActivity;
import com.example.robincxiao.androidcanvas.curve.CurveActivity;
import com.example.robincxiao.androidcanvas.matrix.MatrixActivity;
import com.example.robincxiao.androidcanvas.path.PathEffectActivity;
import com.example.robincxiao.androidcanvas.path.PathMeasureActivity;
import com.example.robincxiao.androidcanvas.shader.ShaderActivity;
import com.example.robincxiao.androidcanvas.svg.SVGActivity;
import com.example.robincxiao.androidcanvas.text.TextActivity;
import com.example.robincxiao.androidcanvas.xfermodes.XfermodesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onXfermodes(View view) {
        startActivity(new Intent(this, XfermodesActivity.class));
    }

    public void onShader(View view) {
        startActivity(new Intent(this, ShaderActivity.class));
    }

    public void onPathEffect(View view) {
        startActivity(new Intent(this, PathEffectActivity.class));
    }

    public void onPathMeasure(View view) {
        startActivity(new Intent(this, PathMeasureActivity.class));
    }

    public void onCurve(View view) {
        startActivity(new Intent(this, CurveActivity.class));
    }

    public void onSVG(View view) {
        startActivity(new Intent(this, SVGActivity.class));
    }

    public void onMatrix(View view) {
        startActivity(new Intent(this, MatrixActivity.class));
    }

    public void onClip(View view) {
        startActivity(new Intent(this, ClipActivity.class));
    }

    public void onText(View view) {
        startActivity(new Intent(this, TextActivity.class));
    }
}

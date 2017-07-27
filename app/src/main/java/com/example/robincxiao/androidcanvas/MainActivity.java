package com.example.robincxiao.androidcanvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.robincxiao.androidcanvas.path.PathEffectActivity;
import com.example.robincxiao.androidcanvas.path.PathMeasureActivity;
import com.example.robincxiao.androidcanvas.shader.ShaderActivity;
import com.example.robincxiao.androidcanvas.xfermodes.XfermodesActivity;

public class MainActivity extends AppCompatActivity {
    private Thread thread;

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
}

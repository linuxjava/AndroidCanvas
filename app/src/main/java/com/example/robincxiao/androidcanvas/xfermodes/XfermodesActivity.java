package com.example.robincxiao.androidcanvas.xfermodes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/6/23.
 * PorterDuffXfermode测试
 */

public class XfermodesActivity extends Activity {
    private static final String[] sLabels = {
            "Clear", "Src", "Dst", "SrcOver",
            "DstOver", "SrcIn", "DstIn", "SrcOut",
            "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen"
    };

    private XfermodesView mXfermodesView;
    private TextView mModeText;
    private int mIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_xfermodes);

        mModeText = (TextView) findViewById(R.id.text_mode);
        mXfermodesView = (XfermodesView) findViewById(R.id.xfermodesview);

        mModeText.setText(sLabels[mIndex]);
    }

    public void onXfermodes(View view) {
        mIndex = (mIndex + 1) % sLabels.length;
        mModeText.setText(sLabels[mIndex]);
        mXfermodesView.setIndex(mIndex);
        mXfermodesView.invalidate();
    }

}

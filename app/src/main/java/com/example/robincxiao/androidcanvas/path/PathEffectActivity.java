package com.example.robincxiao.androidcanvas.path;

import android.app.Activity;
import android.graphics.PathDashPathEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.robincxiao.androidcanvas.R;

/**
 * Created by robincxiao on 2017/7/27.
 * demo演示PathDashPathEffect的使用
 */

public class PathEffectActivity extends Activity {
    private PathView1 myView;
    private SeekBar seekBarAdvance, seekBarPhase;
    private Spinner spinnerStyle;

    private String[] styleNames = {
            "PathDashPathEffect.Style.MORPH",
            "PathDashPathEffect.Style.ROTATE",
            "PathDashPathEffect.Style.TRANSLATE"};
    private PathDashPathEffect.Style[] styleSettings = {
            PathDashPathEffect.Style.MORPH,
            PathDashPathEffect.Style.ROTATE,
            PathDashPathEffect.Style.TRANSLATE};

    private ArrayAdapter<String> spinnerStyleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_effect);

        myView = (PathView1) findViewById(R.id.myview);

        seekBarAdvance = (SeekBar) findViewById(R.id.advance);
        seekBarAdvance.setOnSeekBarChangeListener(seekBarAdvanceChangeListener);
        seekBarPhase = (SeekBar) findViewById(R.id.phase);
        seekBarPhase.setOnSeekBarChangeListener(seekBarPhaseChangeListener);

        spinnerStyle = (Spinner) findViewById(R.id.style);
        spinnerStyleAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, styleNames);
        spinnerStyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStyle.setAdapter(spinnerStyleAdapter);
        spinnerStyle.setOnItemSelectedListener(spinnerStyleOnItemSelectedListener);

    }

    SeekBar.OnSeekBarChangeListener seekBarAdvanceChangeListener =
            new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    myView.setAdvance(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

            };

    SeekBar.OnSeekBarChangeListener seekBarPhaseChangeListener =
            new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    myView.setPhase(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

            };

    AdapterView.OnItemSelectedListener spinnerStyleOnItemSelectedListener =
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    myView.setStype(styleSettings[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub

                }
            };

}

package com.intercoder.memorina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;

public class SettingsActivity extends AppCompatActivity {

    SeekBar seekBarCount, seekBarTimeout;
    TextView seekText, seekTextTime;
    Resources res;
    int COUNT_CARDS = 12;
    int TIMEOUT = 550;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        seekBarCount = findViewById(R.id.seekBar);
        seekBarTimeout = findViewById(R.id.seekBar_time);
        seekText = findViewById(R.id.seekbar_label);
        seekTextTime = findViewById(R.id.seekbar_label_time);
        res = getResources();

        COUNT_CARDS = getIntent().getIntExtra("count", 12);
        TIMEOUT = getIntent().getIntExtra("timeout", 550);
        //todo string res
        seekText.setText((String)(res.getString(R.string.label_count_cards) + " " + COUNT_CARDS));
        seekTextTime.setText((String)(res.getString(R.string.label_timeout) + " " + TIMEOUT + "mc"));
        seekBarCount.setProgress(COUNT_CARDS / 2 - 1);
        seekBarTimeout.setProgress(TIMEOUT / 10);

        seekBarCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                COUNT_CARDS = (i+1)*2;
                //todo string res
                seekText.setText((String)(res.getString(R.string.label_count_cards) + " " + COUNT_CARDS));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        seekBarTimeout.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TIMEOUT = i*10;
                //todo string res
                seekTextTime.setText((String)(res.getString(R.string.label_timeout) + " " + TIMEOUT + "mc"));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    public void onApply(View v){
        Intent intent = new Intent();
        intent.putExtra("count", COUNT_CARDS);
        intent.putExtra("timeout", TIMEOUT);
        setResult(RESULT_OK, intent);
        finish();
    }
}
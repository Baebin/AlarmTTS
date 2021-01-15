package org.techtown.alarmtts;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopUP_Time extends AppCompatActivity {
    CheckBox time_01, time_02, time_03, time_04, time_05, time_06, time_07, time_08, time_09, time_10, time_11, time_12,
            time_13, time_14, time_15, time_16, time_17, time_18, time_19, time_20, time_21, time_22, time_23, time_24;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_time);

        Display dp = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int)(dp.getWidth());
        //int height = (int)(dp.getHeight()*0.32);

        getWindow().getAttributes().width = width;
        //getWindow().getAttributes().height = height;

        //this.setFinishOnTouchOutside(false);

        time_01 = findViewById(R.id.checkBox_01);
        time_01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_01 = isChecked;
            }
        });

        time_02 = findViewById(R.id.checkBox_02);
        time_02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_02 = isChecked;
            }
        });

        time_03 = findViewById(R.id.checkBox_03);
        time_03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_03 = isChecked;
            }
        });

        time_04 = findViewById(R.id.checkBox_04);
        time_04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_04 = isChecked;
            }
        });

        time_05 = findViewById(R.id.checkBox_05);
        time_05.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_05 = isChecked;
            }
        });

        time_06 = findViewById(R.id.checkBox_06);
        time_06.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_06 = isChecked;
            }
        });

        time_07 = findViewById(R.id.checkBox_07);
        time_07.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_07 = isChecked;
            }
        });

        time_08 = findViewById(R.id.checkBox_08);
        time_08.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_08 = isChecked;
            }
        });

        time_09 = findViewById(R.id.checkBox_09);
        time_09.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_09 = isChecked;
            }
        });

        time_10 = findViewById(R.id.checkBox_10);
        time_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_10 = isChecked;
            }
        });

        time_11 = findViewById(R.id.checkBox_11);
        time_11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_11 = isChecked;
            }
        });

        time_12 = findViewById(R.id.checkBox_12);
        time_12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_12 = isChecked;
            }
        });

        time_13 = findViewById(R.id.checkBox_13);
        time_13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_13 = isChecked;
            }
        });

        time_14 = findViewById(R.id.checkBox_14);
        time_14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_14 = isChecked;
            }
        });

        time_15 = findViewById(R.id.checkBox_15);
        time_15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_15 = isChecked;
            }
        });

        time_16 = findViewById(R.id.checkBox_16);
        time_16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_16 = isChecked;
            }
        });

        time_17 = findViewById(R.id.checkBox_17);
        time_17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_17 = isChecked;
            }
        });

        time_18 = findViewById(R.id.checkBox_18);
        time_18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_18 = isChecked;
            }
        });

        time_19 = findViewById(R.id.checkBox_19);
        time_19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_19 = isChecked;
            }
        });

        time_20 = findViewById(R.id.checkBox_20);
        time_20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_20 = isChecked;
            }
        });

        time_21 = findViewById(R.id.checkBox_21);
        time_21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_21 = isChecked;
            }
        });

        time_22 = findViewById(R.id.checkBox_22);
        time_22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_22 = isChecked;
            }
        });

        time_23 = findViewById(R.id.checkBox_23);
        time_23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_23 = isChecked;
            }
        });

        time_24 = findViewById(R.id.checkBox_24);
        time_24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.time_24 = isChecked;
            }
        });

        Button button = findViewById(R.id.button_close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVIB(100);
                finish();
            }
        });

        time_01.setChecked(MainActivity.time_01);
        time_02.setChecked(MainActivity.time_02);
        time_03.setChecked(MainActivity.time_03);
        time_04.setChecked(MainActivity.time_04);
        time_05.setChecked(MainActivity.time_05);
        time_06.setChecked(MainActivity.time_06);
        time_07.setChecked(MainActivity.time_07);
        time_08.setChecked(MainActivity.time_08);
        time_09.setChecked(MainActivity.time_09);
        time_10.setChecked(MainActivity.time_10);
        time_11.setChecked(MainActivity.time_11);
        time_12.setChecked(MainActivity.time_12);
        time_13.setChecked(MainActivity.time_13);
        time_14.setChecked(MainActivity.time_14);
        time_15.setChecked(MainActivity.time_15);
        time_16.setChecked(MainActivity.time_16);
        time_17.setChecked(MainActivity.time_17);
        time_18.setChecked(MainActivity.time_18);
        time_19.setChecked(MainActivity.time_19);
        time_20.setChecked(MainActivity.time_20);
        time_21.setChecked(MainActivity.time_21);
        time_22.setChecked(MainActivity.time_22);
        time_23.setChecked(MainActivity.time_23);
        time_24.setChecked(MainActivity.time_24);
    }

    public void playVIB(int ms) {
        Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(ms);
    }
}

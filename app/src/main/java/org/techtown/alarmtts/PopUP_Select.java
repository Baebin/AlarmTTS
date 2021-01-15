package org.techtown.alarmtts;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopUP_Select extends AppCompatActivity {
    RadioButton rb_default;
    RadioButton rb_google;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_select);

        Display dp = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int)(dp.getWidth());
        //int height = (int)(dp.getHeight()*0.32);

        getWindow().getAttributes().width = width;
        //getWindow().getAttributes().height = height;

        //this.setFinishOnTouchOutside(false);

        rb_default = findViewById(R.id.radioButton_Default);
        rb_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sound_default = true;
            }
        });

        rb_google = findViewById(R.id.radioButton_Google);
        rb_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sound_default = false;
            }
        });

        rb_default.setChecked(MainActivity.sound_default);
        rb_google.setChecked(!MainActivity.sound_default);

        Button button_close = findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVIB(100);
                finish();
            }
        });
    }

    public void playVIB(int ms) {
        Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(ms);
    }
}

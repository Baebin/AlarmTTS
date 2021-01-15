package org.techtown.alarmtts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static Context context_main;

    String file = "data";

    AlarmSoundPlayer alarmSoundPlayer = new AlarmSoundPlayer();

    boolean start = true;
    public static boolean time_01, time_02, time_03, time_04, time_05, time_06, time_07, time_08, time_09, time_10, time_11, time_12,
            time_13, time_14, time_15, time_16, time_17, time_18, time_19, time_20, time_21, time_22, time_23, time_24;

    public static boolean sound_default = true;
    public static boolean sound = true;
    public static boolean vib = true;
    public static int vib_gauge;
    int volume;

    Intent AlarmBackgroundIntent, TTSserviceIntent;

    public static boolean firststart;

    CheckBox checkBox_Alarm, checkBox_Sound, checkBox_Vib;
    SeekBar seekBar_Sound, seekBar_Vib;

    TextView textView_Sound, textView_Vib;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmSoundPlayer.initSounds(getApplicationContext());

        textView_Sound = findViewById(R.id.textView_Sound);
        textView_Vib = findViewById(R.id.textView_Vib);

        checkBox_Alarm = findViewById(R.id.checkBox_Alarm);
        checkBox_Alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                start = isChecked;

                controlService(start);

                alarmSoundPlayer.play(AlarmSoundPlayer.click_01);
                playVIB(100);
            }
        });

        checkBox_Sound = findViewById(R.id.checkBox_Sound);
        checkBox_Sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sound = isChecked;

                alarmSoundPlayer.play(AlarmSoundPlayer.click_01);
                playVIB(100);
            }
        });

        checkBox_Vib = findViewById(R.id.checkBox_Vib_Use);
        checkBox_Vib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vib = isChecked;

                alarmSoundPlayer.play(AlarmSoundPlayer.click_01);
                playVIB(100);
            }
        });

        seekBar_Sound = findViewById(R.id.seekBar_Sound);
        seekBar_Sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume = progress;
                Volume();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (MainActivity.sound_default) {
                    alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_01);
                } else {
                    AlarmTTS.alarmTTS.speak("딩동!" + 1 + "시 입니다!");
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_Vib = findViewById(R.id.seekBar_Vib);
        seekBar_Vib.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vib_gauge = progress;
                Vib();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                playVIB(vib_gauge*20);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        getSF();
        Volume();
        Vib();

        controlService(start);

        if (firststart) {

        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        saveSF();

        Log.d(TAG, "onStop 호출됨");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curid = item.getItemId();

        switch (curid) {
            case R.id.menu_time:
                Intent intent_time = new Intent(MainActivity.this, PopUP_Time.class);
                startActivity(intent_time);
                break;
            case R.id.menu_select:
                Intent intent_sel = new Intent(MainActivity.this, PopUP_Select.class);
                startActivity(intent_sel);
                break;
            case R.id.menu_dev:
                Intent intent_dev = new Intent(MainActivity.this, PopUP_Dev.class);
                startActivity(intent_dev);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void playVIB(int ms) {
        Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(ms);
    }

    public void controlService(boolean status) {
        AlarmBackgroundIntent = new Intent(this, AlarmBackgroundService.class);
        TTSserviceIntent = new Intent(this, AlarmTTS.class);
        if (status) {
            startService(AlarmBackgroundIntent);
            startService(TTSserviceIntent);
        } else {
            stopService(AlarmBackgroundIntent);
            stopService(TTSserviceIntent);
        }
        Log.d(TAG, "controlService 호출됨 : " + status);
    }

    public void saveSF() {
        SharedPreferences sf = getSharedPreferences(file, MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();

        editor.putBoolean("start", start);
        editor.putBoolean("default", sound_default);
        editor.putBoolean("sound", sound);
        editor.putBoolean("vib", vib);

        editor.putBoolean("time_01", time_01);
        editor.putBoolean("time_02", time_02);
        editor.putBoolean("time_03", time_03);
        editor.putBoolean("time_04", time_04);
        editor.putBoolean("time_05", time_05);
        editor.putBoolean("time_06", time_06);
        editor.putBoolean("time_07", time_07);
        editor.putBoolean("time_08", time_08);
        editor.putBoolean("time_09", time_09);
        editor.putBoolean("time_10", time_10);
        editor.putBoolean("time_11", time_11);
        editor.putBoolean("time_12", time_12);
        editor.putBoolean("time_13", time_13);
        editor.putBoolean("time_14", time_14);
        editor.putBoolean("time_15", time_15);
        editor.putBoolean("time_16", time_16);
        editor.putBoolean("time_17", time_17);
        editor.putBoolean("time_18", time_18);
        editor.putBoolean("time_19", time_19);
        editor.putBoolean("time_20", time_20);
        editor.putBoolean("time_21", time_21);
        editor.putBoolean("time_22", time_22);
        editor.putBoolean("time_23", time_23);
        editor.putBoolean("time_24", time_24);

        editor.putInt("volume", volume);
        editor.putInt("vib_gauge", vib_gauge);
        editor.apply();
    }

    public void getSF() {
        SharedPreferences sf = getSharedPreferences(file, MODE_PRIVATE);

        start = sf.getBoolean("start", true);
        sound_default = sf.getBoolean("default", true);
        sound = sf.getBoolean("sound", true);
        vib = sf.getBoolean("vib", true);

        time_01 = sf.getBoolean("time_01", true);
        time_02 = sf.getBoolean("time_02", true);
        time_03 = sf.getBoolean("time_03", true);
        time_04 = sf.getBoolean("time_04", true);
        time_05 = sf.getBoolean("time_05", true);
        time_06 = sf.getBoolean("time_06", true);
        time_07 = sf.getBoolean("time_07", true);
        time_08 = sf.getBoolean("time_08", true);
        time_09 = sf.getBoolean("time_09", true);
        time_10 = sf.getBoolean("time_10", true);
        time_11 = sf.getBoolean("time_11", true);
        time_12 = sf.getBoolean("time_12", true);
        time_13 = sf.getBoolean("time_13", true);
        time_14 = sf.getBoolean("time_14", true);
        time_15 = sf.getBoolean("time_15", true);
        time_16 = sf.getBoolean("time_16", true);
        time_17 = sf.getBoolean("time_17", true);
        time_18 = sf.getBoolean("time_18", true);
        time_19 = sf.getBoolean("time_19", true);
        time_20 = sf.getBoolean("time_20", true);
        time_21 = sf.getBoolean("time_21", true);
        time_22 = sf.getBoolean("time_22", true);
        time_23 = sf.getBoolean("time_23", true);
        time_24 = sf.getBoolean("time_24", true);

        volume = sf.getInt("volume", 100);
        vib_gauge = sf.getInt("vib_gauge", 100);

        firststart = sf.getBoolean("first_start", true);


        if (checkBox_Alarm != null) {
            checkBox_Alarm.setChecked(start);
        }
        if (checkBox_Sound != null) {
            checkBox_Sound.setChecked(sound);
        }
        if (checkBox_Vib != null) {
            checkBox_Vib.setChecked(vib);
        }
        if (seekBar_Sound != null) {
            seekBar_Sound.setProgress(volume);
        }
        if (seekBar_Vib != null) {
            seekBar_Vib.setProgress(vib_gauge);
        }
    }

    public void Volume() {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
        am.getStreamVolume(am.STREAM_MUSIC);
        am.setStreamVolume(am.STREAM_MUSIC, (volume*amStreamMusicMaxVol)/100,0 );

        if (textView_Sound != null) {
            textView_Sound.setText("소리 크기 (" + volume + "%)");
        }
    }

    public void Vib() {
        if (textView_Vib != null) {
            textView_Vib.setText("진동 설정 (" + (float)vib_gauge/50 + "초)");
        }
    }
}
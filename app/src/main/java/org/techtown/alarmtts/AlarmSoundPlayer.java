package org.techtown.alarmtts;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class AlarmSoundPlayer {
    public static final int rabbit_hour_01 = R.raw.rabbit_hour_1;
    public static final int rabbit_hour_02 = R.raw.rabbit_hour_2;
    public static final int rabbit_hour_03 = R.raw.rabbit_hour_3;
    public static final int rabbit_hour_04 = R.raw.rabbit_hour_4;
    public static final int rabbit_hour_05 = R.raw.rabbit_hour_5;
    public static final int rabbit_hour_06 = R.raw.rabbit_hour_6;
    public static final int rabbit_hour_07 = R.raw.rabbit_hour_7;
    public static final int rabbit_hour_08 = R.raw.rabbit_hour_8;
    public static final int rabbit_hour_09 = R.raw.rabbit_hour_9;
    public static final int rabbit_hour_10 = R.raw.rabbit_hour_10;
    public static final int rabbit_hour_11 = R.raw.rabbit_hour_11;
    public static final int rabbit_hour_12 = R.raw.rabbit_hour_12;

    public static final int bell = R.raw.bell;

    public static final int click_01 = R.raw.click_01;

    private static SoundPool soundPool;
    private static HashMap<Integer, Integer> soundPoolMap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void initSounds(Context context) {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        soundPoolMap = new HashMap(13);

        soundPoolMap.put(rabbit_hour_01, soundPool.load(context, rabbit_hour_01, 1));
        soundPoolMap.put(rabbit_hour_02, soundPool.load(context, rabbit_hour_02, 2));
        soundPoolMap.put(rabbit_hour_03, soundPool.load(context, rabbit_hour_03, 3));
        soundPoolMap.put(rabbit_hour_04, soundPool.load(context, rabbit_hour_04, 4));
        soundPoolMap.put(rabbit_hour_05, soundPool.load(context, rabbit_hour_05, 5));
        soundPoolMap.put(rabbit_hour_06, soundPool.load(context, rabbit_hour_06, 6));
        soundPoolMap.put(rabbit_hour_07, soundPool.load(context, rabbit_hour_07, 7));
        soundPoolMap.put(rabbit_hour_08, soundPool.load(context, rabbit_hour_08, 8));
        soundPoolMap.put(rabbit_hour_09, soundPool.load(context, rabbit_hour_09, 9));
        soundPoolMap.put(rabbit_hour_10, soundPool.load(context, rabbit_hour_10, 10));
        soundPoolMap.put(rabbit_hour_11, soundPool.load(context, rabbit_hour_11, 11));
        soundPoolMap.put(rabbit_hour_12, soundPool.load(context, rabbit_hour_12, 12));

        soundPoolMap.put(bell, soundPool.load(context, bell, 13));

        soundPoolMap.put(click_01, soundPool.load(context, click_01, 14));
    }

    public static void play(int position){
        if( soundPoolMap.containsKey(position) ) {
            soundPool.play(soundPoolMap.get(position), 1, 1, 1, 0, 1f);
        }
    }

}

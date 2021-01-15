package org.techtown.alarmtts;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.Locale;

public class AlarmTTS extends Service implements TextToSpeech.OnInitListener {
    private static final String  TAG = "TTS";

    public static AlarmTextToSpeech alarmTTS = new AlarmTextToSpeech();

    @Override
    public void onCreate() {
        alarmTTS.tts = new TextToSpeech(this, this);
        alarmTTS.tts.setLanguage(Locale.KOREAN);
        alarmTTS.tts.setPitch(1.0f);
        alarmTTS.tts.setSpeechRate(1.0f);
        Log.d(TAG, "tts 초기화됨");

        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "onStart 호출됨");

        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (alarmTTS.tts != null) {
            alarmTTS.tts.stop();
            alarmTTS.tts.shutdown();
            alarmTTS.tts = null;
        }

        Log.d(TAG, "onDestory 호출됨");
    }

    @Override
    public void onInit(int status) {
        Log.d(TAG, "onInit 호출됨");
    }

    static class AlarmTextToSpeech {
        TextToSpeech tts;
        public void speak(String text) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
            } else {
                tts.speak(text, TextToSpeech.QUEUE_ADD, null);
            }
        }
    }
}

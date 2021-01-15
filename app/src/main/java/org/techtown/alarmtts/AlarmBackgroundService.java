package org.techtown.alarmtts;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.service.notification.NotificationListenerService;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AlarmBackgroundService extends Service {
    private static final String TAG = "AlarmBackgroundService";
    private AlarmTimer alarmTimer;

    AlarmSoundPlayer alarmSoundPlayer = new AlarmSoundPlayer();

    ArrayList<String> arrayList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {
        super.onCreate();

        alarmSoundPlayer.initSounds(getApplicationContext());

        Log.d(TAG, "onCreate 호출됨");

        alarmTimer = new AlarmTimer();
        alarmTimer.start();

        arrayList = new ArrayList<>();

        startForegroundService();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        alarmTimer.interrupt();
        super.onDestroy();

        Log.d(TAG, "onDestory 호출됨");
    }

    private void startForegroundService() {
        Log.d(TAG, "startForegroundService 호출됨");

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle()
                .setBigContentTitle("알리미가 작동중입니다!");

        Bitmap LargeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_main);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "1")
                        .setSmallIcon(R.mipmap.ic_main)
                        .setLargeIcon(LargeIcon)
                        .setContentTitle("알리미가 작동중입니다!")
                        .setOngoing(true)
                        .setWhen(0)
                        .setShowWhen(false)
                        .setStyle(style);

        Intent notiIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notiIntent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 버전 체크
            mNotificationManager.createNotificationChannel(new NotificationChannel("1", "포그라운드 서비스", NotificationManager.IMPORTANCE_NONE));
        }

        Notification notification = builder.build();
        startForeground(1, notification);
    }

    class AlarmTimer extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(20000);

                    SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
                    String mtime = sdf.format(new Date());
                    String mtime2 = sdf2.format(new Date());
                    Log.d(TAG, mtime + ", " + mtime2);

                    for (int i = 1; i < 13; i++) {
                        if (i >= 10) {
                            Log.d(TAG, "Now : " + i + "00");
                            if (mtime.contains(i + "00")) {
                                if (!arrayList.contains(mtime)) {
                                    arrayList.add(mtime);
                                    if (MainActivity.sound) {
                                        if (MainActivity.sound_default) {
                                            if (i == 12) {
                                                if (mtime2 == "00") {
                                                    arrayList = new ArrayList<>();
                                                    arrayList.add(mtime);
                                                    if (MainActivity.time_24) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_12);
                                                    }
                                                } else {
                                                    arrayList = new ArrayList<>();
                                                    arrayList.add(mtime);
                                                    if (MainActivity.time_12) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_12);
                                                    }
                                                }
                                            } else if (i == 11) {
                                                if (mtime2 == "23") {
                                                    arrayList.remove("1200");
                                                    if (MainActivity.time_23) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_11);
                                                    }
                                                } else {
                                                    arrayList.remove("1200");
                                                    if (MainActivity.time_11) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_11);
                                                    }
                                                }
                                            } else {
                                                if (mtime2 == "22") {
                                                    if (MainActivity.time_22) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_10);
                                                    }
                                                } else {
                                                    if (MainActivity.time_10) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_10);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (i == 12) {
                                                if (mtime2 == "00") {
                                                    arrayList = new ArrayList<>();
                                                    arrayList.add(mtime);
                                                    if (MainActivity.time_24) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("열두시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    arrayList = new ArrayList<>();
                                                    arrayList.add(mtime);
                                                    if (MainActivity.time_12) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("열두시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 11) {
                                                if (mtime2 == "23") {
                                                    arrayList.remove("1200");
                                                    if (MainActivity.time_23) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("열한시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    arrayList.remove("1200");
                                                    if (MainActivity.time_11) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("열한시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else {
                                                if (mtime2 == "22") {
                                                    if (MainActivity.time_22) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("열시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_10) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("열시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (MainActivity.vib) {
                                        playVIB(MainActivity.vib_gauge*20);
                                    }
                                    Log.d(TAG, "mtime 1 Speak");
                                }
                            }
                        } else {
                            Log.d(TAG, "Now : 0" + i + "00");
                            if (mtime.contains("0" + i + "00")) {
                                if (!arrayList.contains(mtime)) {
                                    arrayList.add(mtime);
                                    if (MainActivity.sound) {
                                        if (MainActivity.sound_default) {
                                            if (i == 9) {
                                                if (mtime2 == "21") {
                                                    if (MainActivity.time_21) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_09);
                                                    }
                                                } else {
                                                    if (MainActivity.time_09) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_09);
                                                    }
                                                }
                                            } else if (i == 8) {
                                                if (mtime2 == "20") {
                                                    if (MainActivity.time_20) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_08);
                                                    }
                                                } else {
                                                    if (MainActivity.time_08) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_08);
                                                    }
                                                }
                                            } else if (i == 7) {
                                                if (mtime2 == "19") {
                                                    if (MainActivity.time_19) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_07);
                                                    }
                                                } else {
                                                    if (MainActivity.time_07) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_07);
                                                    }
                                                }
                                            } else if (i == 6) {
                                                if (mtime2 == "18") {
                                                    if (MainActivity.time_18) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_06);
                                                    }
                                                } else {
                                                    if (MainActivity.time_06) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_06);
                                                    }
                                                }
                                            } else if (i == 5) {
                                                if (mtime2 == "17") {
                                                    if (MainActivity.time_17) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_05);
                                                    }
                                                } else {
                                                    if (MainActivity.time_05) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_05);
                                                    }
                                                }
                                            } else if (i == 4) {
                                                if (mtime2 == "16") {
                                                    if (MainActivity.time_16) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_04);
                                                    }
                                                } else {
                                                    if (MainActivity.time_04) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_04);
                                                    }
                                                }
                                            } else if (i == 3) {
                                                if (mtime2 == "15") {
                                                    if (MainActivity.time_15) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_03);
                                                    }
                                                } else {
                                                    if (MainActivity.time_03) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_03);
                                                    }
                                                }
                                            } else if (i == 2) {
                                                if (mtime2 == "14") {
                                                    if (MainActivity.time_14) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_02);
                                                    }
                                                } else {
                                                    if (MainActivity.time_02) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_02);
                                                    }
                                                }
                                            } else {
                                                if (mtime2 == "13") {
                                                    if (MainActivity.time_13) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_01);
                                                    }
                                                } else {
                                                    if (MainActivity.time_01) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.rabbit_hour_01);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (i == 9) {
                                                if (mtime2 == "21") {
                                                    if (MainActivity.time_21) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("아홉시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_09) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("아홉시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 8) {
                                                if (mtime2 == "20") {
                                                    if (MainActivity.time_20) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("여덟시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_08) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("여덟시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 7) {
                                                if (mtime2 == "19") {
                                                    if (MainActivity.time_19) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("일곱시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_07) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("일곱시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 6) {
                                                if (mtime2 == "18") {
                                                    if (MainActivity.time_18) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("여섯시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_06) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("여섯시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 5) {
                                                if (mtime2 == "17") {
                                                    if (MainActivity.time_17) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("다섯시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_05) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("다섯시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 4) {
                                                if (mtime2 == "16") {
                                                    if (MainActivity.time_16) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("네시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_04) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("네시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 3) {
                                                if (mtime2 == "15") {
                                                    if (MainActivity.time_15) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("세시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_03) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("세시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else if (i == 2) {
                                                if (mtime2 == "14") {
                                                    if (MainActivity.time_14) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("두시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_02) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("두시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            } else {
                                                if (mtime2 == "13") {
                                                    if (MainActivity.time_13) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("한시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                } else {
                                                    if (MainActivity.time_01) {
                                                        alarmSoundPlayer.play(AlarmSoundPlayer.bell);
                                                        Thread.sleep(800);
                                                        //AlarmTTS.alarmTTS.speak("한시 입니다!");
                                                        AlarmTTS.alarmTTS.speak("딩동!" + i + "시 입니다!");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (MainActivity.vib) {
                                        playVIB(MainActivity.vib_gauge*20);
                                    }
                                    Log.d(TAG, "mtime 2 Speak");
                                }
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void playVIB(int ms) {
        Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(ms);
    }
}

package com.hima.atef.splash.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.hima.atef.splash.R;


public class Service extends android.app.Service {
    private NotificationManager notificationManager;
    private MediaPlayer mediaPlayer;


    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String ceooo = intent.getStringExtra("timez");
        int x = intent.getIntExtra("diss", 0);
        if (x == 551) {
            stopImportantJob();
        } else {
            doImportatJob(ceooo);
            mediaPlayer = MediaPlayer.create(this, R.raw.rev);
            mediaPlayer.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationManager.cancelAll();
        stopImportantJob();
    }

    void doImportatJob(String x) {
        Intent stopservice = new Intent(this, Service.class);
        stopservice.putExtra("diss", 551);
        PendingIntent pendingStop = PendingIntent.getService(this, 0, stopservice, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Note")
                .setContentText(x)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Starting up!!!")
                .setOngoing(false) //by default false
                .setContentIntent(pendingStop)
                .build();

        /*notificationManager.notify(22, notification);*/

        startForeground(1, notification);
    }

    private void stopImportantJob() {

        stopForeground(true);
        mediaPlayer.stop();
        stopSelf();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            notificationManager.cancel(22);
            stopSelf();
            stopForeground(STOP_FOREGROUND_DETACH); //now you can dismiss the notification
            stopForeground(STOP_FOREGROUND_REMOVE);
        }
    }
}









package com.hima.atef.splash.Alarm;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hima.atef.splash.Db.DbContract;
import com.hima.atef.splash.Db.DbHelper;
import com.hima.atef.splash.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class SecServ extends android.app.Service {
    private MediaPlayer mediaPlayer;
    Context context;
    public DbHelper myDbHelper;
    public SQLiteDatabase db;
    ArrayList<String> arrayEmsak, arrayFagr, arrayMagreb;
    String magreb = "حان الآن موعد أذان المغرب";
    String Fager = "حان الآن موعد أذان الفجر ";
    String emsak = "باقي علي اذان الفجر خمسة دقائق";
    public long counter = 300000;
    private Activity localActivity;
    int lolo = 0;


    private NotificationManager notificationManager;

    public SecServ() throws ParseException {
    }

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        myDbHelper = new DbHelper(this);
        arrayEmsak = new ArrayList<>();
        arrayFagr = new ArrayList<>();
        arrayMagreb = new ArrayList<>();

        parseSQL();
/*
        alaaarm();
*/
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String ceooo = intent.getStringExtra("timez");
        int x = intent.getIntExtra("diss", 0);
        if (x == 551) {
            mediaPlayer.stop();
        } else {
/*
            alaaarm();
*/
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationManager.cancelAll();
        stopImportantJob();
    }

    void doImportatJob(String ky,long days, long hours, long minutes, long seconds) {
        Intent stopservice = new Intent(this, SecServ.class);
        PendingIntent pendingStop = PendingIntent.getActivity(this, 0, stopservice, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(ky)
                .setContentText( "" + hours % 24 + ":" + minutes % 60 + ":" + seconds % 60 + "")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Starting up!!!")
                .setOngoing(false) //by default false
                .setContentIntent(pendingStop)
                .build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;

        /*notificationManager.notify(22, notification);*/
        startForeground(19, notification);
    }

    private void stopImportantJob() {

        stopForeground(true);
        stopSelf();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            notificationManager.cancel(223);
            stopSelf();
            stopForeground(STOP_FOREGROUND_DETACH); //now you can dismiss the notification
            stopForeground(STOP_FOREGROUND_REMOVE);
        }
    }

    private void parseSQL() {
        db = myDbHelper.getReadableDatabase();
        String[] projection = {"*"};
        Cursor dbcursor = db.query(
                DbContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        while (dbcursor.moveToNext()) {
            /*long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedEntry._ID));*/

            /*dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_EMSAK);*/
            String EmsakString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_EMSAK));
            String FagrString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_ZOHR));
            String MagrebString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_MAGREB));

            arrayEmsak.add(EmsakString);
            arrayFagr.add(FagrString);
            arrayMagreb.add(MagrebString);
        }

        timerMethod();
         lolo = 0;
        timer.start();

        dbcursor.close();
    }

/*
    public void setAlarm(long time, int id) {

        //getting the alarm manager
        */
/*AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);*//*


        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(context, Service.class);
        if (id >= 100 && id < 300) {
            i.putExtra("timez", emsak);
        } else if (id >= 300 && id < 500) {
            i.putExtra("timez", Fager);
        } else if (id >= 500) {
            i.putExtra("timez", magreb);

        }

        //creating a pending intent using the intent
        PendingIntent pendingIntent = PendingIntent.getService(context, id, i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int ALARM_TYPE = AlarmManager.RTC_WAKEUP;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            am.setExactAndAllowWhileIdle(ALARM_TYPE, time, pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            am.setExact(ALARM_TYPE, time, pendingIntent);
        else
            am.set(ALARM_TYPE, time, pendingIntent);

        */
/*Toast.makeText(context, "Alarm is set", Toast.LENGTH_SHORT).show();*//*

    }
*/

/*
    public void alaaarm() {
        Log.e("Tagyyy", arrayEmsak.toString());
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < arrayEmsak.size(); i++) {
            arrayEmsak.get(i);
            arrayFagr.get(i);
            arrayMagreb.get(i);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String Emsak = arrayEmsak.get(i);
            String Fagr = arrayEmsak.get(i);
            String Magreb = arrayEmsak.get(i);


            try {
                long EmsakTime = format.parse(Emsak).getTime();
                long FagrTime = format.parse(Fagr).getTime();
                long MagrebTime = format.parse(Magreb).getTime();
                long xxx = calendar.getTimeInMillis();

                if (xxx > EmsakTime) {
                    EmsakTime = format.parse("2099-10-1 03:30").getTime();
                }
                if (xxx > FagrTime) {
                    FagrTime = format.parse("2099-10-2 03:30").getTime();
                }
                if (xxx > MagrebTime) {
                    MagrebTime = format.parse("2099-10-3 03:30").getTime();
                }

                setAlarm(EmsakTime, i + 100);
                setAlarm(FagrTime, i + 300);
                setAlarm(MagrebTime, i + 500);


            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
*/

    public String k1 = "باقي علي الأمساك";
    public String k2 = "باقي علي أذان الفجر";
    public String k3 = "باقي علي أذان المغرب";
    public String o1 = "حان الأن موعد الامساك عن الطعام";
    public String o2 = "حان الأن موعد أذان الفجر";
    public String o3 = "حان الأن موعد أذان المغرب";
    public String ooo = "";
    public String kkk = "";

    public long calendertime() throws ParseException {
        Calendar calendar1 = Calendar.getInstance();
        long l = calendar1.getTimeInMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");



        String time1 = arrayEmsak.get(lolo);
        String time2 = arrayFagr.get(lolo);
        String time3 = arrayMagreb.get(lolo);
        long t1 = format.parse(time1).getTime();
        long t2 = format.parse(time2).getTime();
        long t3 = format.parse(time3).getTime();

        if (l < t1 ) {
            kkk = k1 ;
            ooo = o1;
            return t1 - l;
        }
        else if (l > t1 && l < t2) {
            kkk = k2 ;
            ooo = o2 ;
            return t2 - l;
        }
        else if (l > t1 && l > t2 && l < t3) {
            kkk = k3 ;
            ooo = o3 ;
            return t3 - l;
        }
        else //if(l>t1 && l>t2 && l>t3)
        {
            lolo++;
            calendertime();
            return calendertime();
        }
    }

    public void media(){
        mediaPlayer = MediaPlayer.create(context, R.raw.rev);
        mediaPlayer.start();
    }

    public CountDownTimer timer;

    public void timerMethod() {
        try {
            timer = new CountDownTimer(calendertime(), 1000) {

                public void onTick(long millisUntilFinished) {

                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;

                    counter = hours;
                    doImportatJob(kkk,days, hours, minutes, seconds);

                }

                public void onFinish() {
                    counter = 0;
                    media();
                    Notifiy();
                    parseSQL();

           /* //Kill the game
            int i = android.os.Process.myPid();
            android.os.Process.killProcess(i);*/

                }

            };
        } catch (Exception e) {
        }
    }
    public void Notifiy(){

        Intent stopservice = new Intent(this, SecServ.class);
        stopservice.putExtra("diss", 551);
        PendingIntent pendingStop = PendingIntent.getService(this, 0, stopservice, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notificationL = new NotificationCompat.Builder(this)
                .setContentTitle(ooo)
                .setContentText("أضغط للأيقاف")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Starting up!!!")
                .setOngoing(false) //by default false
                .setContentIntent(pendingStop)
                .setDeleteIntent(pendingStop)
                .build();



        notificationManager.notify(1111,notificationL);
    }



}




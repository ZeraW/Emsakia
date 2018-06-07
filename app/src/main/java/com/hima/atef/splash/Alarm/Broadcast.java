package com.hima.atef.splash.Alarm;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.hima.atef.splash.Db.DbContract;
import com.hima.atef.splash.Db.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hima on 3/23/2018.
 */

public class Broadcast extends WakefulBroadcastReceiver {
   /* SQLiteDatabase db;
    ArrayList<String> arrayEmsak,arrayFagr,arrayMagreb;
    public DbHelper mDbHelper ;
    public MyAlarm ialarm;*/

    @Override
    public void onReceive(Context context, Intent intent) {
        /*ialarm = new MyAlarm(context);
        mDbHelper = new DbHelper(context);
        parseSQL();
        AlarmSetting()*/;

        Intent service = new Intent(context, SecServ.class);
        context.startService(service);

    }





   /* private void parseSQL() {
        db = mDbHelper.getReadableDatabase();
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

        arrayEmsak = new ArrayList<>();
        arrayFagr = new ArrayList<>();
        arrayMagreb = new ArrayList<>();



        while (dbcursor.moveToNext()) {
            *//*long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedEntry._ID));*//*

            *//*dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_EMSAK);*//*
            String EmsakString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_EMSAK));
            String FagrString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_FAGR));
            String MagrebString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_MAGREB));

            arrayEmsak.add(EmsakString);
            arrayFagr.add(FagrString);
            arrayMagreb.add(MagrebString);
        }

        dbcursor.close();

    }
    private void AlarmSetting(){
        Calendar calendar = Calendar.getInstance();
        for (int i=0;i<arrayEmsak.size();i++){
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
                long x = calendar.getTimeInMillis();




                if(x > EmsakTime){
                    EmsakTime = format.parse("2099-10-1 03:30").getTime();
                }
                if(x > FagrTime){
                    FagrTime = format.parse("2099-10-2 03:30").getTime();
                }
                if(x > MagrebTime){
                    MagrebTime = format.parse("2099-10-3 03:30").getTime();
                }

                ialarm.setAlarm(EmsakTime,i+100);
                ialarm.setAlarm(FagrTime,i+300);
                ialarm.setAlarm(MagrebTime,i+500);



            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }*/

}


package com.hima.atef.splash.Db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;





/**
 * Created by hima on 3/25/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DbContract.FeedEntry.TABLE_NAME + " (" +
                    DbContract.FeedEntry._ID + " INTEGER PRIMARY KEY ," +
                    DbContract.FeedEntry.COLUMN_NAME_DATE + " TEXT," +
                    DbContract.FeedEntry.COLUMN_NAME_EMSAK + " TEXT,"+
                    DbContract.FeedEntry.COLUMN_NAME_SHROUQ + " TEXT," +
                    DbContract.FeedEntry.COLUMN_NAME_FAGR + " TEXT," +
                    DbContract.FeedEntry.COLUMN_NAME_ZOHR + " TEXT," +
                    DbContract.FeedEntry.COLUMN_NAME_ASR + " TEXT," +
                    DbContract.FeedEntry.COLUMN_NAME_MAGREB + " TEXT," +
                    DbContract.FeedEntry.COLUMN_NAME_ISHAA + " TEXT" + " );";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbContract.FeedEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDbTry.db";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}

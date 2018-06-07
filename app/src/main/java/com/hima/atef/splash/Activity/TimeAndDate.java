package com.hima.atef.splash.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hima.atef.splash.Adapters.AdapterTimeAndDate;
import com.hima.atef.splash.Alarm.SecServ;
import com.hima.atef.splash.Db.DbContract;
import com.hima.atef.splash.Db.DbHelper;
import com.hima.atef.splash.Utilities.ModelTimeAndDate;
import com.hima.atef.splash.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimeAndDate extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private AdapterTimeAndDate mAdapter;
    private ArrayList<ModelTimeAndDate> modelArrayList;
    ArrayList<String> arrayEmsak,arrayFagr,arrayMagreb;
    private RequestQueue mRequestQueue;
    private ArrayList<String> modelArrayList2;
    private Button btnstart,btnStop;

    SharedPreferences prefs = null;


    public DbHelper mDbHelper = new DbHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_date);

        prefs = getSharedPreferences("com.hima.atef.splash.Activity", MODE_PRIVATE);
        btnstart = findViewById(R.id.TADSetAlarm);
        btnStop = findViewById(R.id.TADSTopAlarm);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(TimeAndDate.this, SecServ.class));

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(TimeAndDate.this, SecServ.class));

            }
        });




        mRecyclerView = findViewById(R.id.TADRecycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = mDbHelper.getWritableDatabase();


        modelArrayList2 = new ArrayList<>();

        for (int i = 0; i < 30; i++) {

            modelArrayList2.add(i, "الإمساك" + "\n" +
                    "الفجر" + "\n" +
                    "الشروق" + "\n" +
                    "الظهر" + "\n" +
                    "العصر" + "\n" +
                    "المغرب" + "\n" +
                    "العشاء");
        }


        modelArrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
/*
        parseSQL();
*/

    }

    private void parseSQL() {
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
            /*long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedEntry._ID));*/

            /*dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_EMSAK);*/
            String EmsakString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_EMSAK));
            String FagrString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_FAGR));
            String MagrebString = dbcursor.getString(dbcursor.getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_MAGREB));

            arrayEmsak.add(EmsakString);
            arrayFagr.add(FagrString);
            arrayMagreb.add(MagrebString);
        }

        dbcursor.close();
        
    }

    private void parseJSON() {

        String url = "http://api.islamhouse.com/v1/EZnGw27YiLl8DA7E/services/praytime/get-times/2018-04-04/2018-05-03/Egypt/30.0599153/31.2620199/+2/json   ";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray Arraydays = response.getJSONArray("days");
                    for (int i = 0; i < Arraydays.length(); i++) {
                        JSONObject jsonObject = Arraydays.getJSONObject(i);
                        String date = jsonObject.getString("date");
                        JSONArray timesArray = jsonObject.getJSONArray("times");

                        String Fagr = timesArray.optString(0);
                        String Shrouq = timesArray.optString(1);
                        String Zohr = timesArray.optString(2);
                        String Asr = timesArray.optString(3);
                        String Magreb = timesArray.optString(5);
                        String Ishaa = timesArray.optString(6);
                        String Emsak = "";

                        String x1 = Fagr.substring(0, 3);
                        String y2 = Fagr.substring(3, 5);
                        int y3 = Integer.valueOf(y2);
                        int y4 = y3 - 5;

                        String x2 = String.valueOf(y4);
                        if (x2.length() < 2) {
                            Emsak = x1 + "0" + x2;
                        } else {
                            Emsak = x1 + x2;
                        }




                        //dah 3lshan t3ml check an el app bysht3'l le awl mra 3lshan my3mlsh save lel DBSQL kol mra el activity de ttft7
                        if (prefs.getBoolean("firstrun", true)) {

                            // Create a new map of values, where column names are the keys
                            ContentValues values = new ContentValues();
                            values.put(DbContract.FeedEntry._ID, i + 1);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_DATE, date);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_EMSAK, date + " " + Emsak);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_FAGR, date + " " + Fagr);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_SHROUQ, date + " " + Shrouq);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_ZOHR, date + " " + Zohr);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_ASR, date + " " + Asr);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_MAGREB, date + " " + Magreb);
                            values.put(DbContract.FeedEntry.COLUMN_NAME_ISHAA, date + " " + Ishaa);


                            // Insert the new row, returning the primary key value of the new row
                            db.insert(DbContract.FeedEntry.TABLE_NAME, null, values);

                            //el code da tb3 el Sharedpref ely aana ast5dmtha fe el if Condition
                        }


                        modelArrayList.add(new ModelTimeAndDate(date, Emsak, Fagr, Shrouq, Zohr, Asr, Magreb, Ishaa));


                    }
                    prefs.edit().putBoolean("firstrun", false).commit();

                    mAdapter = new AdapterTimeAndDate(TimeAndDate.this, modelArrayList, modelArrayList2);
                    mRecyclerView.setAdapter(mAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TimeAndDate.this, "Hi from here" + error.toString(), Toast.LENGTH_LONG).show();


            }
        });
        mRequestQueue.add(request);

    }
}

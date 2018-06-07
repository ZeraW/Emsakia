package com.hima.atef.splash.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hima.atef.splash.Adapters.AdapterPrayers;
import com.hima.atef.splash.R;

import java.util.ArrayList;

public class Prayers extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterPrayers mAdapter;
    private ArrayList<String>modelArrayList;
    private ArrayList<String>modelArrayList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);

        mRecyclerView = findViewById(R.id.PrayerRecycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        modelArrayList = new ArrayList<>();
        modelArrayList2 = new ArrayList<>();

        modelArrayList2.add("<font color='red'>دعاء</font>");
        modelArrayList.add(

                "اللَّهُمَّ أنَْتَ رَبيِّ لَا إلِهََ إلَِّا أنَتَ،" +
                " خَلَقْتنَيِ وَأنََا عَبدُْكَ،" +
                " وَأنََا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ،" +
                " أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ،" +
                " أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ."

        );


        mAdapter = new AdapterPrayers(this,modelArrayList,modelArrayList2);
        mRecyclerView.setAdapter(mAdapter);

    }
}

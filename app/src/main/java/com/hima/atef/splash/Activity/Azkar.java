package com.hima.atef.splash.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hima.atef.splash.Adapters.AdapterAzkar;
import com.hima.atef.splash.R;

import java.util.ArrayList;

public class Azkar extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AdapterAzkar mAdapter;
    private ArrayList<String>modelArrayList;
    private ArrayList<Integer>modelArrayList2;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar);

        mRecyclerView = findViewById(R.id.AzkarRecycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        modelArrayList = new ArrayList<>();
        modelArrayList2 = new ArrayList<>();



        modelArrayList2.add(sum = 3);
        modelArrayList.add(
                "اللَّهُمَّ أنَْتَ رَبيِّ لَا إلِهََ إلَِّا أنَتَ،" +
                        " خَلَقْتنَيِ وَأنََا عَبدُْكَ،" +
                        " وَأنََا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ،" +
                        " أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ،" +
                        " أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ."

        );


        mAdapter = new AdapterAzkar(this,modelArrayList,modelArrayList2);
        mRecyclerView.setAdapter(mAdapter);
    }
}

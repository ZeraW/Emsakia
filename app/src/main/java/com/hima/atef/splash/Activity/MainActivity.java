package com.hima.atef.splash.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hima.atef.splash.R;

public class MainActivity extends AppCompatActivity {
    ImageButton btnCounter,btnPrayers,btnAzkar,btnTAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnCounter = findViewById(R.id.MainCounter);
        btnPrayers = findViewById(R.id.MainPrayers);
        btnAzkar = findViewById(R.id.MainAzkar);
        btnTAD = findViewById(R.id.MainTAD);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(),Counter.class);
                startActivity(intent);
            }
        });
        btnPrayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Prayers.class);
                startActivity(intent);
            }
        });

        btnAzkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Azkar.class);
                startActivity(intent);
            }
        });
        btnTAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),TimeAndDate.class);
                startActivity(intent);
            }
        });


    }
}

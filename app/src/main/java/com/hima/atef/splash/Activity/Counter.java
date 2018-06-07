package com.hima.atef.splash.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hima.atef.splash.R;

public class Counter extends AppCompatActivity {

    ImageButton counterbtn ;
    TextView counterTxt;
    int sum = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        counterbtn = findViewById(R.id.btnCounter);
        counterTxt = findViewById(R.id.txtCounter);

        counterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sum = sum +1;
                counterTxt.setText(String.valueOf(sum));
            }
        });
    }


}

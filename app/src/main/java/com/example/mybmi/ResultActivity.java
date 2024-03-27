package com.example.mybmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView show = findViewById(R.id.tvShowBMI);
        ImageView img = findViewById(R.id.ivShow);
        double bmi = getIntent().getDoubleExtra("bmi", 0);
        show.setText(String.valueOf(bmi));
        if (bmi < 18.5){
            show.setText("體重過輕");
            img.setImageResource(R.drawable.擷取1);
        }
        else if (bmi <= 24){
            show.setText("體重正常");
            img.setImageResource(R.drawable.擷取2);
        }
        else{
            show.setText("體重過重");
            img.setImageResource(R.drawable.擷取3);
        }
    }
}
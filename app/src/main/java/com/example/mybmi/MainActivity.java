package com.example.mybmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView show;
    private RadioGroup rbSex;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       fideViews();

       rbSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               if (checkedId == R.id.rbMale){
                   show.setText("我4男生");
               }
               else if(checkedId == R.id.rbFemale){
                   show.setText("我4女生");
               }
           }
       });
    }

    public void calcBMI(View view) {

       double bmi = getBmi();

       String result = getString(R.string.strShowBMI) + bmi;

       show.setText(result);

    }

    private double getBmi() {
       DecimalFormat df = new DecimalFormat("0.00");
       fideViews();
       double h = Double.parseDouble(height.getText().toString());
       double w = Double.parseDouble(weight.getText().toString());
       double bmi = w / (h/100.0  * h /100.0);
       bmi = Double.parseDouble(df.format(bmi));
       return bmi;
    }

    private void fideViews() {
       height = findViewById(R.id.etHeight);
       weight = findViewById(R.id.edWeight);
       show = findViewById(R.id.tvShow);
       rbSex = findViewById(R.id.rbSex);
       rbMale = findViewById(R.id.rbMale);
       rbFemale = findViewById(R.id.rbFemale);
    }

    public void GoNext(View view) {
       Intent intent = new Intent(this, ResultActivity.class);
       double bmi = getBmi();
       intent.putExtra("bmi", bmi);
       startActivity(intent);
    }
}
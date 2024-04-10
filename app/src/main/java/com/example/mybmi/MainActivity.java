package com.example.mybmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView show;
    private RadioGroup rbSex;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private CheckBox cbGrape;
    private CheckBox cbApple;
    private CheckBox cbPomelo;
    private String[] sex = {"男生", "女生"};
    private int sexSelected = 0;
    private String[] fruits = {"葡萄", "蘋果", "柚子"};
    private boolean[] fruitsSelected = {false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       fideViews();

        myLisiner();

    }

    private void myLisiner() {
        myListener();
    }

    private void myListener() {
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

        cbGrape.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getFruits();
            }
        });
        cbApple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 getFruits();
            }
        });
        cbPomelo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 getFruits();
            }
        });
    }

    private void getFruits() {
        String msg = "";
        if (cbGrape.isChecked()){
            msg += "葡萄";
        }
        if (cbApple.isChecked()){
            msg += "蘋果";
        }
        if (cbPomelo.isChecked()){
            msg += "柚子";
        }
        show.setText("我喜歡吃" + msg);
    }

    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("BMI");
        double bmi = getBmi();
        String result = getString(R.string.strShowBMI) + bmi;
        //顯示訊息
//      builder.setMessage(result);
        //顯示單選
        builder.setSingleChoiceItems(sex, sexSelected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sexSelected = which;
            }
        });
        //顯示多選
        builder.setMultiChoiceItems(fruits, fruitsSelected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                fruitsSelected[which] = isChecked;
            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, sex[sexSelected], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String msg = "";
                for (int i = 0; i < fruitsSelected.length; i++){
                    if (fruitsSelected[i]){
                        msg += fruits[i];
                    }
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void calcBMI(View view) {

       double bmi = getBmi();

       String result = getString(R.string.strShowBMI) + bmi;

//       show.setText(result);

       Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

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
       cbGrape = findViewById(R.id.cbGrape);
       cbApple = findViewById(R.id.cbApple);
       cbPomelo = findViewById(R.id.cbPomelo);
    }

    public void GoNext(View view) {
       Intent intent = new Intent(this, ResultActivity.class);
       double bmi = getBmi();
       intent.putExtra("bmi", bmi);
       startActivity(intent);
    }
}
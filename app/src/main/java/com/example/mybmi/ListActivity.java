package com.example.mybmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<String> list = new ArrayList<>();
        list.add("蘋果");
        list.add("葡萄");
        list.add("荔枝");
        list.add("奇異果");
        list.add("楊桃");
        list.add("木瓜");
        list.add("哈密瓜");
        list.add("榴槤");
        list.add("椰子");
        list.add("芭樂");
        list.add("蓮霧");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spfruit = findViewById(R.id.spFruit);
        TextView tvshow1 = findViewById(R.id.tvShow1);
        ListView lvfruit = findViewById(R.id.lvFruit);
        lvfruit.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        spfruit.setAdapter(adapter);
        lvfruit.setAdapter(adapter);


        spfruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvshow1.setText(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvfruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = "";
                for (int i = 0; i < list.size(); i++){
                    if (lvfruit.isItemChecked(i)){
                        tvshow1.setText(list.get(i));
                        s += list.get(i) + " ";
                    }
                }
                tvshow1.setText(s);

            }
        });




    }
}
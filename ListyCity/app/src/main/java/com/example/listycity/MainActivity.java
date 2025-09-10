package com.example.listycity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCity;
    Button deleteCity;
    Button confirm;
    EditText text;
    LinearLayout popout;
    int selectedCityPosition = -1; // Default position

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city);
        deleteCity = findViewById(R.id.delete_city);
        confirm = findViewById(R.id.confirm);
        text = findViewById(R.id.city_text);
        popout = findViewById(R.id.popout);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin",
                           "Vietnam", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // OnClickListeners for each button and for each item in the list
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCityPosition = position;
            }
        });

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popout.setVisibility(View.VISIBLE);
            }
        });

        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCityPosition != -1) {
                    dataList.remove(selectedCityPosition);
                    cityAdapter.notifyDataSetChanged();
                    selectedCityPosition = -1;
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = text.getText().toString().strip();
                if (!input.equals("")) {
                    dataList.add(input);
                    cityAdapter.notifyDataSetChanged();
                    text.setText("");
                    popout.setVisibility(View.GONE);
                }
            }
        });
    }

}
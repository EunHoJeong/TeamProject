package com.example.teamproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.teamproject.CustomAdapter;
import com.example.teamproject.R;

public class LocationActivity extends AppCompatActivity {
    private Spinner spinnerLocation;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        findViewByIdFunc();

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        CustomAdapter customAdapter = new CustomAdapter(getLayoutInflater());
        pager.setAdapter(customAdapter);

    }

    public void findViewByIdFunc() {
        spinnerLocation = findViewById(R.id.spinnerLocation);
        pager = findViewById(R.id.pager);

    }
}
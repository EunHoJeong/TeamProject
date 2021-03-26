package com.example.teamproject.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.teamproject.CustomAdapter;
import com.example.teamproject.R;

public class MotelActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        findViewByIdFunc();
        Log.d("a","a");


        CustomAdapter customAdapter = new CustomAdapter(getLayoutInflater());
        pager.setAdapter(customAdapter);

    }

    public void findViewByIdFunc() {
        pager = findViewById(R.id.pager);

    }
}
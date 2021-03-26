package com.example.teamproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
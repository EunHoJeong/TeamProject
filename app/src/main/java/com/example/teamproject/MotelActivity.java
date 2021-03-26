package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MotelActivity extends AppCompatActivity {
    ViewPager pager;
    //텍스트뷰 순서대로 아이디값
    private TextView tvLocation1,tvLocation2,tvLocation3,tvLocation4,tvLocation5,tvLocation6,tvLocation7,tvLocation8,tvLocation9,tvLocation10,tvLocation11,
            tvLocation12,tvLocation13,tvLocation14,tvLocation15,tvLocation16,tvLocation17,tvLocation18,tvLocation19,tvLocation20,tvLocation21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        findViewByIdFunc();

        CustomAdapter customAdapter = new CustomAdapter(getLayoutInflater());
        pager.setAdapter(customAdapter);

        tvLocation1.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);
            startActivity(intent);
        });

    }

    public void findViewByIdFunc() {
        pager = findViewById(R.id.pager);
        tvLocation1 = findViewById(R.id.tvLocation1);
        tvLocation2 = findViewById(R.id.tvLocation2);
        tvLocation3 = findViewById(R.id.tvLocation3);
        tvLocation4 = findViewById(R.id.tvLocation4);
        tvLocation5 = findViewById(R.id.tvLocation5);
        tvLocation6 = findViewById(R.id.tvLocation6);
        tvLocation7 = findViewById(R.id.tvLocation7);
        tvLocation8 = findViewById(R.id.tvLocation8);
        tvLocation9 = findViewById(R.id.tvLocation9);
        tvLocation10 = findViewById(R.id.tvLocation10);
        tvLocation11 = findViewById(R.id.tvLocation11);
        tvLocation12 = findViewById(R.id.tvLocation12);
        tvLocation13 = findViewById(R.id.tvLocation13);
        tvLocation14 = findViewById(R.id.tvLocation14);
        tvLocation15 = findViewById(R.id.tvLocation15);
        tvLocation16 = findViewById(R.id.tvLocation16);
        tvLocation17 = findViewById(R.id.tvLocation17);
        tvLocation18 = findViewById(R.id.tvLocation18);
        tvLocation19 = findViewById(R.id.tvLocation19);
        tvLocation20 = findViewById(R.id.tvLocation20);
        tvLocation21 = findViewById(R.id.tvLocation21);

    }
}
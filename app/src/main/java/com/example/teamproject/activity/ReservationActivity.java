package com.example.teamproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.teamproject.R;

public class ReservationActivity extends AppCompatActivity {
    private static final int HOTEL = 0;
    private static final int MOTEL = 1;
    private static final int PENSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);



    }
}
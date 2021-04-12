package com.example.teamproject.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.teamproject.R;

public class LodingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = new Intent(this, MainActivity.class);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.getData();
                try {
                    Thread.sleep(1700);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();






    }
}
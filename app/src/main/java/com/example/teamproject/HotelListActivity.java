package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HotelListActivity extends AppCompatActivity {
    private ImageButton pscHome, pscBack, pscSearch;
    private Button pscLocation, pscDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        findViewByIdFunc();

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {
        pscHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        pscLocation.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchMapActivity.class);
            startActivity(intent);
        });

        pscBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MotelActivity.class);
            startActivity(intent);
        });

        pscDate.setOnClickListener(view -> {
            Intent intent = new Intent(this, CheckinOutActivity.class);
            startActivity(intent);
        });
    }

    private void findViewByIdFunc() {
        pscHome     = findViewById(R.id.pscHome);
        pscSearch   = findViewById(R.id.pscSearch);
        pscLocation = findViewById(R.id.pscLocation);
        pscDate     = findViewById(R.id.pscDate);
        pscBack     = findViewById(R.id.pscBack);
    }
}

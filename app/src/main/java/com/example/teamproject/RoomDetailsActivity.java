package com.example.teamproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RoomDetailsActivity extends AppCompatActivity {

    private TextView pscRoomName, pscHotelName, pscCall;
    private ImageView pscViewPager;
    private ImageButton pscBack;
    private Button pscReservation, pscRoomSelection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        findViewByIdFunc();

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {
        pscBack.setOnClickListener(view -> {
            finish();
        });

        pscCall.setOnClickListener(view -> {
            Uri uri = Uri.parse("tel:");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });

        pscReservation.setOnClickListener(view -> {
            Intent intent = new Intent(this, CheckinOutActivity.class);
            startActivity(intent);
        });

    }

    private void findViewByIdFunc() {
        pscBack = findViewById(R.id.pscBack);
        pscRoomName = findViewById(R.id.pscRoomName);
        pscHotelName = findViewById(R.id.pscHotelName);
        pscCall = findViewById(R.id.pscCall);
        pscViewPager = findViewById(R.id.pscViewPager);
        pscReservation = findViewById(R.id.pscReservation);
        pscRoomSelection = findViewById(R.id.pscRoomSelection);
    }
}

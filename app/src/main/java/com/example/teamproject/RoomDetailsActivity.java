package com.example.teamproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomDetailsActivity extends AppCompatActivity {

    private TextView pscRoomName, pscHotelName, pscCall;
    private ImageButton pscBack;
    private Button pscReservation, pscRoomSelection;

    private DatabaseReference dbRf;

    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name = getIntent().getStringExtra("name");

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
        pscReservation = findViewById(R.id.pscReservation);
        pscRoomSelection = findViewById(R.id.pscRoomSelection);
    }
}

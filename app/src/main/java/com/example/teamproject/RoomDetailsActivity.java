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
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomDetailsActivity extends AppCompatActivity {

    private TextView pscRoomName, pscHotelName, pscCall, pscLargeRoomPrice, pscLodgmentPrice;
    private ImageButton pscBack;
    private Button pscReservation, pscRoomSelection;
    private ImageView pscViewPager;

    private DatabaseReference dbRf;

    private StoreInfo storeInfo;
    private StorePrice storePrice;
    private StoreTime storeTime;

    private String name;
    private String img;
    private String hotel;
    private String largeRoom;
    private String lodgment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewByIdFunc();

        eventHandlerFunc();

        storeInfo = MainActivity.getStoreInfo(name);
        storePrice = MainActivity.getStorePrice();

        img = getIntent().getStringExtra("img");

        name = getIntent().getStringExtra("name");

        hotel = getIntent().getStringExtra("hotel");

        largeRoom = getIntent().getStringExtra("largeRoom");

        lodgment = getIntent().getStringExtra("lodgment");

        Glide.with(getApplicationContext())
                .load(img)
                .into(pscViewPager);

        pscRoomName.setText(name);
        pscHotelName.setText(hotel);
        pscLargeRoomPrice.setText(largeRoom);
        pscLodgmentPrice.setText(lodgment);

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
        pscViewPager = findViewById(R.id.pscViewPager);
        pscLargeRoomPrice = findViewById(R.id.pscLargeRoomPrice);
        pscLodgmentPrice = findViewById(R.id.pscLodgmentPrice);
    }
}

package com.example.teamproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HotelGuestActivity extends AppCompatActivity {
    private ImageButton pscBack;
    private Button pscLocation, pscCall, pscReservation, pscAgain;
    private DatabaseReference dbRf;
    private ArrayList<StoreInfo> info = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_guest);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();

        StoreInfo storeInfo = (StoreInfo)intent.getSerializableExtra("storeInfo");

        Toast.makeText(this, storeInfo.getMainImage(), Toast.LENGTH_SHORT).show();

        if (info.size() == 0){
            getMotelData();
        }
        
        findViewByIdFunc();

        eventHandlerFunc();

//        hotelAdapter = new HotelAdapter(getApplicationContext(), info);
//        recyclerLocation.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerLocation.setAdapter(hotelAdapter);
//
//        hotelAdapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(getApplicationContext(), HotelGuestActivity.class);
//                String name = info.get(position).getStoreName();
//                intent.putExtra("name", name);
//                startActivity(intent);
//            }
//        });
    }

    private void getMotelData() {
        dbRf = FirebaseDatabase.getInstance().getReference("storeInfo");

        dbRf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    StoreInfo storeInfo = s.getValue(StoreInfo.class);
                    info.add(storeInfo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SystemClock.sleep(1000);
    }

    private void eventHandlerFunc() {
        pscLocation.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

        pscCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        pscReservation.setOnClickListener(view -> {
            Intent intent = new Intent(this, CheckinOutActivity.class);
            startActivity(intent);
        });

        pscBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, HotelListActivity.class);
        });
    }

    private void findViewByIdFunc() {
        pscLocation = findViewById(R.id.pscLocation);
        pscCall = findViewById(R.id.pscCall);
        pscBack = findViewById(R.id.pscBack);
        pscReservation = findViewById(R.id.pscReservation);
    }
}

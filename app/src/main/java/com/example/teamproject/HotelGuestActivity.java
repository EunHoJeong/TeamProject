package com.example.teamproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
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
    private RecyclerView recyclerLocation;
    private HotelAdapter hotelAdapter;
    private ImageButton pscLike;
    private Button pscLocation, pscCall, pscIn, pscOut, pscAgain;
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
        pscLike.setOnClickListener(view -> {
            Intent intent = new Intent(this, BestActivity.class);
            startActivity(intent);
        });

        pscLocation.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

        pscCall.setOnClickListener(view -> {
            Uri uri = Uri.parse("");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });

    }

    private void findViewByIdFunc() {
        pscLike = findViewById(R.id.pscLike);
        pscLocation = findViewById(R.id.pscLocation);
        pscCall = findViewById(R.id.pscCall);
    }
}

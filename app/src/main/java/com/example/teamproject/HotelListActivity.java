package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ImageButton;

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

import java.util.ArrayList;

public class HotelListActivity extends AppCompatActivity {
    private RecyclerView recyclerLocation;
    private HotelAdapter hotelAdapter;
    private ImageButton pscHome, pscBack, pscSearch;
    private Button pscLocation, pscDate;
    private DatabaseReference dbRf;
    private ArrayList<StoreInfo> info = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        if(info.size()==0){
            getMotelData();
        }

        findViewByIdFunc();

        hotelAdapter = new HotelAdapter(getApplicationContext(), info);
        recyclerLocation.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerLocation.setAdapter(hotelAdapter);

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
        recyclerLocation     = findViewById(R.id.recyclerLocation);
    }
}

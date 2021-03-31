package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
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

import java.util.ArrayList;

public class HotelListActivity extends AppCompatActivity implements HotelAdapter.OnItemClickListener {
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

        info = (ArrayList<StoreInfo>) getIntent().getSerializableExtra("list");


        findViewByIdFunc();

        hotelAdapter = new HotelAdapter(getApplicationContext(), info);
        recyclerLocation.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerLocation.setAdapter(hotelAdapter);

        eventHandlerFunc();

        hotelAdapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), HotelGuestActivity.class);
                String name = info.get(position).getStoreName();
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
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
            finish();
        });

        pscDate.setOnClickListener(view -> {
            Intent intent = new Intent(this, CheckinOutActivity.class);
            startActivity(intent);
        });
    }

    private void findViewByIdFunc() {
        pscHome          = findViewById(R.id.pscHome);
        pscSearch        = findViewById(R.id.pscSearch);
        pscLocation      = findViewById(R.id.pscLocation);
        pscDate          = findViewById(R.id.pscDate);
        pscBack          = findViewById(R.id.pscBack);
        recyclerLocation = findViewById(R.id.recyclerLocation);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}

package com.example.teamproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HotelGuestActivity extends AppCompatActivity {
    private ImageButton pscBack, pscLikeList, psctSearch;
    private ImageView pscViewPager, pscStar, pscImg1, pscImg2, pscImg3;
    private TextView pscRoomSelection, pscRankName, pscGrade, pscMaxGrade, pscReview,
            pscLargeRoom1, pscLargeRoom2, pscLargeRoom3, pscLodgment1, pscLodgment2, pscLodgment3;
    private Button pscLocation, pscCall, pscReservation;
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
        pscBack = findViewById(R.id.pscBack);
        pscLikeList = findViewById(R.id.pscLikeList);
        psctSearch = findViewById(R.id.psctSearch);
        pscViewPager = findViewById(R.id.pscViewPager);
        pscStar = findViewById(R.id.pscStar);
        pscImg1 = findViewById(R.id.pscImg1);
        pscImg2 = findViewById(R.id.pscImg2);
        pscImg3 = findViewById(R.id.pscImg3);
        pscLocation = findViewById(R.id.pscLocation);
        pscCall = findViewById(R.id.pscCall);
        pscReservation = findViewById(R.id.pscReservation);
        pscRoomSelection = findViewById(R.id.pscRoomSelection);
        pscRankName = findViewById(R.id.pscRankName);
        pscGrade = findViewById(R.id.pscGrade);
        pscReview = findViewById(R.id.pscReview);
        pscMaxGrade = findViewById(R.id.pscMaxGrade);
        pscLargeRoom1 = findViewById(R.id.pscLargeRoom1);
        pscLargeRoom2 = findViewById(R.id.pscLargeRoom2);
        pscLargeRoom3 = findViewById(R.id.pscLargeRoom3);
        pscLodgment1 = findViewById(R.id.pscLodgment1);
        pscLodgment2 = findViewById(R.id.pscLodgment2);
        pscLodgment3 = findViewById(R.id.pscLodgment3);

    }
}

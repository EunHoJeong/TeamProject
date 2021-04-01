package com.example.teamproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
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

import com.bumptech.glide.Glide;
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
            pscLargeRoom1, pscLargeRoom2, pscLargeRoom3, pscLodgment1, pscLodgment2, pscLodgment3,
            tvStTime1, tvSpTime1, tvSwTime1, tvStTime2, tvSpTime2, tvSwTime2;
    private Button pscLocation, pscCall, pscReservation;
    private DatabaseReference dbRf;

    private StoreInfo storeInfo;
    private StoreImage storeImage;
    private StorePrice storePrice;
    private StoreTime storeTime;

    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_guest);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name = getIntent().getStringExtra("name");


        findViewByIdFunc();

        eventHandlerFunc();

        storeInfo = MainActivity.getStoreInfo(name);
        storeImage = MainActivity.getStoreImage();
        storePrice = MainActivity.getStorePrice();
        storeTime = MainActivity.getStoreTime();

        setInfomation();

        SystemClock.sleep(500);

    }

    private void setInfomation() {

        Glide.with(getApplicationContext())
                .load(storeInfo.getMainImage())
                .into(pscViewPager);

        Glide.with(getApplicationContext())
                .load(storeInfo.getMainImage())
                .into(pscImg1);

        Glide.with(getApplicationContext())
                .load(storeImage.getSp1())
                .into(pscImg2);

        Glide.with(getApplicationContext())
                .load(storeImage.getSw1())
                .into(pscImg3);

        pscRankName.setText(storeInfo.getStoreName());
        pscGrade.setText(String.valueOf(storeInfo.getGrade()));
        pscReview.setText("후기 "+storeInfo.getReview()+"개");
        tvStTime1.setText("대실 최대 "+storeInfo.getSt_Time1()+"시간");
        pscLargeRoom1.setText(storeInfo.getSt_Large());
        tvStTime2.setText("숙박 최대 "+storeInfo.getSt_Time2()+"부터");
        pscLodgment1.setText(storeInfo.getSt_Lodgment());

        tvSpTime1.setText("대실 최대 "+storeTime.getSp_Time1()+"시간");
        pscLargeRoom2.setText(storePrice.getSp_Large());
        tvSpTime2.setText("숙박 최대 "+storeTime.getSp_Time2()+"부터");
        pscLodgment2.setText(storePrice.getSp_Lodgment());

        tvSwTime1.setText("대실 최대 "+storeTime.getSw_Time1()+"시간");
        pscLargeRoom3.setText(storePrice.getSw_Large());
        tvSwTime2.setText("숙박 최대 "+storeTime.getSw_Time2()+"부터");
        pscLodgment3.setText(storePrice.getSw_Lodgment());



    }

    private void getMotelData() {
        dbRf = FirebaseDatabase.getInstance().getReference("storeInfo");

        dbRf.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                storeInfo = snapshot.getValue(StoreInfo.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Test", error.toString());
            }
        });

        SystemClock.sleep(2000);


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
        tvStTime1 = findViewById(R.id.tvStTime1);
        tvSpTime1 = findViewById(R.id.tvSpTime1);
        tvSwTime1 = findViewById(R.id.tvSwTime1);
        tvStTime2 = findViewById(R.id.tvStTime2);
        tvSpTime2 = findViewById(R.id.tvSpTime2);
        tvSwTime2 = findViewById(R.id.tvSwTime2);

    }
}

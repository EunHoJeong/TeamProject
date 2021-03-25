package com.example.teamproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.teamproject.R;
import com.example.teamproject.adapter.ReservationAdapter;
import com.example.teamproject.data.ReservationConfirmData;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {
    private static final int HOTEL = 0;
    private static final int MOTEL = 1;
    private static final int PENSION = 2;

    private RecyclerView recyclerReservation;
    private ReservationAdapter adapter;

    private ArrayList<ReservationConfirmData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        for(int i = 0; i < 10; i++){
            String state = "퇴실완료"+i;
            String storeName = "숙소이름"+i;
            String roomInfo = "스탠다드룸"+i;
            String date = "0000/00/00(월) 00:00 ~ 0000/00/00(화) 00:00"+i;
            list.add(new ReservationConfirmData(state, storeName, roomInfo, date));
        }

        adapter = new ReservationAdapter(getApplicationContext(), list);
        recyclerReservation = findViewById(R.id.recyclerReservation);
        recyclerReservation.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerReservation.setAdapter(adapter);



    }
}
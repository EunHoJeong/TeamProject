package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {

    private RecyclerView recyclerReservation;
    private ReservationAdapter adapter;

    private ArrayList<Reservation> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        String id = getIntent().getStringExtra("id");

        if(list.size() == 0){
            Reservation reservation = new Reservation();
            list = reservation.getData(id);
        }

        adapter = new ReservationAdapter(getApplicationContext(), list);
        recyclerReservation = findViewById(R.id.recyclerReservation);
        recyclerReservation.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerReservation.setAdapter(adapter);



    }
}
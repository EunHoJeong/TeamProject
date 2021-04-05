package com.example.teamproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity {

    private RecyclerView recyclerReservation;
    private ReservationAdapter adapter;

    private static ArrayList<Reservation> list = new ArrayList<>();
    private ArrayList<String> keyList = new ArrayList<>();

    private String id;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        id = getIntent().getStringExtra("id");

        if(keyList.size() == 0){
            Reservation reservation = new Reservation();
            MainActivity.sortRvData();
            list = MainActivity.getRvData();
            keyList = reservation.getKey(id);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String time = sdf.format(date.getTime());

        Log.d("Test", list.size()+"첫번째");

        adapter = new ReservationAdapter(getApplicationContext(), list, time);
        recyclerReservation = findViewById(R.id.recyclerReservation);
        recyclerReservation.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerReservation.setAdapter(adapter);

        adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setMessage("예약을 취소 하시겠습니까?");
                dlg.setPositiveButton("아니요", null);
                dlg.setNeutralButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteCeoDB(position);
                        deleteUserDB(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ReservationActivity.this, "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();

            }
        });

    }




    private void deleteCeoDB(int position) {
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference();

        String check_In = list.get(position).getDate().substring(0, 10);
        String storeName = list.get(position).getStoreName();


        Log.d("Test", "CeoReservation/"+check_In+"/"+storeName+"/"+keyList.get(position));

        dbRf.child("CeoReservation").child(check_In).child(storeName).child(keyList.get(position)).removeValue();
    }

    private void deleteUserDB(int position){

        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference();
        MainActivity.deleteRvData(position);

        dbRf.child("Reservation").child(id).child(keyList.get(position)).removeValue();

    }




}
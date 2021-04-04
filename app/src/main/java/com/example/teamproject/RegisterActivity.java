package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private static ArrayList<CeoReservation> list = new ArrayList<>();
    private CeoListAdapter adapter;
    private ListView lvCeo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        TextView tvCeoDate = findViewById(R.id.tvCeoDate);
        lvCeo = findViewById(R.id.lvCeo);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (EE)");

        tvCeoDate.setText(sdf.format(date));






        adapter = new CeoListAdapter(list);
        lvCeo.setAdapter(adapter);


    }


    public static void setList(ArrayList<CeoReservation> crvList){
        list = crvList;
    }

    public static void insertList(CeoReservation crv){
        list.add(crv);
    }

    public static void deleteList(int position){
        list.remove(position);
    }

}
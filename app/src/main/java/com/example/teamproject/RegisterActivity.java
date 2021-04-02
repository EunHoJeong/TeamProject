package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private ArrayList<CeoReservationData> list = new ArrayList<>();
    private CeoListAdapter adapter;
    private ListView lvCeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView tvCeoDate = findViewById(R.id.tvCeoDate);
        lvCeo = findViewById(R.id.lvCeo);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd (EE)");

        tvCeoDate.setText(sdf.format(date));


        for(int i = 0; i < 10; i++){
            list.add(new CeoReservationData("gh888", "스탠다드", "숙박"));
        }

        adapter = new CeoListAdapter(list);
        lvCeo.setAdapter(adapter);






    }
}
package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MotelActivity extends AppCompatActivity {
    //텍스트뷰 순서대로 아이디값
    private TextView tvLocation1,tvLocation2,tvLocation3,tvLocation4,tvLocation5,tvLocation6,tvLocation7,tvLocation8,tvLocation9,tvLocation10,tvLocation11,
            tvLocation12,tvLocation13,tvLocation14,tvLocation15,tvLocation16,tvLocation17,tvLocation18,tvLocation19,tvLocation20,tvLocation21;

    private ImageButton pscBack;

    private static ArrayList<StoreInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewByIdFunc();

        tvLocation1.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"강남","역삼","삼성","논현"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation2.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"서초","신사","방배"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation3.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"잠실","신천(잠실새내)"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation4.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"영등포","여의도"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation5.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"신림","서울대","사당","동작"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation6.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"천호","길동","둔촌"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation7.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"화곡","까치산","양천","목동"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation8.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);
            String[] tag = new String[]{"구로","금천","오류","신도림"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation9.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"신촌","홍대","합정"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation10.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"연신내","불광","응암"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation11.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"종로","대학로","동묘앞역"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation12.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"성신여대","성북","월곡"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation13.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"이태원","용산","서울역","명동","회현"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation14.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"동대문","을지로","충무로","신당"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation15.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"회기","고려대","청량리","신설동"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation16.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"장안동","답십리"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation17.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"건대","군자","구의"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation18.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"왕십리","성수","금호"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation19.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"수유","미아"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation20.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"상봉","중랑","면목"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        tvLocation21.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);

            String[] tag = new String[]{"태릉","노원","도봉","창동"};
            list = FragHome.getList(tag);
            intent.putExtra("list", list);

            startActivity(intent);
        });

        pscBack.setOnClickListener(v -> {
            finish();
        });
    }

    public void findViewByIdFunc() {
        tvLocation1 = findViewById(R.id.tvLocation1);
        tvLocation2 = findViewById(R.id.tvLocation2);
        tvLocation3 = findViewById(R.id.tvLocation3);
        tvLocation4 = findViewById(R.id.tvLocation4);
        tvLocation5 = findViewById(R.id.tvLocation5);
        tvLocation6 = findViewById(R.id.tvLocation6);
        tvLocation7 = findViewById(R.id.tvLocation7);
        tvLocation8 = findViewById(R.id.tvLocation8);
        tvLocation9 = findViewById(R.id.tvLocation9);
        tvLocation10 = findViewById(R.id.tvLocation10);
        tvLocation11 = findViewById(R.id.tvLocation11);
        tvLocation12 = findViewById(R.id.tvLocation12);
        tvLocation13 = findViewById(R.id.tvLocation13);
        tvLocation14 = findViewById(R.id.tvLocation14);
        tvLocation15 = findViewById(R.id.tvLocation15);
        tvLocation16 = findViewById(R.id.tvLocation16);
        tvLocation17 = findViewById(R.id.tvLocation17);
        tvLocation18 = findViewById(R.id.tvLocation18);
        tvLocation19 = findViewById(R.id.tvLocation19);
        tvLocation20 = findViewById(R.id.tvLocation20);
        tvLocation21 = findViewById(R.id.tvLocation21);
        pscBack = findViewById(R.id.pscBack);
    }
}
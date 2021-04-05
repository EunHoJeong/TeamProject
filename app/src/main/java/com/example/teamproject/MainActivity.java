package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int HOME = 0;
    private static final int MY_AROUND = 1;
    private static final int STEAMED = 2;
    private static final int MY_MENU = 3;

    private BottomNavigationView bottomNavigationView;
    private FragHome f_Home;
    private FragMyMenu f_MyMenu;
    private FragSteamed f_Steamed;

    private static ArrayList<StoreInfo> info = new ArrayList<>();
    private static ArrayList<StoreImage> image = new ArrayList<>();
    private static ArrayList<StorePrice> price = new ArrayList<>();
    private static ArrayList<StoreTime> time = new ArrayList<>();
    private static ArrayList<CeoReservation> crvList = new ArrayList<>();
    private static ArrayList<Reservation> rvList = new ArrayList<>();


    private static int position = 0;

    private long backButtonTime = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        findViewByIdFunc();

        eventHandler();


        f_Home = new FragHome();
        f_MyMenu = new FragMyMenu();
        f_Steamed = new FragSteamed();

        setFrag(HOME);
    }



    public static void getData() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRf = db.getReference("storeInfo");

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

        Thread thread = new Thread(()->{
            FirebaseDatabase db2 = FirebaseDatabase.getInstance();
            DatabaseReference dbRf2 = db.getReference("storeImage");

            dbRf2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot s : snapshot.getChildren()){
                        StoreImage storeImage = s.getValue(StoreImage.class);
                        image.add(storeImage);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        thread.start();

        Thread thread2 = new Thread(()->{
            FirebaseDatabase db3 = FirebaseDatabase.getInstance();
            DatabaseReference dbRf3 = db.getReference("storePrice");

            dbRf3.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot s : snapshot.getChildren()){
                        StorePrice storePrice = s.getValue(StorePrice.class);
                        price.add(storePrice);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        thread2.start();

        Thread thread3 = new Thread(()->{
            FirebaseDatabase db4 = FirebaseDatabase.getInstance();
            DatabaseReference dbRf4 = db.getReference("storeTime");

            dbRf4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot s : snapshot.getChildren()){
                        StoreTime storeTime = s.getValue(StoreTime.class);
                        time.add(storeTime);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        thread3.start();







    }

    private void eventHandler() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_home: setFrag(HOME);
                        break;
                    case R.id.action_my_around:
                        Intent intent=new Intent(getApplicationContext(),SearchMapActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_steamed: setFrag(STEAMED);
                        break;
                    case R.id.action_my_menu: setFrag(MY_MENU);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }//eventHandler end

    private void findViewByIdFunc() {
        bottomNavigationView = findViewById(R.id.bottomNavi);

    }// findViewById end

    public void setFrag(int select) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (select){
            case HOME:
                ft.replace(R.id.frameMain, f_Home);
                ft.commit();
                break;

            case STEAMED:
                ft.replace(R.id.frameMain, f_Steamed);
                ft.commit();
                break;
            case MY_MENU:
                ft.replace(R.id.frameMain, f_MyMenu);
                ft.commit();
                break;
            default:
                break;
        }
    }// setFrag end


    public static StoreInfo getStoreInfo(String name){
        StoreInfo storeInfo = null;
        for(int i = 0; i < info.size(); i++){
            if(info.get(i).getStoreName().equals(name)){
                storeInfo = info.get(i);
                position = i;
                break;
            }
        }
        return storeInfo;
    }

    public static StoreImage getStoreImage(){
        StoreImage storeImage = image.get(position);

        return storeImage;
    }

    public static StorePrice getStorePrice(){
        StorePrice storePrice = price.get(position);

        return storePrice;
    }

    public static StoreTime getStoreTime(){
        StoreTime storeTime = time.get(position);

        return storeTime;
    }






    public static void changeList(StoreInfo newStoreInfo) {
        info.remove(position);
        info.add(position, newStoreInfo);
    }

    public static int getIndex() {
        return position;
    }


    public static void getCRVData(String storeName) {
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("CeoReservation");
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        CeoReservation crv = null;


        dbRf.child(sdf.format(date.getTime())).child(storeName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    CeoReservation crv = s.getValue(CeoReservation.class);
                    crvList.add(crv);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        RegisterActivity.setList(crvList);

    }

    public static void setRvData(String id){
        rvList.clear();
        Reservation rv = new Reservation();
        rvList = rv.getData(id);
    }

    public static void sortRvData(){
        Collections.sort(rvList, new Comparator<Reservation>() {
            @Override
            public int compare(Reservation a, Reservation b) {
                return b.getDate().compareTo(a.getDate());
            }
        });
    }

    public static ArrayList<Reservation> getRvData(){
        return rvList;
    }

    public static void insertRvData(Reservation rv){
        rvList.add(rv);
    }

    public static void deleteRvData(int position){
        rvList.remove(position);
    }

    public static ArrayList<StoreInfo> getStoreInfoData() {
        return info;
    }


    @Override
    public void onBackPressed() {

        long currentTimeMillis = System.currentTimeMillis(); //1초를 1000/1초 정수값을 표현
        long getTime = currentTimeMillis - backButtonTime;


        if(getTime >= 0 && getTime <= 2000) {
            super.onBackPressed();
        }else{
            backButtonTime = currentTimeMillis;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }
}
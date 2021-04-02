package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int HOME = 0;
    private static final int MY_AROUND = 1;
    private static final int STEAMED = 2;
    private static final int MY_MENU = 3;

    private BottomNavigationView bottomNavigationView;
    private FragHome f_Home;
    private FragMyAround f_MyAround;
    private FragMyMenu f_MyMenu;
    private FragSteamed f_Steamed;

    private static ArrayList<StoreInfo> info = new ArrayList<>();
    private static ArrayList<StoreImage> image = new ArrayList<>();
    private static ArrayList<StorePrice> price = new ArrayList<>();
    private static ArrayList<StoreTime> time = new ArrayList<>();
    private static int position = 0;

    private long backButtonTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        insertDB();

        findViewByIdFunc();

        eventHandler();

        getData();

        f_Home = new FragHome();
        f_MyAround = new FragMyAround();
        f_MyMenu = new FragMyMenu();
        f_Steamed = new FragSteamed();

        setFrag(HOME);
    }

    private void getData() {
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

    }

    private void eventHandler() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_home: setFrag(HOME);
                        break;
                    case R.id.action_my_around: setFrag(MY_AROUND);
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
            case MY_AROUND:
                ft.replace(R.id.frameMain, f_MyAround);
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

    public void insertDB(){
        DatabaseReference dbRf = null;
        HashMap<String, Object> childUpdates= null;
        Map<String, Object> infoValue = null;
        Map<String, Object> imageValue = null;
        Map<String, Object> priceValue = null;
        Map<String, Object> timeValue = null;
        StoreInfo info = null;
        StoreImage image = null;
        StorePrice price = null;
        StoreTime time = null;

        dbRf = FirebaseDatabase.getInstance().getReference();
        childUpdates = new HashMap<>();

        //storeInfo 삽입
        String storeName = "화곡 여우잠";
        String location = "강서구 강서로5가길 14";
        String phone = "050-35052-4201";
        String location_tag = "화곡";
        String mainImage = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fsp1.jpg?alt=media&token=44211a72-5e4b-4678-ac65-88c8b504fc31";
        String st_Large = "20,000원";
        String st_Lodgment = "40,000원";
        String st_Time1 = "4";
        String st_Time2 = "18:00";

        info = new StoreInfo(storeName, location, phone, 0, 0, 0, location_tag, mainImage, st_Large, st_Lodgment, st_Time1, st_Time2);

        //storeImage 삽입
        String st1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fsp1.jpg?alt=media&token=44211a72-5e4b-4678-ac65-88c8b504fc31";
        String st2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fsp2.jpg?alt=media&token=1cea0e58-6756-4569-bb75-bf75069c207d";
        String st3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fsp3.jpg?alt=media&token=f827274f-6a87-413a-8e5e-423565ece476";
        String sp1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fst1.jpg?alt=media&token=201ca6cd-796a-484a-8227-dfad90b9fb52";
        String sp2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fst2.jpg?alt=media&token=d7d2c066-df7d-44ef-b6f5-4e8b86e93a17";
        String sp3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fst3.jpg?alt=media&token=6d5d20a8-b2ae-42b5-981f-f3ab4c2f4dda";
        String sw2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fsw1.jpg?alt=media&token=3abaf330-f5e4-4af6-a251-ec21b343bed6";
        String sw1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fsw2.jpg?alt=media&token=59da2e97-1134-46a5-b495-f4ccb3de3fef";
        String sw3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%ED%99%94%EA%B3%A1%20%EC%97%AC%EC%9A%B0%EC%9E%A0%2Fsw3.jpg?alt=media&token=e5b15cc4-c7d7-4977-bc6d-8e7e033947f3";







        image = new StoreImage(storeName, st1, st2, st3, sp1, sp2, sp3, sw1, sw2, sw3);

        //storePrice 삽입
        String sp_Large = "25,000원";
        String sp_Lodgment = "50,000원";
        String sw_Large = "30,0000원";
        String sw_Lodgment = "60,000원";

        price = new StorePrice(storeName, sp_Large, sp_Lodgment, sw_Large, sw_Lodgment);

        //storeTime 삽입

        String sp_Time1 = "4";
        String sp_Time2 = "18:00";
        String sw_Time1 = "4";
        String sw_Time2 = "18:00";
        time = new StoreTime(storeName, sp_Time1, sp_Time2, sw_Time1, sw_Time2);

        infoValue = info.toMap();
        imageValue = image.toMap();
        priceValue = price.toMap();
        timeValue = time.toMap();

        childUpdates.put("storeInfo/"+ storeName, infoValue);
        childUpdates.put("storeImage/"+ storeName, imageValue);
        childUpdates.put("storePrice/"+ storeName, priceValue);
        childUpdates.put("storeTime/"+ storeName, timeValue);

        dbRf.updateChildren(childUpdates);

        Toast.makeText(this, "DB삽입 성공", Toast.LENGTH_SHORT).show();

    }

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
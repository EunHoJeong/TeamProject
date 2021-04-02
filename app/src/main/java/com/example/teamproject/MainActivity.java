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
        String storeName = "서울대입구 폭스";
        String location = "관악구 관악로 208-4";
        String phone = "050-35050-0009";
        String location_tag = "서울대";
        String mainImage = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fsp1.jpg?alt=media&token=44f4938d-4b50-4d19-820c-7ff90ee637b4";
        String st_Large = "20,000원";
        String st_Lodgment = "40,000원";
        String st_Time1 = "4";
        String st_Time2 = "18:00";

        info = new StoreInfo(storeName, location, phone, 0, 0, 0, location_tag, mainImage, st_Large, st_Lodgment, st_Time1, st_Time2);

        //storeImage 삽입
        String st1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fsp1.jpg?alt=media&token=44f4938d-4b50-4d19-820c-7ff90ee637b4";
        String st2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fsp2.jpg?alt=media&token=1b40006d-0d6f-430e-bce1-80d69e7aa73a";
        String st3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fsp3.jpg?alt=media&token=ebd9646d-6f3d-43c5-b46b-9c3376d99628";
        String sp1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fst1.jpg?alt=media&token=937e9f21-6bd0-4953-beff-16f8f7151def";
        String sp2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fst2.jpg?alt=media&token=b80387e0-d5a5-484b-9748-296eda8b21f4";
        String sp3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fst3.jpg?alt=media&token=548030aa-c923-4f9e-aa46-f04ba179d6cf";
        String sw2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fsw1.jpg?alt=media&token=ba23e868-6072-428f-9070-8ed3a39e28a5";
        String sw1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fsw2.jpg?alt=media&token=78871da1-05dd-4484-a1f7-8549f5e790f3";
        String sw3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%20%ED%8F%AD%EC%8A%A4%2Fsw3.jpg?alt=media&token=30afc942-2e4f-4033-bec4-28f4e9704b4e";




        image = new StoreImage(storeName, st1, st2, st3, sp1, sp2, sp3, sw1, sw2, sw3);

        //storePrice 삽입
        String sp_Large = "25,000원";
        String sp_Lodgment = "45,000원";
        String sw_Large = "25,0000원";
        String sw_Lodgment = "50,000원";

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
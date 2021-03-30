package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        insertDB();

        findViewByIdFunc();

        eventHandler();




        f_Home = new FragHome();
        f_MyAround = new FragMyAround();
        f_MyMenu = new FragMyMenu();
        f_Steamed = new FragSteamed();



        setFrag(HOME);
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
        String storeName = "외대 Life hotel Raha";
        String location = "동대문구 이문로 174-1";
        String phone = "0503-5051-7307";
        String location_tag = "회기";
        String mainImage = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F1.jpg?alt=media&token=1e685689-c2af-47cd-8f9c-df45b5b11c64";
        String st_Large = "25,000원";
        String st_Lodgment = "50,000원";
        String st_Time1 = "4";
        String st_Time2 = "18:00";

        info = new StoreInfo(storeName, location, phone, 0, 0, 0, location_tag, mainImage, st_Large, st_Lodgment, st_Time1, st_Time2);

        //storeImage 삽입
        String st1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F1.jpg?alt=media&token=1e685689-c2af-47cd-8f9c-df45b5b11c64";
        String st2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F2.jpg?alt=media&token=1c7ea6de-1f4e-495b-8865-3df18b0010f3";
        String st3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F3.jpg?alt=media&token=2d131e68-fdff-4f40-9187-1b7e7006f2bc";
        String sp1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F11.jpg?alt=media&token=3409b2cc-6545-425f-92bf-ac2420a1be2c";
        String sp2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F12.jpg?alt=media&token=04306942-3b4e-4387-9ba6-6b1608d2546b";
        String sp3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F13.jpg?alt=media&token=2f96dfc0-b6d1-481a-826a-a4e06cdc6f01";
        String sw1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F21.jpg?alt=media&token=6f06d660-58da-413d-9c4c-8d3607a87a32";
        String sw2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F22.jpg?alt=media&token=d41e7c57-6cf6-4dbc-8930-ebad10f9fc09";
        String sw3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%99%B8%EB%8C%80%20Life%20hotel%20Raha%2F23.jpg?alt=media&token=b875b73f-53f7-49ac-869d-8a3abb5831c2";



        image = new StoreImage(storeName, st1, st2, st3, sp1, sp2, sp3, sw1, sw2, sw3);

        //storePrice 삽입
        String sp_Large = "35,000원";
        String sp_Lodgment = "60,000원";
        String sw_Large = "45,000원";
        String sw_Lodgment = "85,000원";

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
}
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
        //insertDB();

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
        String storeName = "길동 샘";
        String location = "강동구 천중로 197";
        String phone = "050-35050-9699";
        String location_tag = "길동";
        String mainImage = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fst1.jpg?alt=media&token=eef9c9a3-d104-4347-b5f8-6c4cedf7ccc1";
        String st_Large = "20,000원";
        String st_Lodgment = "35,000원";
        String st_Time1 = "4";
        String st_Time2 = "18:00";

        info = new StoreInfo(storeName, location, phone, 0, 0, 0, location_tag, mainImage, st_Large, st_Lodgment, st_Time1, st_Time2);

        //storeImage 삽입
        String st1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fst1.jpg?alt=media&token=eef9c9a3-d104-4347-b5f8-6c4cedf7ccc1";
        String st2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fst2.jpg?alt=media&token=651722ac-1fb6-42bd-9495-d8d4639c75d6";
        String st3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fst3.jpg?alt=media&token=3ba3f245-88eb-4721-b362-0aa15c017eda";
        String sp1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fsp1.jpg?alt=media&token=a234fb6b-8c27-4c69-b1d4-9b4b12e6a871";
        String sp2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fsp2.jpg?alt=media&token=33db71bc-4ca6-4017-8a69-59c5c826da93";
        String sp3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fsp3.jpg?alt=media&token=b40b34f3-220d-4b74-a6d1-368756615734";
        String sw1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fsw1.jpg?alt=media&token=9bb93273-1c6c-4fa1-a979-d03db32285b1";
        String sw2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fsw2.jpg?alt=media&token=5b0e1837-4779-4cca-93d9-4f0b1b246111";
        String sw3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EA%B8%B8%EB%8F%99%20%EC%83%98%2Fsw3.jpg?alt=media&token=71c2963f-bfff-484a-a761-88e9af25884a";

        image = new StoreImage(storeName, st1, st2, st3, sp1, sp2, sp3, sw1, sw2, sw3);

        //storePrice 삽입
        String sp_Large = "20,000원";
        String sp_Lodgment = "40,000원";
        String sw_Large = "25,000원";
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
}
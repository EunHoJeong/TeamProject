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
        String storeName = "이코노미 호텔";
        String location = "은평구 응암로 177";
        String phone = "02-373-2603";
        String location_tag = "연신내";
        String mainImage = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C1.jpg?alt=media&token=c001a7f2-1e97-46fb-9237-771db6f9446e";
        String st_Large = "20,000원";
        String st_Lodgment = "40,000원";
        String st_Time1 = "4";
        String st_Time2 = "18:00";

        info = new StoreInfo(storeName, location, phone, 0, 0, 0, location_tag, mainImage, st_Large, st_Lodgment, st_Time1, st_Time2);

        //storeImage 삽입
        String st1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C1.jpg?alt=media&token=c001a7f2-1e97-46fb-9237-771db6f9446e";
        String st2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C2.jpg?alt=media&token=74ecd7a3-67a7-4702-95fb-19adab2c51d5";
        String st3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C3.jpg?alt=media&token=4a50fb77-acc5-4e27-bbc6-a4061f6cff0b";
        String sp1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EB%94%94%EB%9F%AD%EC%8A%A41.jpg?alt=media&token=53abbd06-50ed-4f91-b867-8b3edc4a8a84";
        String sp2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EB%94%94%EB%9F%AD%EC%8A%A42.jpg?alt=media&token=18d67bac-96c0-401f-8eda-0bac739477d7";
        String sp3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EB%94%94%EB%9F%AD%EC%8A%A43.jpg?alt=media&token=d485e7e0-b6f2-47e0-97d5-c191a32810c1";
        String sw1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EC%8A%A4%EC%9C%84%ED%8A%B81.jpg?alt=media&token=203c8cc0-d75d-42e3-9451-9faffd90c6f3";
        String sw2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EC%8A%A4%EC%9C%84%ED%8A%B82.jpg?alt=media&token=0f48ace2-01fa-4f84-a436-7ce1c3394b44";
        String sw3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%B8%8C%EB%A6%AD%EC%8A%A4%20%EA%B4%80%EA%B4%91%20%ED%98%B8%ED%85%94%2F%EC%8A%A4%EC%9C%84%ED%8A%B83.jpg?alt=media&token=0eae8104-71ce-423f-abd0-c8f4a13d8a33";


        image = new StoreImage(storeName, st1, st2, st3, sp1, sp2, sp3, sw1, sw2, sw3);

        //storePrice 삽입
        String sp_Large = "30,000원";
        String sp_Lodgment = "50,000원";
        String sw_Large = "40,000원";
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
}
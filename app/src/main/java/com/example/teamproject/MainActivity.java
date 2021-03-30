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
        String storeName = "모텔봄 남구로점 모텔";
        String location = "구로구 구로동로 25길 26";
        String phone = "02-853-3747";
        String location_tag = "구로";
        String mainImage = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C1.jpg?alt=media&token=b5dd100d-6f95-4c74-bc83-7d3ff185366b";
        String st_Large = "25,000원";
        String st_Lodgment = "55,000원";
        String st_Time1 = "4";
        String st_Time2 = "18:00";

        info = new StoreInfo(storeName, location, phone, 0, 0, 0, location_tag, mainImage, st_Large, st_Lodgment, st_Time1, st_Time2);

        //storeImage 삽입
        String st1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C1.jpg?alt=media&token=b5dd100d-6f95-4c74-bc83-7d3ff185366b";
        String st2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C2.jpg?alt=media&token=cd6bd8aa-c1c3-450e-b95c-d1acf7ec255c";
        String st3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C3.jpg?alt=media&token=1ff968d9-bb5c-4d50-8798-9c8b8bd7cc28";
        String sp1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EB%94%94%EB%9F%AD%EC%8A%A41.jpg?alt=media&token=974967fb-8709-4637-a2f6-4a608db6f805";
        String sp2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EB%94%94%EB%9F%AD%EC%8A%A42.jpg?alt=media&token=07e28694-8e06-47af-b548-9ee16fad0695";
        String sp3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EB%94%94%EB%9F%AD%EC%8A%A43.jpg?alt=media&token=3b3316a9-71ca-49a5-a092-daeee47869df";
        String sw1 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EC%8A%A4%EC%9C%84%ED%8A%B81.jpg?alt=media&token=d758e4e0-75e6-4c5b-a384-1a5634727b02";
        String sw2 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EC%8A%A4%EC%9C%84%ED%8A%B82.jpg?alt=media&token=d3eeba1d-ef6f-42da-80c5-caf387576b62";
        String sw3 = "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EB%AA%A8%ED%85%94%EB%B4%84%20%EB%82%A8%EA%B5%AC%EB%A1%9C%EC%A0%90%20%EB%AA%A8%ED%85%94%2F%EC%8A%A4%EC%9C%84%ED%8A%B83.jpg?alt=media&token=47401a35-f7e4-494f-ac07-5e5f819faaa7";



        image = new StoreImage(storeName, st1, st2, st3, sp1, sp2, sp3, sw1, sw2, sw3);

        //storePrice 삽입
        String sp_Large = "30,000원";
        String sp_Lodgment = "60,000원";
        String sw_Large = "35,000원";
        String sw_Lodgment = "65,000원";

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
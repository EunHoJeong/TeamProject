package com.example.teamproject;

import androidx.annotation.NonNull;
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

        String storeName = "삼성 디 에이스";

        info = new StoreInfo(storeName, "강남구 테헤란로77길 11-5", "02-563-1901", 0, 0, 0, "삼성", "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FStandard%2Fst1.jpg?alt=media&token=76e57d16-bbb0-42e3-a775-8bde55543e16");
        image = new StoreImage(storeName, "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FStandard%2Fst1.jpg?alt=media&token=76e57d16-bbb0-42e3-a775-8bde55543e16",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FStandard%2Fst2.jpg?alt=media&token=f7cd637d-4cf0-4817-b87f-ebd738b9b6eb",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FStandard%2Fst3.jpg?alt=media&token=52f4b2ea-ef23-44d8-ba30-061df2ddb76c",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FSuperior%2Fsp1.jpg?alt=media&token=0fd566ff-a6e8-43b4-9def-7cd83f4f5aba",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FSuperior%2Fsp2.jpg?alt=media&token=2c850674-fe9c-4b8e-b588-f298d482a15f",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FSuperior%2Fsp3.jpg?alt=media&token=11757efe-1642-456b-87ed-c9b43ae40841",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FSweet%2Fsw1.jpg?alt=media&token=ef09556d-7c26-4266-8e29-1cd1e1ab992c",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FSweet%2Fsw2.jpg?alt=media&token=06003a34-90aa-4503-9033-b6bb3c330c91",
                "https://firebasestorage.googleapis.com/v0/b/teamproject-39add.appspot.com/o/%EC%82%BC%EC%84%B1%20%EB%94%94%20%EC%97%90%EC%9D%B4%EC%8A%A4%2FSweet%2Fsw3.jpg?alt=media&token=e4f1bb01-e0d8-4a36-bfd5-55ce2e9e6540");
        price = new StorePrice(storeName, "20,000", "40,000", "20,000", "45,000", "25,000", "50,000");
        time = new StoreTime(storeName, "4", "18:00", "4", "18:00", "4", "18:00");

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
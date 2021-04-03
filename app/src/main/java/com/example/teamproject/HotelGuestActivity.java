package com.example.teamproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HotelGuestActivity extends AppCompatActivity {
    private LinearLayout pscStandard, pscSuperior, pscSweet;
    private ImageButton pscBack, pscLikeList, psctSearch;
    private ImageView pscViewPager, pscStar, pscImg1, pscImg2, pscImg3;
    private TextView pscRoomSelection, pscRankName, pscGrade, pscMaxGrade, pscReview,
            pscLargeRoom1, pscLargeRoom2, pscLargeRoom3, pscLodgment1, pscLodgment2, pscLodgment3,
            tvStTime1, tvSpTime1, tvSwTime1, tvStTime2, tvSpTime2, tvSwTime2, pscRatingBarScore;
    private Button pscLocation, pscCall, pscReservation, pscViewAll, pscCompletedReview;
    private EditText pscWriteReview;
    private RatingBar pscRatingBar;
    private DatabaseReference dbRf;

    private ArrayList<CeoReview> list = new ArrayList<>();

    private StoreInfo storeInfo;
    private StoreImage storeImage;
    private StorePrice storePrice;
    private StoreTime storeTime;

    private String name;
    private String RoomName;
    private String HotelName;
    private String contents;
    private static String id;

    private int index = 0;

    private float grade = 0;

    private boolean like = false;
    private static boolean login;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_guest);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        name = getIntent().getStringExtra("name");

        findViewByIdFunc();

        eventHandlerFunc();


        storeInfo = MainActivity.getStoreInfo(name);
        storeImage = MainActivity.getStoreImage();
        storePrice = MainActivity.getStorePrice();
        storeTime = MainActivity.getStoreTime();
        index = MainActivity.getIndex();

        getReviewData();


        if(id != null){
            checkSteamed();
        }

        setInfomation();

        SystemClock.sleep(500);
    }

    private void getReviewData() {
        CeoReview ceoReview = new CeoReview();
        list = ceoReview.getData(storeInfo.getStoreName());
    }

    private void checkSteamed() {
        String name = storeInfo.getStoreName();
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("Steamed");
        dbRf.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    if(s.getKey().equals(name)){
                        pscLikeList.setImageResource(R.drawable.img_like);
                        like = true;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setInfomation() {

        Glide.with(getApplicationContext())
                .load(storeInfo.getMainImage())
                .into(pscViewPager);

        Glide.with(getApplicationContext())
                .load(storeInfo.getMainImage())
                .into(pscImg1);

        Glide.with(getApplicationContext())
                .load(storeImage.getSp1())
                .into(pscImg2);

        Glide.with(getApplicationContext())
                .load(storeImage.getSw1())
                .into(pscImg3);

        pscRankName.setText(storeInfo.getStoreName());
        pscGrade.setText(String.valueOf(storeInfo.getGrade()));
        pscReview.setText("후기 "+storeInfo.getReview()+"개");
        tvStTime1.setText("대실 최대 "+storeInfo.getSt_Time1()+"시간");
        pscLargeRoom1.setText(storeInfo.getSt_Large());
        tvStTime2.setText("숙박 최대 "+storeInfo.getSt_Time2()+"부터");
        pscLodgment1.setText(storeInfo.getSt_Lodgment());

        tvSpTime1.setText("대실 최대 "+storeTime.getSp_Time1()+"시간");
        pscLargeRoom2.setText(storePrice.getSp_Large());
        tvSpTime2.setText("숙박 최대 "+storeTime.getSp_Time2()+"부터");
        pscLodgment2.setText(storePrice.getSp_Lodgment());

        tvSwTime1.setText("대실 최대 "+storeTime.getSw_Time1()+"시간");
        pscLargeRoom3.setText(storePrice.getSw_Large());
        tvSwTime2.setText("숙박 최대 "+storeTime.getSw_Time2()+"부터");
        pscLodgment3.setText(storePrice.getSw_Lodgment());
    }


    private void eventHandlerFunc() {
        pscLikeList.setOnClickListener(v -> {
            if(login){
                if(like){
                    deleteSteamedDB();
                    FragSteamed.deleteList(storeInfo.getStoreName());
                    pscLikeList.setImageResource(R.drawable.img_unlike);
                    like = false;
                }else{
                    insertSteamedDB();
                    FragSteamed.insertList(storeInfo.getStoreName());
                    pscLikeList.setImageResource(R.drawable.img_like);
                    like = true;
                    Toast.makeText(this, "찜 목록에 추가했습니다.", Toast.LENGTH_SHORT).show();
                }
            }else{
                showLoginDialog();
            }

        });


        pscLocation.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

        pscCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        pscReservation.setOnClickListener(view -> {

            Intent intent = new Intent(this, CheckinOutActivity.class);
            startActivity(intent);
        });

        pscBack.setOnClickListener(view -> {
            finish();
        });



        pscStandard.setOnClickListener(view -> {
            Intent intent = new Intent(this, RoomDetailsActivity.class);
            RoomName = "스탠다드";
            intent.putExtra("name", RoomName);

            intent.putExtra("img", storeInfo.getMainImage());

            intent.putExtra("hotel", storeInfo.getStoreName());

            intent.putExtra("largeRoom", storeInfo.getSt_Large());

            intent.putExtra("lodgment", storeInfo.getSt_Lodgment());

            startActivity(intent);

        });

        pscSuperior.setOnClickListener(view -> {
            Intent intent = new Intent(this, RoomDetailsActivity.class);
            RoomName = "슈페리얼";
            intent.putExtra("name", RoomName);

            intent.putExtra("img", storeImage.getSp1());

            intent.putExtra("hotel", storeInfo.getStoreName());

            intent.putExtra("largeRoom", storePrice.getSp_Large());

            intent.putExtra("lodgment", storePrice.getSp_Lodgment());

            startActivity(intent);
        });

        pscSweet.setOnClickListener(view -> {
            Intent intent = new Intent(this, RoomDetailsActivity.class);
            RoomName = "스위트";
            intent.putExtra("name", RoomName);

            intent.putExtra("img", storeImage.getSw1());

            intent.putExtra("hotel", storeInfo.getStoreName());

            intent.putExtra("largeRoom", storePrice.getSw_Large());

            intent.putExtra("lodgment", storePrice.getSw_Lodgment());

            startActivity(intent);
        });

        pscRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                pscRatingBarScore.setText("별점 : " + rating);
                grade = rating;
            }
        });

        pscCompletedReview.setOnClickListener(view -> {
            if(login){
                contents = pscWriteReview.getText().toString();
                if(contents.trim().equals("")){
                    Toast.makeText(HotelGuestActivity.this, "후기를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    insertReviewDB();
                    pscRatingBar.setRating(0);
                    pscRatingBarScore.setText("별점 : ");
                    pscWriteReview.setText("");
                }
            }else{
                showLoginDialog();
            }

        });

        pscViewAll.setOnClickListener(v -> {
            Intent intent = new Intent(this, CeoReviewActivity.class);
            intent.putExtra("list", list);
            intent.putExtra("grade", storeInfo.getGrade());
            intent.putExtra("review", storeInfo.getReview());
            startActivity(intent);
        });
    }



    private void insertReviewDB() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        DatabaseReference insert = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> childUpdates= new HashMap<>();
        Map<String, Object> map = null;

        Review review = new Review(storeInfo.getStoreName(), grade, date.substring(0, 14), contents);

        map = review.toMap();

        childUpdates.put("Review/"+id+"/"+date.substring(8, 17), map);

        CeoReview ceoReview = new CeoReview(id ,grade, date.substring(0, 14), contents);

        map = ceoReview.toMap();

        date = date.substring(8, 17);
        childUpdates.put("CeoReview/"+storeInfo.getStoreName()+"/"+date, map);

        String storeName = storeInfo.getStoreName();
        String location = storeInfo.getLocation();
        String phone = storeInfo.getPhone();

        float in_Grade = (storeInfo.getGrade()*storeInfo.getGradeCount())+grade;
        int gradeCount = storeInfo.getGradeCount()+1;

        in_Grade = (float)(Math.round((in_Grade/(float)gradeCount)*10)/10.0);

        int in_Review = storeInfo.getReview()+1;

        String location_tag = storeInfo.getLocation_tag();
        String mainImage = storeInfo.getMainImage();
        String st_Large = storeInfo.getSt_Large();
        String st_Lodgment = storeInfo.getSt_Lodgment();
        String st_Time1 = storeInfo.getSt_Time1();
        String st_Time2 = storeInfo.getSt_Time2();

        StoreInfo info = new StoreInfo(storeName, location, phone, in_Grade, gradeCount, in_Review, location_tag, mainImage, st_Large, st_Lodgment, st_Time1, st_Time2);
        map = info.toMap();
        childUpdates.put("storeInfo/"+storeName, map);
        insert.updateChildren(childUpdates);


        storeInfo.setGrade(in_Grade);
        storeInfo.setGradeCount(gradeCount);
        storeInfo.setReview(in_Review);

        pscGrade.setText(String.valueOf(storeInfo.getGrade()));
        pscReview.setText("후기 "+storeInfo.getReview()+"개");

        MainActivity.changeList(storeInfo);
        FragHome.changeInfoList(storeInfo, index);

    }

    private void insertSteamedDB() {
        DatabaseReference insert = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> childUpdates= new HashMap<>();
        Map<String, Object> map = null;
        Steamed steamed = new Steamed(storeInfo.getStoreName());

        map = steamed.toMap();

        childUpdates.put("Steamed/"+id+"/"+storeInfo.getStoreName(), map);
        insert.updateChildren(childUpdates);
    }

    private void deleteSteamedDB() {
        DatabaseReference delete = FirebaseDatabase.getInstance().getReference("Steamed");
        delete.child(id).child(storeInfo.getStoreName()).removeValue();

    }

    private void showLoginDialog(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(HotelGuestActivity.this);
        dlg.setMessage("로그인을 하시겠습니까?");
        dlg.setPositiveButton("아니요", null);
        dlg.setNeutralButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(HotelGuestActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        dlg.show();
    }

    private void findViewByIdFunc() {
        pscBack = findViewById(R.id.pscBack);
        pscLikeList = findViewById(R.id.pscLikeList);
        psctSearch = findViewById(R.id.psctSearch);
        pscViewPager = findViewById(R.id.pscViewPager);
        pscStar = findViewById(R.id.pscStar);
        pscImg1 = findViewById(R.id.pscImg1);
        pscImg2 = findViewById(R.id.pscImg2);
        pscImg3 = findViewById(R.id.pscImg3);
        pscLocation = findViewById(R.id.pscLocation);
        pscCall = findViewById(R.id.pscCall);
        pscReservation = findViewById(R.id.pscReservation);
        pscRoomSelection = findViewById(R.id.pscRoomSelection);
        pscRankName = findViewById(R.id.pscRankName);
        pscGrade = findViewById(R.id.pscGrade);
        pscReview = findViewById(R.id.pscReview);
        pscMaxGrade = findViewById(R.id.pscMaxGrade);
        pscLargeRoom1 = findViewById(R.id.pscLargeRoom1);
        pscLargeRoom2 = findViewById(R.id.pscLargeRoom2);
        pscLargeRoom3 = findViewById(R.id.pscLargeRoom3);
        pscLodgment1 = findViewById(R.id.pscLodgment1);
        pscLodgment2 = findViewById(R.id.pscLodgment2);
        pscLodgment3 = findViewById(R.id.pscLodgment3);
        tvStTime1 = findViewById(R.id.tvStTime1);
        tvSpTime1 = findViewById(R.id.tvSpTime1);
        tvSwTime1 = findViewById(R.id.tvSwTime1);
        tvStTime2 = findViewById(R.id.tvStTime2);
        tvSpTime2 = findViewById(R.id.tvSpTime2);
        tvSwTime2 = findViewById(R.id.tvSwTime2);
        pscStandard = findViewById(R.id.pscStandard);
        pscSuperior = findViewById(R.id.pscSuperior);
        pscSweet = findViewById(R.id.pscSweet);
        pscViewAll = findViewById(R.id.pscViewAll);
        pscCompletedReview = findViewById(R.id.pscCompletedReview);
        pscWriteReview = findViewById(R.id.pscWriteReview);
        pscRatingBar = findViewById(R.id.pscRatingBar);
        pscRatingBarScore = findViewById(R.id.pscRatingBarScore);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(id != null){
            checkSteamed();
        }
    }

    public static void setLogin(String i, boolean f){
        id = i;
        login = f;
    }
}

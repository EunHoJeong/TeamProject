package com.example.teamproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RoomDetailsActivity extends AppCompatActivity {

    private TextView pscRoomName, pscHotelName, pscCall, pscLargeRoomPrice, pscLodgmentPrice, pscRoom, psclodgment;
    private ImageButton pscBack;
    private Button pscReservation, pscRoomSelection;
    private ImageView pscViewPager;

    private String storeName;
    private String phone;
    private String name;
    private String img;
    private String hotel;
    private String largeRoom;
    private String lodgment;
    private String roomType;
    private static String id;
    private static String check_In;
    private static String check_Out;
    private static boolean login = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewByIdFunc();

        eventHandlerFunc();

        storeName = getIntent().getStringExtra("storeName");

        phone = getIntent().getStringExtra("phone");

        img = getIntent().getStringExtra("img");

        name = getIntent().getStringExtra("name");

        hotel = getIntent().getStringExtra("hotel");

        largeRoom = getIntent().getStringExtra("largeRoom");

        lodgment = getIntent().getStringExtra("lodgment");


        Glide.with(getApplicationContext())
                .load(img)
                .into(pscViewPager);

        pscRoomName.setText(name);
        pscHotelName.setText(hotel);
        pscLargeRoomPrice.setText(largeRoom);
        pscLodgmentPrice.setText(lodgment);

    }


    private void eventHandlerFunc() {
        pscBack.setOnClickListener(view -> {
            finish();
        });

        pscCall.setOnClickListener(view -> {
            Uri uri = Uri.parse("tel:"+phone);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });

        pscReservation.setOnClickListener(view -> {
            Intent intent = new Intent(this, CheckinOutActivity.class);
            startActivity(intent);
        });

        pscRoom.setOnClickListener(v -> {
            if(login){
                roomType = "대실";
                showReservationDialog(roomType);
            }else{
                showLoginDialog();
            }
        });

        psclodgment.setOnClickListener(v -> {
            if(login){
                roomType = "숙박";
                showReservationDialog(roomType);
            }else{
                showLoginDialog();
            }
        });

    }

    private void showLoginDialog(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(RoomDetailsActivity.this);
        dlg.setMessage("로그인을 하시겠습니까?");
        dlg.setPositiveButton("아니요", null);
        dlg.setNeutralButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RoomDetailsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        dlg.show();
    }

    private void showReservationDialog(String type){
        AlertDialog.Builder dlg = new AlertDialog.Builder(RoomDetailsActivity.this);
        dlg.setMessage(check_In+" - "+ check_Out+" "+type+"예약 하시겠습니까?");
        dlg.setPositiveButton("아니요", null);
        dlg.setNeutralButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RoomDetailsActivity.this, "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                insertCeoDB();
                insertUserDB();

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(cal.getTime());


                if(check_In.equals(date)){

                    CeoReservation crv = new CeoReservation(id, name ,roomType);
                    RegisterActivity.insertList(crv);
                }

            }
        });
        dlg.show();
    }

    private void insertUserDB() {
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> map = null;

        String date = check_In+" 18:00 ~ "+check_Out+" 12:00";
        Reservation rv = new Reservation(storeName, name, date);
        MainActivity.insertRvData(rv);


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String time = sdf.format(cal.getTime());

        map = rv.toMap();
        childUpdates.put("Reservation/"+id+"/"+time, map);
        dbRf.updateChildren(childUpdates);
    }

    private void insertCeoDB() {
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> map = null;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String time = sdf.format(cal.getTime());


        CeoReservation crv = new CeoReservation(id, name, roomType);

        map = crv.toMap();
        childUpdates.put("CeoReservation/"+check_In+"/"+storeName+"/"+time, map);

        dbRf.updateChildren(childUpdates);


    }

    private void findViewByIdFunc() {
        pscBack = findViewById(R.id.pscBack);
        pscRoomName = findViewById(R.id.pscRoomName);
        pscHotelName = findViewById(R.id.pscHotelName);
        pscCall = findViewById(R.id.pscCall);
        pscReservation = findViewById(R.id.pscReservation);
        pscRoomSelection = findViewById(R.id.pscRoomSelection);
        pscViewPager = findViewById(R.id.pscViewPager);
        pscLargeRoomPrice = findViewById(R.id.pscLargeRoomPrice);
        pscLodgmentPrice = findViewById(R.id.pscLodgmentPrice);
        pscRoom = findViewById(R.id.pscRoom);
        psclodgment = findViewById(R.id.psclodgment);
    }

    public static void setDate(String in, String out){
        check_In = in;
        check_Out = out;
    }

    public static void setId(String i){
        id = i;
    }

    public static void setLogin(boolean flag){login = flag;}
}

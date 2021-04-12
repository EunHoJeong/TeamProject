package com.example.teamproject.data;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CeoReservation {
    private String id;
    private String roomName;
    private String roomType;

    public CeoReservation(){}

    public CeoReservation(String id, String roomName, String roomType) {
        this.id = id;
        this.roomName = roomName;
        this.roomType = roomType;
    }

    public String getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("roomName", roomName);
        result.put("roomType", roomType);


        return result;
    }

    public ArrayList<CeoReservation> getData(String date){
        ArrayList<CeoReservation> list = new ArrayList<>();
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("CeoReservation");
        dbRf.child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    list.add(s.getValue(CeoReservation.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SystemClock.sleep(500);

        return list;
    }
}

package com.example.teamproject.data;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reservation {
    private String storeName;
    private String roomName;
    private String date;

    public Reservation(){}

    public Reservation(String storeName, String roomName, String date) {
        this.storeName = storeName;
        this.roomName = roomName;
        this.date = date;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("roomName", roomName);
        result.put("date", date);


        return result;
    }


    public ArrayList<Reservation> getData(String id){
        ArrayList<Reservation> list = new ArrayList<>();
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("Reservation");
        dbRf.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    list.add(s.getValue(Reservation.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return list;
    }

    public ArrayList<String> getKey(String id){
        ArrayList<String> key = new ArrayList<>();
        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("Reservation");
        dbRf.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    key.add(s.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return key;
    }

}

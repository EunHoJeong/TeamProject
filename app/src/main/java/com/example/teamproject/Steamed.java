package com.example.teamproject;

import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Steamed {
    private String id;
    private String storeName;

    public Steamed(){}

    public Steamed(String id, String storeName) {
        this.id = id;
        this.storeName = storeName;
    }

    public String getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("storeName", storeName);

        return result;
    }

    public ArrayList<String> getData(String id){

        ArrayList<String> nameList = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRf = db.getReference();

        dbRf.child("Steamed").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    nameList.add(s.getKey());
                    Log.d("Test", nameList.size()+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SystemClock.sleep(500);

        return nameList;
    }
}

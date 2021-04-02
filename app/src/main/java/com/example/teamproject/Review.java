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

public class Review {
    private String storeName;
    private float grade;
    private String id;
    private String contents;

    public Review(){};

    public Review(String storeName, float grade, String id, String contents) {
        this.storeName = storeName;
        this.grade = grade;
        this.id = id;
        this.contents = contents;
    }

    public String getStoreName() {
        return storeName;
    }

    public float getGrade() {
        return grade;
    }

    public String getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("grade", grade);
        result.put("id", id);
        result.put("contents", contents);

        return result;
    }

    public ArrayList<Review> getData(String id){
        ArrayList<Review> reviewList = new ArrayList<>();
        Review review = null;

        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("Review");
        dbRf.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    reviewList.add(s.getValue(Review.class));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SystemClock.sleep(500);

        return reviewList;
    }

}

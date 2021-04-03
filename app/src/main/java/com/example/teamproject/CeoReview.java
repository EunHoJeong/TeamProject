package com.example.teamproject;

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

public class CeoReview {
    private String id;
    private float grade;
    private String date;
    private String contents;

    public CeoReview(){};

    public CeoReview(String id, float grade, String date, String contents) {
        this.id = id;
        this.grade = grade;
        this.date = date;
        this.contents = contents;
    }

    public String getId() {
        return id;
    }

    public float getGrade() {
        return grade;
    }

    public String getDate() {
        return date;
    }

    public String getContents() {
        return contents;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("grade", grade);
        result.put("date", date);
        result.put("contents", contents);

        return result;
    }

    public ArrayList<CeoReview> getData(String storeName){
        ArrayList<CeoReview> reviewList = new ArrayList<>();
        CeoReview review = null;

        DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("CeoReview");
        dbRf.child(storeName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    reviewList.add(s.getValue(CeoReview.class));

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

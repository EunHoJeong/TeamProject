package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {
    private RecyclerView recyclerReview;
    private ReviewAdapter myReviewAdapter;

    private ArrayList<Review> reviewList = new ArrayList<>();

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        id = getIntent().getStringExtra("id");

        getMyReviewData();


        Log.d("Test", reviewList.size()+"");
        myReviewAdapter = new ReviewAdapter(getApplicationContext(), reviewList);
        recyclerReview = findViewById(R.id.recyclerReview);
        recyclerReview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerReview.setAdapter(myReviewAdapter);
    }

    private void getMyReviewData() {
        for(int i = 1; i <= 5; i++){
            reviewList.add(new Review("숙소이름"+i,4.5f,id+i, "리뷰내용 ex: 깨끗해요" ));
        }
    }
}
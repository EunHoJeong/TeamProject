package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        id = getIntent().getStringExtra("id");

        if(reviewList.size() == 0){
            getMyReviewData();
        }

        myReviewAdapter = new ReviewAdapter(getApplicationContext(), reviewList);
        recyclerReview = findViewById(R.id.recyclerReview);
        recyclerReview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerReview.setAdapter(myReviewAdapter);
    }

    private void getMyReviewData() {
        Review review = new Review();

        reviewList = review.getData(id);


    }
}
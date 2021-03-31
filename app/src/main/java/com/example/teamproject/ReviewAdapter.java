package com.example.teamproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Review> list;

    public ReviewAdapter(Context context, ArrayList<Review> list) {
        Log.d("Test", list.size()+"");
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_review, parent, false);
        RecyclerView.ViewHolder viewHolder = new MyReview(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("Text", position+"");
        if(holder instanceof MyReview){
            MyReview myReview = (MyReview) holder;
            myReview.setInfo(position);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ?list.size() : 0;
    }

    public class MyReview extends RecyclerView.ViewHolder{
        private TextView tvReviewName, tvReviewId, tvReviewContents;
        private RatingBar myRatingBar;


        public MyReview(@NonNull View itemView) {
            super(itemView);
            tvReviewName = itemView.findViewById(R.id.tvReviewName);
            tvReviewId = itemView.findViewById(R.id.tvReviewId);
            tvReviewContents = itemView.findViewById(R.id.tvReviewContents);
            myRatingBar = itemView.findViewById(R.id.myRatingBar);
        }

        public void setInfo(int position){
            tvReviewName.setText(list.get(position).getStoreName());
            myRatingBar.setRating(list.get(position).getGrade());
            tvReviewId.setText(list.get(position).getId());
            tvReviewContents.setText(list.get(position).getContents());
        }
    }
}

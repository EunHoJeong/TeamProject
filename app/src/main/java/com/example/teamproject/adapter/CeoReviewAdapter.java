package com.example.teamproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.data.CeoReview;
import com.example.teamproject.R;

import java.util.ArrayList;

public class CeoReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<CeoReview> list;

    public CeoReviewAdapter(Context context, ArrayList<CeoReview> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_ceo_review, parent, false);
        RecyclerView.ViewHolder viewHolder = new CeoReviewData(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CeoReviewData){
            CeoReviewData crv = (CeoReviewData) holder;
            crv.setInfo();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class CeoReviewData extends RecyclerView.ViewHolder{
        private TextView tvCeoReviewId, tvCeoReviewDate, tvCeoReviewContents;
        private RatingBar CeoRatingBar;


        public CeoReviewData(@NonNull View itemView) {
            super(itemView);
            tvCeoReviewId = itemView.findViewById(R.id.tvCeoReviewId);
            tvCeoReviewDate = itemView.findViewById(R.id.tvCeoReviewDate);
            tvCeoReviewContents = itemView.findViewById(R.id.tvCeoReviewContents);
            CeoRatingBar = itemView.findViewById(R.id.CeoRatingBar);
        }

        public void setInfo(){
            tvCeoReviewId.setText(list.get(getAdapterPosition()).getId());
            tvCeoReviewDate.setText(list.get(getAdapterPosition()).getDate());
            tvCeoReviewContents.setText(list.get(getAdapterPosition()).getContents());
            CeoRatingBar.setRating(list.get(getAdapterPosition()).getGrade());
        }
    }
}

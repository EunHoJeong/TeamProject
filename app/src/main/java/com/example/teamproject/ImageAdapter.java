package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.teamproject.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<StoreInfo> infoList;

    public ImageAdapter(Context context, ArrayList<StoreInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_image, parent, false);
        RecyclerView.ViewHolder viewHolder = new MainImage(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  MainImage){
            MainImage m = (MainImage) holder;
            Glide.with(m.itemView)
                    .load(infoList.get(position).getMainImage())
                    .into(m.imgRank);


            m.tvRankName.setText(infoList.get(position).getStoreName());
            m.tvGrade.setText(String.valueOf(infoList.get(position).getGrade()));
            m.tvReview.setText("후기 " +infoList.get(position).getReview() +"개");
            m.tvRankPrice.setText(infoList.get(position).getSt_Lodgment());


        }


    }

    @Override
    public int getItemCount() {
        return infoList != null ? infoList.size() : 0;
    }


    public class MainImage extends RecyclerView.ViewHolder{
        private ImageView imgRank;
        private TextView tvRankName, tvGrade, tvReview, tvRankPrice;

        public MainImage(@NonNull View itemView) {
            super(itemView);
            imgRank = itemView.findViewById(R.id.imgRank);
            tvRankName = itemView.findViewById(R.id.tvRankName);
            tvGrade = itemView.findViewById(R.id.tvGrade);
            tvReview = itemView.findViewById(R.id.tvReview);
            tvRankPrice = itemView.findViewById(R.id.tvRankPrice);

            itemView.setOnClickListener(view -> {
                Toast.makeText(context, (getAdapterPosition()+1)+"번", Toast.LENGTH_SHORT).show();

            });
        }
    }
}

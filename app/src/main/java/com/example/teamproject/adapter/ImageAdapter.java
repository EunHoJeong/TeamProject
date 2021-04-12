package com.example.teamproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teamproject.R;
import com.example.teamproject.data.StoreInfo;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<StoreInfo> infoList;
    private OnItemClickListener mListener = null;

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
        return infoList != null ? 10 : 0;
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
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(view, position);
                }
            });
        }
    }

    public interface  OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

}

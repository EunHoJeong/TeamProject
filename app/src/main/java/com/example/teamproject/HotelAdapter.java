package com.example.teamproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<StoreInfo> infoList;

    private OnItemClickListener mListener = null;

    public HotelAdapter(Context context, ArrayList<StoreInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hotel_image, parent, false);
        RecyclerView.ViewHolder viewHolder = new RecyclerImage(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerImage){
            RecyclerImage ri = (RecyclerImage) holder;
            ri.setInformation(position);
        }
    }

    @Override
    public int getItemCount() {
        return infoList != null ? infoList.size() : 0;
    }

    public class RecyclerImage extends RecyclerView.ViewHolder{
        private ImageView pscRank;
        private TextView pscRocation, pscName, pscPernight, pscRankPrice;
        private RatingBar pscRatingBar;
        private ArrayList<StoreInfo> info = new ArrayList<>();

        public RecyclerImage(@NonNull View itemView) {
            super(itemView);
            pscRank      = itemView.findViewById(R.id.pscRank);
            pscRocation  = itemView.findViewById(R.id.pscRocation);
            pscName      = itemView.findViewById(R.id.pscName);
            pscPernight  = itemView.findViewById(R.id.pscPernight);
            pscRankPrice = itemView.findViewById(R.id.pscRankPrice);
            pscRatingBar = itemView.findViewById(R.id.pscRatingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int posiotion = getAdapterPosition();
                    if(posiotion != RecyclerView.NO_POSITION){
                        mListener.onItemClick(view, posiotion);
                    }

                    Intent intent = new Intent(context, HotelGuestActivity.class);
                    StoreInfo storeInfo = infoList.get(posiotion);
                    intent.putExtra("storeInfo", storeInfo);
                    context.startActivity(intent);
                }
            });
        }

        public void setInformation(int position){
            Glide.with(itemView)
                    .load(infoList.get(position).getMainImage())
                    .into(pscRank);

            pscRocation.setText(infoList.get(position).getLocation());
            pscName.setText(infoList.get(position).getStoreName());
            pscRatingBar.setNumStars(infoList.get(position).getGrade());
            pscPernight.setText("대실 "+infoList.get(position).getSt_Time1()+"시간 " + infoList.get(position).getSt_Large());
            pscRankPrice.setText("숙박 "+infoList.get(position).getSt_Time2()+"부터 " + infoList.get(position).getSt_Lodgment());
        }
    }

    public interface  OnItemClickListener{

        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
}

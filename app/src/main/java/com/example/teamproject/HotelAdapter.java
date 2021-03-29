package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<Integer> imageList;
    private int item;

    public HotelAdapter(Context context, ArrayList<Integer> imageList, int item) {
        this.context = context;
        this.imageList = imageList;
        this.item = item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int item) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (item){
            case 1 :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hotel_image, parent, false);
                viewHolder = new RecyclerImage(view);
                break;
            case 2 :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hotel_image, parent, false);
                viewHolder = new RecyclerImage(view);
                break;
            case 3 :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hotel_image, parent, false);
                viewHolder = new RecyclerImage(view);
                break;
            case 4 :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hotel_image, parent, false);
                viewHolder = new RecyclerImage(view);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (item) {
            case 1 :
            if (holder instanceof RecyclerImage) {
                RecyclerImage recyclerImage = (RecyclerImage) holder;
                recyclerImage.pscRank.setImageResource(imageList.get(position));
            }
            break;

        }

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class RecyclerImage extends RecyclerView.ViewHolder{
        private ImageView pscRank;
        private TextView pscRocation, pscName, pscPernight, pscRankPrice;

        public RecyclerImage(@NonNull View itemView) {
            super(itemView);
            pscRank      = itemView.findViewById(R.id.pscRank);
            pscRocation  = itemView.findViewById(R.id.pscRocation);
            pscName      = itemView.findViewById(R.id.pscName);
            pscPernight  = itemView.findViewById(R.id.pscPernight);
            pscRankPrice = itemView.findViewById(R.id.pscRankPrice);
        }
    }
}

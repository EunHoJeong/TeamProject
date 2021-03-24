package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Integer> imageList;

    public ImageAdapter(Context context, ArrayList<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
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
            MainImage mainImg = (MainImage) holder;
            mainImg.vpImg.setImageResource(imageList.get(position));
        }

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, (position+1)+"번", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    public class MainImage extends RecyclerView.ViewHolder{
        private ImageView vpImg;

        public MainImage(@NonNull View itemView) {
            super(itemView);
            vpImg = itemView.findViewById(R.id.imgRank);
        }
    }
}

package com.example.teamproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.R;
import com.example.teamproject.data.ReservationConfirmData;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ReservationConfirmData> list;

    public ReservationAdapter(Context context, ArrayList<ReservationConfirmData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_reservation, parent, false);
        RecyclerView.ViewHolder viewHolder = new BreakdownHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BreakdownHolder){
            BreakdownHolder bdH = (BreakdownHolder) holder;
            bdH.setText(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BreakdownHolder extends RecyclerView.ViewHolder{
        private TextView tvState, tvStoreName, tvRoomInfo, tvReservationDate;


        public BreakdownHolder(@NonNull View itemView) {
            super(itemView);
            tvState = itemView.findViewById(R.id.tvState);
            tvStoreName = itemView.findViewById(R.id.tvStoreName);
            tvRoomInfo = itemView.findViewById(R.id.tvRoomInfo);
            tvReservationDate = itemView.findViewById(R.id.tvReservationDate);
        }

        public void setText(int position){
            tvState.setText(list.get(position).getState());
            tvStoreName.setText(list.get(position).getStoreName());
            tvRoomInfo.setText(list.get(position).getRoomInfo());
            tvReservationDate.setText(list.get(position).getDate());
        }

    }

}

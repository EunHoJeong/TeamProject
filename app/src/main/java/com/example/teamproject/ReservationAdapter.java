package com.example.teamproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Reservation> list;
    private String time;


    private HotelAdapter.OnItemClickListener mListener = null;

    public ReservationAdapter(Context context, ArrayList<Reservation> list, String time) {
        this.context = context;
        this.list = list;
        this.time = time;
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
        return list != null ?list.size() : 0;
    }

    public class BreakdownHolder extends RecyclerView.ViewHolder{
        private TextView tvStoreName, tvRoomInfo, tvReservationDate;
        private Button btnCancel;


        public BreakdownHolder(@NonNull View itemView) {
            super(itemView);
            tvStoreName = itemView.findViewById(R.id.tvStoreName);
            tvRoomInfo = itemView.findViewById(R.id.tvRoomInfo);
            tvReservationDate = itemView.findViewById(R.id.tvReservationDate);
            btnCancel = itemView.findViewById(R.id.btnCancel);
        }

        public void setText(int position){

            tvStoreName.setText(list.get(position).getStoreName());
            tvRoomInfo.setText(list.get(position).getRoomName());
            tvReservationDate.setText(list.get(position).getDate());
            String time2 = list.get(position).getDate().substring(0, 10)+" 17:00";
            int compare = time.compareTo(time2);
            Log.d("Test", position+"/"+time+"-"+time2+"="+compare);

            if(compare < 0){
                btnCancel.setVisibility(View.VISIBLE);
            }else{
                btnCancel.setVisibility(View.INVISIBLE);
            }

            btnCancel.setOnClickListener(view -> {

                if(position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(view, position);
                }
            });
        }



    }

    public interface  OnItemClickListener{

        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(HotelAdapter.OnItemClickListener listener){
        this.mListener = listener;
    }

}

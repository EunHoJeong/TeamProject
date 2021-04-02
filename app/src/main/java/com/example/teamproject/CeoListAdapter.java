package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CeoListAdapter extends BaseAdapter {
    private ArrayList<CeoReservationData> list = new ArrayList<>();

    public CeoListAdapter(ArrayList<CeoReservationData> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_ceo, viewGroup, false);
        }

        TextView tvCeoId = view.findViewById(R.id.tvCeoId);
        TextView tvCeoRoomName = view.findViewById(R.id.tvCeoRoomName);
        TextView tvCeoRoomData = view.findViewById(R.id.tvCeoRoomData);

        tvCeoId.setText(list.get(position).getId());
        tvCeoRoomName.setText(list.get(position).getRoomName());
        tvCeoRoomData.setText(list.get(position).getRoomData());

        return view;
    }
}

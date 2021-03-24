package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragHome extends Fragment {
    public static final ArrayList<Integer> imageList = new ArrayList<>();

    private ImageButton imgbtnHotel;
    private RecyclerView recyclerImage;
    private ImageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_home, container, false);

        int[] posterID = new int[]{R.drawable.mov01, R.drawable.mov02,
                R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};

        for(int i = 0; i < posterID.length; i++){
            imageList.add(posterID[i]);
        }

        imgbtnHotel = view.findViewById(R.id.imgbtnHotel);
        recyclerImage = view.findViewById(R.id.recyclerImage);

        adapter = new ImageAdapter(getActivity(), imageList);
        recyclerImage.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerImage.setAdapter(adapter);

        imgbtnHotel.setOnClickListener(v -> {
            Intent intent;
        });

        return view;
    }
}

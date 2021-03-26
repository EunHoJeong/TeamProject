package com.example.teamproject.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.adapter.ImageAdapter;
import com.example.teamproject.activity.MotelActivity;
import com.example.teamproject.R;
import com.example.teamproject.activity.SearchMapActivity;
import com.example.teamproject.activity.MapActivity;

import java.util.ArrayList;

public class FragHome extends Fragment {
    public static final ArrayList<Integer> imageList = new ArrayList<>();

    private ImageButton imgbtnHotel;
    private ImageButton imgbtnMotel;
    private ImageButton imgBtnF;
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
        imgbtnMotel = view.findViewById(R.id.imgbtnMotel);
        imgBtnF = view.findViewById(R.id.imgBtnF);
        recyclerImage = view.findViewById(R.id.recyclerImage);

        adapter = new ImageAdapter(getActivity(), imageList);
        recyclerImage.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerImage.setAdapter(adapter);

        imgbtnHotel.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), MotelActivity.class);
            startActivity(intent);
        });

        imgbtnMotel.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), MapActivity.class);
            startActivity(intent);
        });

        imgBtnF.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), SearchMapActivity.class);
            startActivity(intent);
        });

        return view;
    }
}

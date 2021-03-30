package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.teamproject.ImageAdapter;
import com.example.teamproject.MotelActivity;
import com.example.teamproject.R;
import com.example.teamproject.SearchMapActivity;
import com.example.teamproject.MapActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FragHome extends Fragment {
    public static final ArrayList<Integer> imageList = new ArrayList<>();

    private ImageButton imgbtnHotel;
    private ImageButton imgbtnMotel;
    private ImageButton imgbtnPension;
    private ImageButton imgbtnTheme;
    private RecyclerView recyclerImage;
    private ImageAdapter adapter;
    private ArrayList<StoreInfo> info = new ArrayList<>();

    private FirebaseDatabase db;
    private DatabaseReference dbRf;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_home, container, false);

        if(info.size()==0){
            getMotelData();
        }


        findViewByIdFunc(view);

        adapter = new ImageAdapter(getActivity(), info);
        recyclerImage.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerImage.setAdapter(adapter);

        eventHandlerFunc();







        return view;
    }

    private void getMotelData() {
        db = FirebaseDatabase.getInstance();
        dbRf = db.getReference("storeInfo");

        dbRf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    StoreInfo storeInfo = s.getValue(StoreInfo.class);
                    info.add(storeInfo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SystemClock.sleep(2000);
    }

    private void eventHandlerFunc() {

        imgbtnHotel.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), MotelActivity.class);
            startActivity(intent);
        });

        imgbtnMotel.setOnClickListener(view1 -> {
            Intent intent=new Intent(getActivity(), MotelActivity.class);
            startActivity(intent);
        });

        imgbtnPension.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), MotelActivity.class);
            startActivity(intent);
        });

        imgbtnTheme.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), MotelActivity.class);
            startActivity(intent);
        });
    }

    private void findViewByIdFunc(ViewGroup view) {
        imgbtnHotel = view.findViewById(R.id.imgbtnHotel);
        imgbtnMotel = view.findViewById(R.id.imgbtnMotel);
        imgbtnPension = view.findViewById(R.id.imgbtnPension);
        imgbtnTheme = view.findViewById(R.id.imgbtnTheme);
        recyclerImage = view.findViewById(R.id.recyclerImage);
    }
}

package com.example.teamproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FragSteamed extends Fragment {
    private RecyclerView recyclerSteamed;
    private HotelAdapter adapter;
    private ArrayList<StoreInfo> info = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();

    private FirebaseDatabase db;
    private DatabaseReference dbRf;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_steamed, container, false);

        if(info.size() == 0){
            getData();
        }

        recyclerSteamed = view.findViewById(R.id.recyclerSteamed);
        adapter = new HotelAdapter(getActivity(), info);
        recyclerSteamed.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerSteamed.setAdapter(adapter);

        return view;
    }

    private void getData() {
//        db = FirebaseDatabase.getInstance();
//        dbRf = db.getReference("Steamed").child("gh888");
//
//        dbRf.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot s : snapshot.getChildren()){
//                    String name = s.getKey();
//                    nameList.add(name);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        db = FirebaseDatabase.getInstance();

            dbRf = db.getReference("storeInfo");
            dbRf.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds : snapshot.getChildren()){
                        StoreInfo storeInfo = ds.getValue(StoreInfo.class);
                        info.add(storeInfo);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        SystemClock.sleep(1500);

    }
}

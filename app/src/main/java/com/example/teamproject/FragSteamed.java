package com.example.teamproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button btnSteamedLogin;
    private RecyclerView recyclerSteamed;
    private HotelAdapter adapter;
    private ArrayList<StoreInfo> info = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();

    private FirebaseDatabase db;
    private DatabaseReference dbRf;

    private static boolean flag = false;
    private static String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_steamed, container, false);

        if(info.size() == 0 && flag){
            getData();
        }

        btnSteamedLogin = view.findViewById(R.id.btnSteamedLogin);
        recyclerSteamed = view.findViewById(R.id.recyclerSteamed);

        if(flag){
            btnSteamedLogin.setVisibility(View.INVISIBLE);
            btnSteamedLogin.setClickable(false);
        }else{
            btnSteamedLogin.setVisibility(View.VISIBLE);
            btnSteamedLogin.setClickable(true);
        }

        adapter = new HotelAdapter(getActivity(), info);
        recyclerSteamed.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerSteamed.setAdapter(adapter);
        Log.d("Test", nameList.size()+"");
        Log.d("Test", info.size()+"");
        return view;
    }

    private void getData() {
        db = FirebaseDatabase.getInstance();
        dbRf = db.getReference();

        dbRf.child("Steamed").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){

                    nameList.add(s.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        db = FirebaseDatabase.getInstance();
        dbRf = db.getReference();
        for(String s : nameList) {
            Log.d("Test", s);
            dbRf.child("storeInfo").child(s.toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                        StoreInfo storeInfo = snapshot.getValue(StoreInfo.class);
                        info.add(storeInfo);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



    }

    public static void setFlag(boolean relay){
        flag = relay;
    }

    public static void setId(String i){
        id = i;
    }
}

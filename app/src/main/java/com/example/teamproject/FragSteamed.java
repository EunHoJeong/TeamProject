package com.example.teamproject;

import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.appcompat.app.AlertDialog;
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
    private static ArrayList<StoreInfo> info = new ArrayList<>();
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

        findViewByIdFunc(view);

        eventHandlerFunc();





        return view;
    }

    private void eventHandlerFunc() {
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

        adapter.setOnItemClickListener((v, position)-> {
            Intent intent = new Intent(getActivity(), HotelGuestActivity.class);
            String name = info.get(position).getStoreName();
            intent.putExtra("name", name);
            startActivity(intent);
        });

        btnSteamedLogin.setOnClickListener(view -> {
            showDialog();
        });
    }

    private void findViewByIdFunc(ViewGroup view) {
        btnSteamedLogin = view.findViewById(R.id.btnSteamedLogin);
        recyclerSteamed = view.findViewById(R.id.recyclerSteamed);
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

        SystemClock.sleep(1000);

        int size = nameList.size();
        String[] tag = new String[size];
        for(int i = 0; i < size; i++){
            tag[i] = nameList.get(i);
        }

        info = FragHome.getSteamedList(tag);



    }

    private void showDialog(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
        dlg.setMessage("로그인을 하시겠습니까?");
        dlg.setPositiveButton("아니요", null);
        dlg.setNeutralButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        dlg.show();
    }

    public static void setFlag(boolean relay){
        flag = relay;
    }

    public static void setId(String i){
        id = i;
    }
}

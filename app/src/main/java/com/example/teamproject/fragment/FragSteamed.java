package com.example.teamproject.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.R;
import com.example.teamproject.activity.HotelGuestActivity;
import com.example.teamproject.activity.LoginActivity;
import com.example.teamproject.activity.MainActivity;
import com.example.teamproject.adapter.HotelAdapter;
import com.example.teamproject.data.StoreInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragSteamed extends Fragment implements HotelAdapter.OnItemClickListener {
    private Button btnSteamedLogin;
    private RecyclerView recyclerSteamed;
    private static HotelAdapter adapter;
    private static ArrayList<StoreInfo> info = new ArrayList<>();
    private static ArrayList<String> nameList = new ArrayList<>();

    private FirebaseDatabase db;
    private DatabaseReference dbRf;

    private static boolean flag = false;
    private static boolean start = false;
    private static String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_steamed, container, false);

        if(info.size() == 0 && flag && !start){
            getData();
        }

        findViewByIdFunc(view);

        eventHandlerFunc();




        adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), HotelGuestActivity.class);
                String name = info.get(position).getStoreName();
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });


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



        btnSteamedLogin.setOnClickListener(view -> {
            showDialog();
        });
    }

    private void findViewByIdFunc(ViewGroup view) {
        btnSteamedLogin = view.findViewById(R.id.btnSteamedLogin);
        recyclerSteamed = view.findViewById(R.id.recyclerSteamed);
    }

    private void getData() {


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

    public static void setList(ArrayList<String> list){
        nameList = list;
    }

    public static void insertList(String storeName){

        info.add(MainActivity.getStoreInfo(storeName));
    }

    public static void deleteList(String storeName){
        for(int i = 0; i < info.size(); i++){
            if(storeName.equals(info.get(i).getStoreName())){
                info.remove(i);
            }
        }
        adapter.notifyDataSetChanged();

    }

    public static void clearNameList(){
        nameList.clear();
        info.clear();
    }

    public static void setStart(){
        start = true;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(start){
            start = false;
            getData();

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

            adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), HotelGuestActivity.class);
                    String name = info.get(position).getStoreName();
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            });
        }
    }


    @Override
    public void onItemClick(View view, int position) {

    }
}

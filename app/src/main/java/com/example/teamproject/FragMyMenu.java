package com.example.teamproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.teamproject.R;
import com.example.teamproject.LoginActivity;
import com.example.teamproject.ReservationActivity;

public class FragMyMenu extends Fragment {
    private Button btnLoginSignUp,btnRegister;

    private LinearLayout llReservationHotel, llMyReview;

    private static boolean flag = false;
    private static boolean flag2 = false;
    private static String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_my_menu, container, false);


        findViewByIdFunc(view);

        eventHandlerFunc();



        return view;
    }

    private void eventHandlerFunc() {





        btnLoginSignUp.setOnClickListener(view -> {
            if (flag){
                showDialogLogout();
            }else{
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(),RegisterActivity.class);
            startActivity(intent);
        });

        llMyReview.setOnClickListener(view -> {
            if(flag){
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }else{
                showDialog();
            }
        });

        llReservationHotel.setOnClickListener(view -> {
            if(flag) {
                Intent intent = new Intent(getActivity(), ReservationActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }else{
                showDialog();
            }
        });
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

    private void showDialogLogout(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
        dlg.setMessage("로그아웃 하시겠습니까?");
        dlg.setPositiveButton("아니요", null);
        dlg.setNeutralButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                flag = false;
                flag2 = false;
                id = null;
                FragSteamed.setFlag(false);
                RoomDetailsActivity.setLogin(false);
                FragSteamed.setId(null);
                RoomDetailsActivity.setId(null);
                HotelGuestActivity.setLogin(null, false);
                FragSteamed.clearNameList();
                btnLoginSignUp.setText("로그인 및 회원가입 하기");
                btnRegister.setVisibility(View.INVISIBLE);
            }
        });
        dlg.show();
    }

    private void findViewByIdFunc(ViewGroup v) {
        llReservationHotel = v.findViewById(R.id.llReservationHotel);

        btnLoginSignUp = v.findViewById(R.id.btnLoginSignUp);
        btnRegister = v.findViewById(R.id.btnRegister);

        llMyReview = v.findViewById(R.id.llMyReview);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (flag){
            btnLoginSignUp.setText("로그아웃");
        }else{
            btnLoginSignUp.setText("로그인 및 회원가입 하기");
        }

        if(flag2){
            btnRegister.setVisibility(View.VISIBLE);
        }else{
            btnRegister.setVisibility(View.INVISIBLE);
        }

    }

    public static void setFlag(boolean relay){
        flag = relay;
    }
    public static void setFlag2(boolean relay){
        flag2 = relay;
    }
    public static boolean getLogin(){
        return flag;
    }
    public static String getMyId() {return id;}

    public static void setId(String i){
        id = i;
    }

}

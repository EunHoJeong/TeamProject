package com.example.teamproject.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teamproject.R;
import com.example.teamproject.activity.LoginActivity;
import com.example.teamproject.activity.SignUpActivity;

public class FragMyMenu extends Fragment {
    private Button btnLoginSignUp;
    private LinearLayout llReservationPension, llReservationHotel, llReservationMotel;

    private static boolean flag = false;

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
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        llReservationHotel.setOnClickListener(view -> {

        });
    }

    private void findViewByIdFunc(ViewGroup v) {
        llReservationPension = v.findViewById(R.id.llReservationPension);
        llReservationMotel = v.findViewById(R.id.llReservationMotel);
        llReservationHotel = v.findViewById(R.id.llReservationHotel);

        btnLoginSignUp = v.findViewById(R.id.btnLoginSignUp);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (flag){
            btnLoginSignUp.setText("로그인 성공");
        }else{
            btnLoginSignUp.setText("로그인 및 회원가입 하기");
        }

    }

    public static void setFlag(boolean relay){
        flag = relay;
    }


}

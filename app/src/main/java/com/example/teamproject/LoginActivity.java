package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtID, edtPassword;
    private TextView tvSignUp;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewByIdFunc();

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {

        btnLogin.setOnClickListener(view -> {
            String id =edtID.getText().toString();
            String pw = edtPassword.getText().toString();

            DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("User");
            dbRf.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.getValue(User.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            try{
                Log.d("Test", id+"="+user.getId()+", "+pw+"="+user.getPassword());
                if(id.equals(user.getId()) && pw.equals(user.getPassword())){
                    FragMyMenu.setFlag(true);

                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }catch (NullPointerException n){
                Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
            }




        });

        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

    }

    private void findViewByIdFunc() {
        btnLogin = findViewById(R.id.btnLogin);

        edtID = findViewById(R.id.edtID);
        edtPassword = findViewById(R.id.edtPassword);

        tvSignUp = findViewById(R.id.tvSignUp);
    }



}
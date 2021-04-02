package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtID, edtPassword;
    private TextView tvSignUp, tvSignUpCeo;
    private User user;
    private ArrayList<User> ceo = new ArrayList<>();
    private DatabaseReference dbRf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ceo = MainActivity.getCeo();
        dbRf = FirebaseDatabase.getInstance().getReference("User");
        findViewByIdFunc();

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {



        btnLogin.setOnClickListener(view -> {
            dbRf = FirebaseDatabase.getInstance().getReference("User");

            String id =edtID.getText().toString();
            String pw = edtPassword.getText().toString();

            try{

                if(id.equals(user.getId()) && pw.equals(user.getPassword())){
                    FragMyMenu.setFlag(true);
                    FragSteamed.setFlag(true);
                    FragMyMenu.setId(id);
                    FragSteamed.setId(id);
                    Steamed steamed = new Steamed();

                    ArrayList<String>nameList = steamed.getData(id);
                    FragSteamed.setList(nameList);
                    for(User u : ceo){

                        if(id.equals(u.getId())){
                            FragMyMenu.setFlag2(true);
                            break;
                        }
                    }
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }catch (NullPointerException n){
                Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
            }




        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    String id = edtID.getText().toString();
                    dbRf.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            user = snapshot.getValue(User.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }catch (Exception e){

                }

            }
        });

        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        tvSignUpCeo.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.putExtra("flag", true);
            startActivity(intent);
        });

    }

    private void findViewByIdFunc() {
        btnLogin = findViewById(R.id.btnLogin);

        edtID = findViewById(R.id.edtID);
        edtPassword = findViewById(R.id.edtPassword);

        tvSignUp = findViewById(R.id.tvSignUp);
        tvSignUpCeo = findViewById(R.id.tvSignUpCeo);
    }



}
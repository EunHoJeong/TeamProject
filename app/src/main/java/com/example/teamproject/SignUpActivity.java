package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.teamproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtSUID, edtSUPassword, edtSUPWCheck, edtSUEmailID, edtSUEmail, edtSUPhone, edtSUCeo;
    private Button btnOverlapCheckID, btnJoin;
    private TextView tvCheckID, tvCheckPassword, tvCheckPasswordCheck;

    private boolean idCheck = false;
    private boolean pwCheck = false;
    private boolean pwCheck2 = false;
    private boolean userAndCeo = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        userAndCeo = getIntent().getBooleanExtra("flag", false);

        findViewByIdFunc();
        if(userAndCeo){
            edtSUCeo.setVisibility(View.VISIBLE);
        }else{
            edtSUCeo.setVisibility(View.INVISIBLE);
        }

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {


        btnOverlapCheckID.setOnClickListener(v -> {

            String id = edtSUID.getText().toString();

            boolean flag = checkID(id);

            if (flag){
                Toast.makeText(this, "아이디를 조건에맞게 입력해주세요.", Toast.LENGTH_SHORT).show();
                idCheck = false;
            }else{



                DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference("User");
                dbRf.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        if(user == null){
                            tvCheckID.setText("사용 가능한 아이디입니다.");
                            tvCheckID.setTextColor(0xFF87CEEB);
                            idCheck = true;

                        }else{
                            tvCheckID.setText("이미 존재하는 아이디입니다.");
                            tvCheckID.setTextColor(Color.RED);
                            idCheck = false;

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }


        });//end of setOn

        edtSUID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvCheckID.setText("영문 소문자와 숫자만 사용하여 5~12자의 아이디를 입력해주세요.");
                tvCheckID.setTextColor(Color.RED);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtSUPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = edtSUPassword.getText().toString();

                boolean flag = checkPassword(password);

                if(flag){
                    tvCheckPassword.setText("사용 가능한 비밀번호입니다.");
                    tvCheckPassword.setTextColor(0xFF87CEEB);
                    pwCheck = true;
                }else{
                    tvCheckPassword.setText("영문 소문자와 특수문자 1가지 이상을 조합하여 6~20자로 입력해주세요. (공백제외)");
                    tvCheckPassword.setTextColor(Color.RED);
                    pwCheck = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtSUPWCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edtSUPassword.getText().toString().equals(edtSUPWCheck.getText().toString())){
                    tvCheckPasswordCheck.setText("비밀번호가 일치합니다.");
                    tvCheckPasswordCheck.setTextColor(0xFF87CEEB);
                    pwCheck2 = true;
                }else{
                    tvCheckPasswordCheck.setText("비밀번호가 일치하지않습니다.");
                    tvCheckPasswordCheck.setTextColor(Color.RED);
                    pwCheck2 = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnJoin.setOnClickListener(v -> {

            if(idCheck && pwCheck && pwCheck2){

                String id = edtSUID.getText().toString();
                String password = edtSUPassword.getText().toString();
                String email = edtSUEmailID.getText().toString() + "@" + edtSUEmail.getText().toString();
                String phone = edtSUPhone.getText().toString();

                DatabaseReference dbRf = FirebaseDatabase.getInstance().getReference();
                DatabaseReference dbRf2 = FirebaseDatabase.getInstance().getReference();

                HashMap<String, Object> childUpdates = new HashMap<>();


                Map<String, Object> userMap = null;

                User user = new User(id, password, email, phone);
                userMap = user.toMap();
                if(userAndCeo){
                    String storeName = edtSUCeo.getText().toString();


                    HashMap<String, Object> childUpdates2 = new HashMap<>();
                    childUpdates2.put("CEO/"+storeName, userMap);
                    dbRf2.updateChildren(childUpdates2);

                }

                childUpdates.put("User/"+id, userMap);
                dbRf.updateChildren(childUpdates);


                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "입력을 정확히 해주세요", Toast.LENGTH_SHORT).show();
            }


        });//end of btnJoin

    }//end of event



    private boolean checkPassword(String password) {
        boolean flag = false;
        boolean flagPassword = false;

        if(password.length() >=5 && password.length() <=12) {
            for(int i = 0; i < password.length(); i++) {
                if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                    continue;
                }else if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                    continue;
                }else if(password.charAt(i) >= '0' && password.charAt(i) <= '9'){
                    continue;
                }else if(password.charAt(i) == ' '){
                    flagPassword = true;
                    break;
                }else{
                    flag = true;
                }
            }//end of for
        }

        if(flag) {
            if(flagPassword) {
                System.out.println("비밀번호 사용 불가능합니다.");
                flag = false;
            }else {
                System.out.println("비밀번호 사용 가능합니다.");

            }

        }

        return flag;
    }


    private boolean checkID(String id) {
        boolean flag = false;

        if (id.length() >= 5 && id.length() <= 12) {

            for (int i = 0; i < id.length(); i++) {

                if (id.charAt(i) >= 'a' && id.charAt(i) <= 'z') {
                    continue;
                } else if (id.charAt(i) >= '0' && id.charAt(i) <= '9') {
                    continue;
                } else {
                    flag = true;
                    break;
                }//end of if

            }//end of for
        }else{
            flag = true;
        }//end of if

        return flag;
    }

    private void findViewByIdFunc() {
        edtSUID = findViewById(R.id.edtSUID);
        edtSUPassword = findViewById(R.id.edtSUPassword);
        edtSUPWCheck = findViewById(R.id.edtSUPWCheck);
        edtSUEmailID = findViewById(R.id.edtSUEmailID);
        edtSUEmail = findViewById(R.id.edtSUEmail);
        edtSUPhone = findViewById(R.id.edtSUPhone);
        edtSUCeo = findViewById(R.id.edtSUCeo);

        btnOverlapCheckID = findViewById(R.id.btnOverlapCheckID);
        btnJoin = findViewById(R.id.btnJoin);

        tvCheckID = findViewById(R.id.tvCheckID);
        tvCheckPassword = findViewById(R.id.tvCheckPassword);
        tvCheckPasswordCheck = findViewById(R.id.tvCheckPasswordCheck);
    }
}
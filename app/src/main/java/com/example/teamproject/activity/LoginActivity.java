package com.example.teamproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamproject.R;
import com.example.teamproject.frag.FragMyMenu;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtID, edtPassword;
    private TextView tvSignUp;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewByIdFunc();

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {

        btnLogin.setOnClickListener(view -> {
            boolean flag = true;
            FragMyMenu.setFlag(flag);
            finish();
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
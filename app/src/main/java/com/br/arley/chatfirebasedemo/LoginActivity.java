package com.br.arley.chatfirebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView tvGoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setObjects();
        setObjectsOnClick();


    }

    public void setObjectsOnClick(){
        tvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Teste",  edtPassword.getText().toString());
                Log.d("Teste",  edtEmail.getText().toString());
            }
        });
    }


    public void setObjects(){
        edtEmail = findViewById(R.id.activity_login_edt_email);
        edtPassword = findViewById(R.id.activity_login_edt_password);
        btnLogin = findViewById(R.id.activity_login_bt_login);
        tvGoRegister = findViewById(R.id.activity_login_tv_go_register);
    }
}

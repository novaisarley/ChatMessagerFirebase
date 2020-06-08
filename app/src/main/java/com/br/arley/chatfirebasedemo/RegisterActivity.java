package com.br.arley.chatfirebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword;
    Button btnRegister, btSelectPhoto;
    TextView tvGoLogin;
    Uri selectedUri;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        setObjects();
        setObjectsOnClick();

    }

    public void setObjectsOnClick(){
        tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        btSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });
    }


    public void setObjects(){
        edtName = findViewById(R.id.activity_register_edt_name);
        edtEmail = findViewById(R.id.activity_register_edt_email);
        edtPassword = findViewById(R.id.activity_register_edt_password);
        btnRegister = findViewById(R.id.activity_register_bt_register);
        btSelectPhoto = findViewById(R.id.activity_register_bt_select_photo);
        tvGoLogin = findViewById(R.id.activity_register_tv_go_login);
    }

    public void createUser(){
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString();

        if(name == null || name.isEmpty() || email == null || email.isEmpty() || password ==null || password.isEmpty()){
            Toast.makeText(this, R.string.preencher_campos, Toast.LENGTH_SHORT).show();
        }
        else{
            if (password.length()>=6){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Log.d("Teste", task.getResult().getUser().getUid());
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Teste", e.getMessage());
                            }
                        });
            }
            else{
                Toast.makeText(this, R.string.senha_seis_caracteres, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0){
            selectedUri = data.getData();
        }
    }

    public void selectPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);

    }
}
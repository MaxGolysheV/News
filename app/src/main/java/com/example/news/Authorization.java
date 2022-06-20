package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.concurrent.Executor;

public class Authorization extends AppCompatActivity {

    EditText et_login, et_password;
    Button btnCreate, btnAuth, btnScanner;
    Executor executor;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo prompInfo;
    DatabaseHelper databaseHelper;
    String role;
    boolean user = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);


        et_login = findViewById(R.id.auth_login);
        et_password = findViewById(R.id.auth_password);
        btnCreate = findViewById(R.id.auth_btnNewAccount);
        btnAuth = findViewById(R.id.auth_btnLogin);
        databaseHelper = new DatabaseHelper(this);

        btnScanner = findViewById(R.id.UserScanner);
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Authorization.this, executor, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Log.e("ErrorAuth", errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent = new Intent(Authorization.this, NewsUserWindow.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.e("FailedAuth", "FAIL!");
            }
        });

        prompInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Авторизация")
                .setSubtitle("Приложите палец")
                .setNegativeButtonText("Отмена")
                .build();

        btnScanner.setOnClickListener(view -> {
            biometricPrompt.authenticate(prompInfo);
        });

        btnCreate.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Registration.class);
            startActivity(intent);
        });

        btnAuth.setOnClickListener(view -> {
            Cursor res = databaseHelper.getUserdata();

            while(res.moveToNext()){
                if(!user){
                    if(res.getString(1).equals(et_login.getText().toString()) && res.getString(2).equals(et_password.getText().toString())){
                        role = res.getString(3);
                        user = true;
                    }
                }
                else
                    break;
            }

            if(!user){
                Toast.makeText(getApplicationContext(), "Неверный логин/пароль!", Toast.LENGTH_LONG).show();
            }
            else{
                if(role.equals("user")){
                    user = false;
                    Intent intent = new Intent(getApplicationContext(), NewsUserWindow.class);
                    startActivity(intent);
                }
                else if(role.equals("admin")){
                    Intent intent = new Intent(getApplicationContext(), NewsAdminWindow.class);
                    startActivity(intent);
                    user = false;
                }
            }

        });

    }
}
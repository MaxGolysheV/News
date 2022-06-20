package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText login, password;
    Button RegAdmin, RegUser, Back;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = findViewById(R.id.reg_login);
        password = findViewById(R.id.reg_password);
        RegAdmin = findViewById(R.id.reg_btnNewAccountAdmin);
        RegUser = findViewById(R.id.reg_btnNewAccountUser);
        Back = findViewById(R.id.reg_btnBack);
        databaseHelper = new DatabaseHelper(this);

        Back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Authorization.class);
            startActivity(intent);
        });

        RegAdmin.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.InsertUser(login.getText().toString(), password.getText().toString(), "admin");
            if(checkInsertData){
                Toast.makeText(getApplicationContext(), "Администратор успешно создан", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), Authorization.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });

        RegUser.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.InsertUser(login.getText().toString(), password.getText().toString(), "user");
            if (checkInsertData){
                Toast.makeText(getApplicationContext(), "Пользователь успешно создан", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), Authorization.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });

    }
}
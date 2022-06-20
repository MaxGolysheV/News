package com.example.news;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminWindow extends AppCompatActivity {

    EditText id, title, text;
    Button insert, update, delete, show, showUsers, back;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_window);


        databaseHelper = new DatabaseHelper(this);

        id = findViewById(R.id.aw_et_id);
        title = findViewById(R.id.aw_et_title);
        text = findViewById(R.id.aw_et_text);

        insert = findViewById(R.id.aw_btn_Insert);
        update = findViewById(R.id.aw_btn_Update);
        delete = findViewById(R.id.aw_btn_Delete);
        show = findViewById(R.id.aw_btn_SelectNews);
        showUsers = findViewById(R.id.aw_btn_SelectUsers);
        back = findViewById(R.id.aw_btn_Back);

        back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NewsAdminWindow.class);
            startActivity(intent);
        });

        if(!title.toString().isEmpty() && !text.toString().isEmpty()) {
            insert.setOnClickListener(view -> {

                Boolean checkInsertData = databaseHelper.InsertNews(title.getText().toString(), text.getText().toString());
                if (checkInsertData) {
                    Toast.makeText(getApplicationContext(), "Новость успешно добалвена", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
                }
            });
        }

        update.setOnClickListener(view -> {
            if(!id.toString().isEmpty() && !title.toString().isEmpty() && !text.toString().isEmpty()) {
                Boolean checkInsertData = databaseHelper.UpdateNews(id.getText().toString(), title.getText().toString(), text.getText().toString());
                if (checkInsertData) {
                    Toast.makeText(getApplicationContext(), "Новость успешно обновлена", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(view -> {
            if(!id.toString().isEmpty()) {
                Boolean checkInsertData = databaseHelper.DeleteNews(id.getText().toString());
                if (checkInsertData) {
                    Toast.makeText(getApplicationContext(), "Новость успешно удалена", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
                }
            }
        });

        show.setOnClickListener(view -> {
            Cursor res = databaseHelper.getNewsdata();
            if(res.getCount() == 0){
                Toast.makeText(getApplicationContext(), "Нет новостей", Toast.LENGTH_LONG).show();
                return;
            }

            StringBuilder builder = new StringBuilder();
            while(res.moveToNext()){
                builder.append("ID: ").append(res.getString(0)).append("\n");
                builder.append("Заголовок: ").append(res.getString(1)).append("\n");
                builder.append("Текст: ").append(res.getString(2)).append("\n\n");
            }

            AlertDialog.Builder _builder = new AlertDialog.Builder(AdminWindow.this);
            _builder.setCancelable(true);
            _builder.setTitle("Список новостей:");
            _builder.setMessage(builder.toString());
            _builder.show();
        });

        showUsers.setOnClickListener(view -> {
            Cursor users = databaseHelper.getUserdata();
            if(users.getCount() == 0){
                Toast.makeText(getApplicationContext(), "Нет пользователей", Toast.LENGTH_LONG).show();
                return;
            }

            StringBuilder builder = new StringBuilder();
            while(users.moveToNext()){
                builder.append("ID: ").append(users.getString(0)).append("\n");
                builder.append("Логин: ").append(users.getString(1)).append("\n");
                builder.append("Пароль: ").append(users.getString(2)).append("\n");
                builder.append("Роль: ").append(users.getString(3)).append("\n\n");
            }

            AlertDialog.Builder _builder = new AlertDialog.Builder(AdminWindow.this);
            _builder.setCancelable(true);
            _builder.setTitle("Список пользователей:");
            _builder.setMessage(builder.toString());
            _builder.show();
        });

    }
}
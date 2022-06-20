package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class NewsAdminWindow extends AppCompatActivity {

    ArrayList<Model> news = new ArrayList<>();
    DatabaseHelper databaseHelper;
    Button btnNews, admin_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_admin_window);


        RecyclerView admin_rv = findViewById(R.id.user_rv);
        databaseHelper = new DatabaseHelper(this);
        btnNews = findViewById(R.id.admin_button);
        admin_exit = findViewById(R.id.admin_exit);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, news);
        admin_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        admin_rv.setAdapter(adapter);

        admin_exit.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Authorization.class);
            startActivity(intent);
        });

        btnNews.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdminWindow.class);
            startActivity(intent);
        });

        Cursor res = databaseHelper.getNewsdata();

        while(res.moveToNext()){
            news.add(new Model (res.getString(1), res.getString(2)));
        }
    }
}
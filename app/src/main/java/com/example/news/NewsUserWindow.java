package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class NewsUserWindow extends AppCompatActivity {


    ArrayList<Model> news = new ArrayList<>();
    DatabaseHelper databaseHelper;
    Button user_exit;
    RecyclerView user_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_user_window);

        user_exit = findViewById(R.id.user_exit);
        databaseHelper = new DatabaseHelper(this);

        user_rv = findViewById(R.id.user_rv);
        RecyclerView recyclerView = findViewById(R.id.user_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, news);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        user_exit.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Authorization.class);
            startActivity(intent);
        });

        Cursor res = databaseHelper.getNewsdata();

        while(res.moveToNext()){
            news.add(new Model (res.getString(1), res.getString(2)));
        }
    }
}
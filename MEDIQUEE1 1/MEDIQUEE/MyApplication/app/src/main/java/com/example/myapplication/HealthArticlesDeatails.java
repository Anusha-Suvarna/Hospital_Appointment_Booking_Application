package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDeatails extends AppCompatActivity {
    TextView tv1;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_deatails);

        tv1 = findViewById(R.id.textViewHADtitle);
        img = findViewById(R.id.imageHADView);

        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        if (intent.hasExtra("imageResourceId")) {
            int resId = intent.getIntExtra("imageResourceId", 0);
            img.setImageResource(resId);
        }

    }
}
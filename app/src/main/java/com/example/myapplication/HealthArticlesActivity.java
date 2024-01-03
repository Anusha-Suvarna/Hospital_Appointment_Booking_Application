package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HealthArticlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        ListView listView = findViewById(R.id.listView);

        // Sample health articles data
        final String[] articleTitles = {"Walking Daily","Home care of COVID-19","Stop Smoking",


                "Menstrual Cramps",
                "Healthy Gut"};
        final int[] articleImages = {R.drawable.health1, R.drawable.health2, R.drawable.health3};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, articleTitles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HealthArticlesActivity.this, HealthArticlesDeatails.class);
                intent.putExtra("text1", articleTitles[position]);
                intent.putExtra("imageResourceId", articleImages[position]);
                startActivity(intent);
            }
        });

    }
}
package com.example.moviecatalog;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLang = findViewById(R.id.btn_lang);
        btnLang.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        });

        Button btnMovieDir = findViewById(R.id.btn_movie_dir);
        btnMovieDir.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MovieDirectory.class);
            startActivity(intent);
        });
    }
}
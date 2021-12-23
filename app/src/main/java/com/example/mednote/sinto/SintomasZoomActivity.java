package com.example.mednote.sinto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mednote.R;

public class SintomasZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas_zoom);
        String Title, Desc;

        TextView TvTitle = findViewById(R.id.TvSinZoomTitle);
        TextView TvDesc = findViewById(R.id.TvSinZoomDesc);

        Intent intent = getIntent();

        Title = intent.getStringExtra("Titulo");
        Desc = intent.getStringExtra("Desc");

        TvTitle.setText(Title);
        TvDesc.setText(Desc);
    }
}
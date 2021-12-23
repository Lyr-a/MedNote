package com.example.mednote.trat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mednote.R;

public class TratamentoZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamento_zoom);
        String Title, Desc;

        TextView TvTitle = findViewById(R.id.TvTraZoomTitle);
        TextView TvDesc = findViewById(R.id.TvTraZoomDesc);

        Intent intent = getIntent();

        Title = intent.getStringExtra("Titulo");
        Desc = intent.getStringExtra("Desc");

        TvTitle.setText(Title);
        TvDesc.setText(Desc);
    }
}
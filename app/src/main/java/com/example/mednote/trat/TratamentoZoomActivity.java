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
        String Title, Desc, Data, Hora;

        TextView TvTitle = findViewById(R.id.TvTraZoomTitle);
        TextView TvDesc = findViewById(R.id.TvTraZoomDesc);
        TextView TvData = findViewById(R.id.TvTraZoomData);
        TextView TvHora = findViewById(R.id.TvTraZoomHora);

        Intent intent = getIntent();

        Title = intent.getStringExtra("Titulo");
        Desc = intent.getStringExtra("Desc");
        Data = intent.getStringExtra("Data");
        Hora = intent.getStringExtra("Hora");

        TvTitle.setText(Title);
        TvDesc.setText(Desc);
        TvData.setText(Data);
        TvHora.setText(Hora);
    }
}
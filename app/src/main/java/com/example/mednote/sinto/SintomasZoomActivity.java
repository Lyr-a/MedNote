package com.example.mednote.sinto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mednote.R;

public class SintomasZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas_zoom);

 

        Uri Im;
        String Title, Desc, Dia, Hora;

        ImageView Imv = findViewById(R.id.ImvSinZoom);
        TextView TvTitle = findViewById(R.id.TvSinZoomTitle);
        TextView TvDesc = findViewById(R.id.TvSinZoomDesc);
        TextView TvData = findViewById(R.id.TvSinZoomData);
        TextView TvHora = findViewById(R.id.TvSinZoomHora);

        Intent intent = getIntent();

        Im = intent.getData();
        Title = intent.getStringExtra("Titulo");
        Desc = intent.getStringExtra("Desc");
        Dia = intent.getStringExtra("Data");
        Hora = intent.getStringExtra("Hora");

        if (Im == null){
            Imv.setImageResource(R.drawable.ic_not_image);
        }
        else{
            Imv.setImageURI(Im);
        }
        TvTitle.setText(Title);
        TvDesc.setText(Desc);
        TvData.setText(Dia);
        TvHora.setText(Hora);
    }
}
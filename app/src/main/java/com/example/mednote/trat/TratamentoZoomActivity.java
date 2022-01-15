package com.example.mednote.trat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mednote.R;
import com.example.mednote.recvi.TratamentoZoomAdapter;
import com.example.mednote.recvi.TratamentoZoomItem;

import java.io.File;
import java.util.ArrayList;

public class TratamentoZoomActivity extends AppCompatActivity {

    TratamentoZoomAdapter tratamentoZoomAdapter;
    ArrayList<TratamentoZoomItem> TzItens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamento_zoom);
        String Title, Desc, Data, Hora;
        ArrayList<String> Photos;



        TextView TvTitle = findViewById(R.id.TvTraZoomTitle);
        TextView TvDesc = findViewById(R.id.TvTraZoomDesc);
        TextView TvData = findViewById(R.id.TvTraZoomData);
        TextView TvHora = findViewById(R.id.TvTraZoomHora);
        RecyclerView RvPhoto = findViewById(R.id.RvTratZoomPhoto);


        tratamentoZoomAdapter = new TratamentoZoomAdapter(TratamentoZoomActivity.this, TzItens);
        RvPhoto.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TratamentoZoomActivity.this, LinearLayoutManager.HORIZONTAL, false);
        RvPhoto.setLayoutManager(layoutManager);
        RvPhoto.setAdapter(tratamentoZoomAdapter);

        Intent intent = getIntent();

        Photos = intent.getStringArrayListExtra("Photo");
        Title = intent.getStringExtra("Titulo");
        Desc = intent.getStringExtra("Desc");
        Data = intent.getStringExtra("Data");
        Hora = intent.getStringExtra("Hora");


        for (String foto : Photos){
            TratamentoZoomItem tratamentoZoomItem = new TratamentoZoomItem();
            tratamentoZoomItem.Photo = Uri.fromFile(new File(foto));
            TzItens.add(tratamentoZoomItem);
        }



        TvTitle.setText(Title);
        TvDesc.setText(Desc);
        TvData.setText(Data);
        TvHora.setText(Hora);
    }
}
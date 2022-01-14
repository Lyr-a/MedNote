package com.example.mednote.trat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mednote.R;
import com.example.mednote.recvi.TratamentoZoomAdapter;
import com.example.mednote.recvi.TratamentoZoomItem;

import java.io.File;
import java.util.ArrayList;

public class TratamentoZoomActivity extends AppCompatActivity {

    TratamentoZoomAdapter tratamentoZoomAdapter;
    ArrayList<String> TrzItem = new ArrayList<>();
    ArrayList<TratamentoZoomItem> teste = new ArrayList<>();

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

        TrzItem = (intent.getStringArrayListExtra("Photo"));
        Title = intent.getStringExtra("Titulo");
        Desc = intent.getStringExtra("Desc");
        Data = intent.getStringExtra("Data");
        Hora = intent.getStringExtra("Hora");


        tratamentoZoomAdapter = new TratamentoZoomAdapter(TratamentoZoomActivity.this, teste);

        RecyclerView RvPhotos = findViewById(R.id.RvTratZoomPhoto);
        RvPhotos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TratamentoZoomActivity.this);
        RvPhotos.setLayoutManager(layoutManager);
        RvPhotos.setAdapter(tratamentoZoomAdapter);

        /*
        for(int i = 0; i< TrzItem.size(); i++){
            TratamentoZoomItem tratamentoZoomItem = new TratamentoZoomItem();
            Uri uri = Uri.fromFile(new File(TrzItem.get(i)));
            tratamentoZoomItem.photo = uri;
            teste.add(tratamentoZoomItem);

            tratamentoZoomAdapter.notifyItemInserted(TrzItem.size()-1);
        }


         */
        tratamentoZoomAdapter.notifyItemInserted(TrzItem.size()-1);
        TvTitle.setText(Title);
        TvDesc.setText(Desc);
        TvData.setText(Data);
        TvHora.setText(Hora);
    }
}
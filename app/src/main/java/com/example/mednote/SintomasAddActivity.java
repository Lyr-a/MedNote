package com.example.mednote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SintomasAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas_add);
        Button BtnSintomasAdd = findViewById(R.id.BtnSintomasAdd);
        BtnSintomasAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SintomasAddActivity.this, MainActivity.class);
                //startActivity(intent);
                Intent i = new Intent();
                finish();
            }
        });
    }
}
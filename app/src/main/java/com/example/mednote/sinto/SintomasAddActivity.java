package com.example.mednote.sinto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mednote.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SintomasAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas_add);

        Button BtnSintomasAdd = findViewById(R.id.BtnSintomasAdd);

        BtnSintomasAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText EtSintomaAddDesc = findViewById(R.id.EtSintomaAddDesc);
                EditText EtSintomaAddTitle = findViewById(R.id.EtSintomaAddTitle);
                String Hora = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                String Dia = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                String SinTitulo = EtSintomaAddTitle.getText().toString();
                if (SinTitulo.isEmpty()){
                    Toast.makeText(SintomasAddActivity.this, "Você Precisa inserir um título", Toast.LENGTH_SHORT).show();
                    return;
                }

                String SinDesc = EtSintomaAddDesc.getText().toString();
                if (SinDesc.isEmpty()){
                    Toast.makeText(SintomasAddActivity.this, "Você Precisa inserir uma descrição", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("SinTitle", SinTitulo);
                intent.putExtra("SinDesc", SinDesc);
                intent.putExtra("SinHora", Hora);
                intent.putExtra("SinDia", Dia);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
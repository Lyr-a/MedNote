package com.example.mednote.trat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mednote.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TratamentoAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamento_add);

        //region VIEWS

        FloatingActionButton FbtnTratamentoAdd = findViewById(R.id.FbtnTratamentoAdd);
        //endregion

        //region BUTTONS

        FbtnTratamentoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText EtTratamentoAddDesc = findViewById(R.id.EtTratamentoAddDesc);
                EditText EtTratamentoAddTitle = findViewById(R.id.EtTratamentoAddTitle);
                String Hora = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                String Dia = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                String TraTitulo = EtTratamentoAddTitle.getText().toString();
                if (TraTitulo.isEmpty()){
                    Toast.makeText(TratamentoAddActivity.this, "Você Precisa inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                String TraDesc = EtTratamentoAddDesc.getText().toString();
                if (TraDesc.isEmpty()){
                    Toast.makeText(TratamentoAddActivity.this, "Você Precisa inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("TraTitle", TraTitulo);
                intent.putExtra("TraDesc", TraDesc);
                intent.putExtra("TraHora", Hora);
                intent.putExtra("TraDia", Dia);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        //endregion

    }
}
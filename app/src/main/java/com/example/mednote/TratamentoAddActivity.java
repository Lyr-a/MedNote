package com.example.mednote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TratamentoAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamento_add);

        //region VIEWS

        FloatingActionButton FbtnTratamentoAdd = findViewById(R.id.FbtnTratamentoAdd);
        EditText EtTratamentoAddTitle = findViewById(R.id.EtTratamentoAddTitle);
        EditText EtTratamentoAddDesc = findViewById(R.id.EtTratamentoAddDesc);

        //endregion

        //region BUTTONS

        FbtnTratamentoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                String TraTitulo = EtTratamentoAddTitle.getText().toString();
                String TraDesc = EtTratamentoAddDesc.getText().toString();

                intent.putExtra("TraTitle", TraTitulo);
                intent.putExtra("TraDesc", TraDesc);

                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        //endregion

    }
}
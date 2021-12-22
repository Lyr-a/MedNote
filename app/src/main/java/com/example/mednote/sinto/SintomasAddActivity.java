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

                String SinTitulo = EtSintomaAddTitle.getText().toString();
                if (SinTitulo.isEmpty()){
                    Toast.makeText(SintomasAddActivity.this, "Você Precisa inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                String SinDesc = EtSintomaAddDesc.getText().toString();
                if (SinDesc.isEmpty()){
                    Toast.makeText(SintomasAddActivity.this, "Você Precisa inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("SinTitle", SinTitulo);
                intent.putExtra("SinDesc", SinDesc);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
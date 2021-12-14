package com.example.mednote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //region Views
        Button BtnRegister = findViewById(R.id.BtnRegister);
        Spinner SpnGenero = findViewById(R.id.SpnRegisterGender);
        Spinner SpnRegisterBloodType = findViewById(R.id.SpnRegisterBloodType);
        Spinner SpnRegistroBloodRh = findViewById(R.id.SpnRegistroBloodRh);
        //endregion
        //region Spinners
        ArrayAdapter<CharSequence> GeneroAdapter = ArrayAdapter.createFromResource(this, R.array.generos,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> BloodAdapter = ArrayAdapter.createFromResource(this, R.array.tipos_sanguineos,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> RhAdapter = ArrayAdapter.createFromResource(this, R.array.rh,
                android.R.layout.simple_spinner_item);
        SpnGenero.setAdapter(GeneroAdapter);
        SpnRegisterBloodType.setAdapter(BloodAdapter);
        SpnRegistroBloodRh.setAdapter(RhAdapter);

        //endregion

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                finish();
            }
        });

    }
}
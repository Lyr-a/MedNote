package com.example.mednote.logi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mednote.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //region Views

        //TextView TvData
        EditText EtCpf = findViewById(R.id.EtRegisterCpf);
        EditText EtNome = findViewById(R.id.EtRegisterName);
        //EditText EtData = findViewById(R.id.EtRegisterData);
        EditText EtSenha = findViewById(R.id.EtRegisterPassword);
        EditText EtSenha2 = findViewById(R.id.EtRegisterPasswordRpt);
        EditText EtNumero = findViewById(R.id.EtRegisterNumber);

        Button BtnRegister = findViewById(R.id.BtnRegister);
        Spinner SpnGenero = findViewById(R.id.SpnRegisterGender);
        Spinner SpnRegisterBloodType = findViewById(R.id.SpnRegisterBloodType);

        //endregion

        //region Spinners

        ArrayAdapter<CharSequence> GeneroAdapter = ArrayAdapter.createFromResource(this, R.array.generos,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> BloodAdapter = ArrayAdapter.createFromResource(this, R.array.tipos_sanguineos,
                android.R.layout.simple_spinner_item);
        SpnGenero.setAdapter(GeneroAdapter);
        SpnRegisterBloodType.setAdapter(BloodAdapter);

        //endregion

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cpf, Nome, Data, Senha, Senha2, Numero, Genero, Sangue;

                //region FILTRO

                Genero = SpnGenero.getSelectedItem().toString();
                Sangue = SpnRegisterBloodType.getSelectedItem().toString();
                Cpf = EtCpf.getText().toString();
                Nome = EtNome.getText().toString();
                //Data = EtNome.getText().toString();
                Senha = EtSenha.getText().toString();
                Senha2 = EtSenha2.getText().toString();
                Numero = EtNumero.getText().toString();


                if (Genero.equals("Genero…")) {

                    Toast.makeText(RegisterActivity.this, "Selecione um gênero", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (Sangue.equals("Sangue…")) {

                    Toast.makeText(RegisterActivity.this, "Selecione um tipo sanguíneo", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (Cpf.isEmpty() || Nome.isEmpty() || Senha.isEmpty() || Senha2.isEmpty() || Numero.isEmpty()){

                    Toast.makeText(RegisterActivity.this, "insira todos os dados", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (!Senha.equals(Senha2)){

                    Toast.makeText(RegisterActivity.this, "As senhas não batem", Toast.LENGTH_SHORT).show();
                    return;

                }



                //endregion

                Intent intent = new Intent();

                finish();
            }
        });

    }
}
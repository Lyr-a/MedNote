package com.example.mednote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    static int RESULT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //region VIEWS

        Button BtnLogin = findViewById(R.id.BtnLogin);
        Button BtnLoginRegister = findViewById(R.id.BtnLoginRegister);
        EditText EtLoginCpf = findViewById(R.id.EtLoginCpf);
        EditText EtLoginPassword = findViewById(R.id.EtLoginPassword);

        //endregion

        //region BOTÕES

        BtnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(registro, RESULT);
            }
        });
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Cpf, Pass;

                Cpf = EtLoginCpf.getText().toString();
                Pass = EtLoginPassword.getText().toString();

                if (Cpf.equals("1234") || Pass.equals("adm")) {
                    int L = 1;
                    Intent login = new Intent();
                    login.putExtra("CPF", Cpf);
                    login.putExtra("Pass", Pass);
                    login.putExtra("Login", L);
                    setResult(Activity.RESULT_OK);
                    finish();

                }
                else{
                    Toast.makeText(LoginActivity.this, "Digite as credenciais corretas", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //endregion

    }
}
package com.example.mednote.logi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mednote.HttpRequest;
import com.example.mednote.R;
import com.example.mednote.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String Dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //region Views

        EditText EtCpf = findViewById(R.id.EtRegisterCpf);
        EditText EtNome = findViewById(R.id.EtRegisterName);
        //EditText EtData = findViewById(R.id.EtRegisterData);
        EditText EtSenha = findViewById(R.id.EtRegisterPassword);
        EditText EtSenha2 = findViewById(R.id.EtRegisterPasswordRpt);
        EditText EtNumero = findViewById(R.id.EtRegisterNumber);

        TextView TvData = findViewById(R.id.TvData);
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

        TvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDateDialog();
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cpf, Nome, Senha, Senha2, Numero, Genero, Sangue;

                //region FILTRO

                Genero = SpnGenero.getSelectedItem().toString();
                Sangue = SpnRegisterBloodType.getSelectedItem().toString();
                Cpf = EtCpf.getText().toString();
                Nome = EtNome.getText().toString();
                Senha = EtSenha.getText().toString();
                Senha2 = EtSenha2.getText().toString();
                Numero = EtNumero.getText().toString();


                if (Dat.isEmpty()){

                    Toast.makeText(RegisterActivity.this, "Insira uma data de aniversário clicando no botão", Toast.LENGTH_SHORT).show();
                    return;

                }

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

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "create_pessoa.php", "POST", "UTF-8");

                        httpRequest.addParam("cpf", Cpf);
                        httpRequest.addParam("senha", Senha);
                        httpRequest.addParam("nome", Nome);
                        httpRequest.addParam("genero", Genero);
                        httpRequest.addParam("tipo_sang", Sangue);
                        httpRequest.addParam("data_nasc", Dat);
                        httpRequest.addParam("num_emer", Numero);

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if(success == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Registro realizado com sucesso", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(i);
                                    }
                                });
                            }
                            else {
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show();
                                    }

                                });
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }

    public void CreateDateDialog () {

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        TextView TvData = findViewById(R.id.TvData);
        if (month<10){
            String Mon = "0" + month;
            Dat = dayOfMonth + "/" + Mon + "/" + year;
        }
        else {
            Dat = dayOfMonth + "/" + month + "/" + year;
        }
        TvData.setText(Dat);
    }
}
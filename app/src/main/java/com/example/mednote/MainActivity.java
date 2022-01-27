package com.example.mednote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mednote.logi.Config;
import com.example.mednote.recvi.SintomasItem;
import com.example.mednote.recvi.TratamentoItem;
import com.example.mednote.sinto.SintomasFragment;
import com.example.mednote.trat.TratamentoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ArrayList<SintomasItem> sintomas = new ArrayList<>();
    ArrayList<TratamentoItem> tratament = new ArrayList<>();
    ArrayList<String> user = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.FcvMain, new SintomasFragment()).commit();

        //region VIEWS

        bottomNavigationView = findViewById(R.id.BtmnavMain);
        Toolbar TbMain = findViewById(R.id.TbMain);

        //endregion

        //region INFO

        //sintomas

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_all_sintomas.php", "GET", "UTF-8");
                httpRequest.setBasicAuth( Config.getLogin(MainActivity.this), Config.getPassword(MainActivity.this));
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");

                    if(success == 1) {


                        JSONArray jsonArray = jsonObject.getJSONArray("Sintoma");
                        //Log.e("AAAAAAAAAAAAAAAAAAAAAAA", String.valueOf(jsonArray.length()));

                        //int i = 0; i < jsonArray.length();i++

                        for (int i = jsonArray.length()-1; i > - 1 ;i--){
                            String titulo, desc, data, hora = "";
                            SintomasItem novoSintoma = new SintomasItem();
                            JSONObject sintoma = jsonArray.getJSONObject(i);

                            titulo = sintoma.getString("sintoma_title");
                            desc = sintoma.getString("sintoma_desc");
                            data = sintoma.getString("sintoma_data");
                            hora = sintoma.getString("sintoma_hora");

                            novoSintoma.Title = titulo;
                            novoSintoma.Desc = desc;
                            novoSintoma.Data = data;
                            novoSintoma.Hora = hora;

                            sintomas.add(novoSintoma);

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //textView.setText(teste);

                            }
                        });
                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //tratamento

        ExecutorService executorServic = Executors.newSingleThreadExecutor();
        executorServic.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_all_tratamentos.php", "GET", "UTF-8");
                httpRequest.setBasicAuth( Config.getLogin(MainActivity.this), Config.getPassword(MainActivity.this));

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");

                    if(success == 1) {


                        JSONArray jsonArray = jsonObject.getJSONArray("Tratamento");

                        for (int i = jsonArray.length()-1; i > - 1 ;i--){

                            ArrayList<String> photos = new ArrayList<>();
                            String titulo, desc, data, hora = "";
                            TratamentoItem novoTratamento = new TratamentoItem();
                            JSONObject tratamento = jsonArray.getJSONObject(i);

                            titulo = tratamento.getString("tratamento_title");
                            desc = tratamento.getString("tratamento_desc");
                            data = tratamento.getString("tratamento_data");
                            hora = tratamento.getString("tratamento_hora");

                            titulo.trim();
                            desc.trim();

                            novoTratamento.Title = titulo;
                            novoTratamento.Desc = desc;
                            novoTratamento.Data = data;
                            novoTratamento.Hora = hora;
                            novoTratamento.Photos = photos;

                            tratament.add(novoTratamento);

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //textView.setText(teste);

                            }
                        });
                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //endregion

        //region USER

        ExecutorService executorServi = Executors.newSingleThreadExecutor();
        executorServi.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_pessoa_details.php", "GET", "UTF-8");
                httpRequest.setBasicAuth(Config.getLogin(MainActivity.this), Config.getPassword(MainActivity.this));

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Pessoa");
                        Log.e("AAAAAAAAAAAAAAAAAAA", String.valueOf(jsonArray));
                        JSONObject usuario = jsonArray.getJSONObject(0);

                        user.add(usuario.getString("cpf"));
                        user.add(usuario.getString("nome"));
                        user.add(usuario.getString("genero"));
                        user.add(usuario.getString("tipo_sang"));
                        user.add(usuario.getString("data_nasc"));
                        user.add(usuario.getString("num_emer"));

                        Log.e("oiii", String.valueOf(user));

                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                            }

                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //endregion

        setSupportActionBar(TbMain);

        //region BOTTOMNAV
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch(item.getItemId()){
                    case R.id.Item1:
                        fragment = new SintomasFragment();
                        break;
                    case R.id.Item2:
                        fragment = new TratamentoFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FcvMain, fragment).commit();
                return true;
            }
        });
        //endregion

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.OpShare){
            ShareDialog();

            //shareGmail();

        }
        if (id == R.id.OpSair){
            Config.setLogin(MainActivity.this , "");
            Config.setPassword(MainActivity.this , "");
            Intent ponte = new Intent(MainActivity.this , PonteActivity.class);
            startActivity(ponte);

        }
        return super.onOptionsItemSelected(item);
    }

    private void shareGmail(ArrayList<String> Infor, String text) {

        //Ação de Enviar o Gmail
        Intent i = new Intent(Intent.ACTION_SENDTO);

        String Dia = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        i.setData(Uri.parse("mailto:"));

        //Acoplando os dados ao intent
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{Infor.get(4)});
        i.putExtra(Intent.EXTRA_SUBJECT, "RELATÓRIO MEDNOTE " + Dia);
        i.putExtra(Intent.EXTRA_TEXT, text);
        //i.putExtra(Intent.EXTRA_STREAM, )

        //Testa se o usuário possui algum app para gmail
        try {
            //ativa o intent
            startActivity(i);

        }
        //Exibe mensagem de erro caso não tenha
        catch (ActivityNotFoundException e){

            Toast.makeText(MainActivity.this, "Não há nenhuma app de gmail instalada", Toast.LENGTH_SHORT);

        }
    }

    private void ShareDialog() {

        ArrayList<String> DiaInfo = new ArrayList<>();

        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_share);

        //region VIEWS

        Button BtnShare= dialog.findViewById(R.id.BtnShare);
        CheckBox CbSin = dialog.findViewById(R.id.CbSintoma);
        CheckBox CbTra = dialog.findViewById(R.id.CbTratamento);
        Spinner SpnSin = dialog.findViewById(R.id.SpnSintoma);
        Spinner SpnTra = dialog.findViewById(R.id.SpnTratamento);
        Spinner SpnMed = dialog.findViewById(R.id.SpnMed);

        //endregion

        //region SPINNER

        ArrayAdapter<CharSequence> MedAdapter = ArrayAdapter.createFromResource(this,
                R.array.medicos, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> TempoAdapter = ArrayAdapter.createFromResource(this,
                R.array.tempo, android.R.layout.simple_spinner_item);

        SpnMed.setAdapter(MedAdapter);
        SpnSin.setAdapter(TempoAdapter);
        SpnTra.setAdapter(TempoAdapter);

        //endregion

        BtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Medico = SpnMed.getSelectedItem().toString();
                String TemSin = SpnSin.getSelectedItem().toString();
                String TemTra = SpnTra.getSelectedItem().toString();

                if (Medico.equals("Gmail…")){
                    Toast.makeText(MainActivity.this, "insira um endereço de gmail", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(CbSin.isChecked() || CbTra.isChecked())){
                    Toast.makeText(MainActivity.this, "selecione algo para compartilhar", Toast.LENGTH_SHORT).show();
                    return;
                }

                else{
                    DiaInfo.add(TemSin);
                    DiaInfo.add(TemTra);
                    DiaInfo.add("0");
                    DiaInfo.add("0");
                    DiaInfo.add(Medico);

                    if(CbSin.isChecked()){
                        DiaInfo.set(2,"1");
                    }
                    if(CbTra.isChecked()){
                        DiaInfo.set(3,"1");
                    }

                    dialog.dismiss();

                    createText(DiaInfo);

                }
            }
        });

        dialog.show();
    }

    private void createText(ArrayList<String> diaInfo) {

        String texto = "";

        //region user

        texto +=   "*******************************************************************";
        texto += "\n*                                Usuário";
        texto += "\n*******************************************************************\n";
        texto +=  "* \n* Paciente: " + user.get(1);
        texto +=  "\n* CPF: " + user.get(0);
        texto +=  "\n* Genêro: " + user.get(2);
        texto +=  "\n* Tipo sanguíneo: " + user.get(3);
        texto +=  "\n* Data de nascimento: " + user.get(4);
        texto +=  "\n* Número de emergência: " + user.get(5);
        texto += "\n*";

        //endregion

        //region sintoma

        if (diaInfo.get(2).equals("1")){

            texto += "\n*******************************************************************";
            texto += "\n*                                Sintomas";
            for (int i = 0 ; sintomas.size() > i ; i++){

                String sin, title, desc;
                title = sintomas.get(i).Title;
                desc = sintomas.get(i).Desc;
                sin = "\n*******************************************************************";
                sin += "\n* Título: " + title;
                sin += "\n* Descrição: " + desc;
                texto += sin;

            }
            texto += "\n*";
        }

        //endregion

        //region tratamento

        if (diaInfo.get(3).equals("1")){

            texto +=   "\n*******************************************************************";
            texto += "\n*                                Tratamentos";
            for (int i = 0 ; tratament.size() > i ; i++){

                String tra, title, desc;
                title = tratament.get(i).Title;
                desc = tratament.get(i).Desc;
                tra = "\n*******************************************************************";
                tra += "\n* Título: " + title;
                tra += "\n* Descrição: " + desc;
                texto += tra;

            }
            texto +=   "\n*******************************************************************";

        }

        //endregion

        //Log.e("AAAAAAAAAAAAAAAAAAAAAAAAA", texto);

        shareGmail(diaInfo, texto);

    }
}
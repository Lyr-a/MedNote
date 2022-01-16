package com.example.mednote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    boolean Log = false;
    int Lo = 0;

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


    private void shareGmail(ArrayList<String> Infor) {
        //Ação de Enviar o Gmail
        Intent i = new Intent(Intent.ACTION_SENDTO);

        i.setData(Uri.parse("mailto:"));

        //Acoplando os dados ao intent
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{Infor.get(4)});
        i.putExtra(Intent.EXTRA_SUBJECT, "RELATÓRIO MEDNOTE");
        i.putExtra(Intent.EXTRA_TEXT, "Segue em anexo");

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
                    ArrayList<TratamentoItem> Tra = new ArrayList<>();
                    ArrayList<SintomasItem> Sin = new ArrayList<>();
                    CreatePdf(DiaInfo, Tra, Sin);



                }
            }
        });

        dialog.show();
    }

    private void CreatePdf(ArrayList<String> diaInfo, ArrayList<TratamentoItem> Tra, ArrayList<SintomasItem> Sin){

        //region TAMANHO

        int tamanho = 1;

        if (diaInfo.get(2).equals("1")){
            for (int i = 0; i <= Sin.size(); i+=4){
                tamanho++;
            }
        }
        if (diaInfo.get(3).equals("1")){
            for (int i = 0; i <= Tra.size(); i+=4){
                tamanho++;
            }
        }

        //endregion

        PdfDocument pdf = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(400,600,tamanho).create();
        //PdfDocument.Page


    }

}
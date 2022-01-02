package com.example.mednote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mednote.logi.Config;
import com.example.mednote.logi.LoginActivity;
import com.example.mednote.sinto.SintomasFragment;
import com.example.mednote.trat.TratamentoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
            shareGmail();

        }
        if (id == R.id.OpSair){
            Config.setLogin(MainActivity.this , "");
            Config.setPassword(MainActivity.this , "");
            Intent ponte = new Intent(MainActivity.this , PonteActivity.class);
            startActivity(ponte);

        }
        return super.onOptionsItemSelected(item);
    }

    private void shareGmail() {
        //Ação de Enviar o Gmail
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));

        //Acoplando os dados ao intent
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
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



}
package com.example.mednote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

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

        //endregion



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



}
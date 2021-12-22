package com.example.mednote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mednote.logi.Config;
import com.example.mednote.logi.LoginActivity;

public class PonteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Config.getLogin(PonteActivity.this).isEmpty()) {
            Intent i = new Intent(PonteActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        else {
            Intent i = new Intent(PonteActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
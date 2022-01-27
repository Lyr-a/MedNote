package com.example.mednote;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mednote.logi.Config;
import com.example.mednote.logi.LoginActivity;
import com.example.mednote.recvi.SintomasItem;
import com.example.mednote.recvi.TratamentoItem;
import com.example.mednote.sinto.SintomasFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<SintomasItem>> sintomas;
    MutableLiveData<List<TratamentoItem>> tratamentos;

    public LiveData<List<SintomasItem>> getSintomas(){
        if(sintomas == null){
            sintomas = new MutableLiveData<List<SintomasItem>>();
            loadSintomas();
        }

        return sintomas;
    }


    public LiveData<List<TratamentoItem>> getTratamentos(){
        if(tratamentos == null){
        tratamentos = new MutableLiveData<List<TratamentoItem>>();
        loadTratamentos();
    }

        return tratamentos;
    }


    void loadTratamentos() {



    }

    void loadSintomas() {

        /*
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_sintoma_details.php", "POST", "UTF-8");
                //httpRequest.addParam(Config.getLogin());
                httpRequest.setBasicAuth(Config.getLogin());
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {

                       // Toast.makeText(this, "aaaaaa", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        final String error = jsonObject.getString("error");

                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        */

    }
}

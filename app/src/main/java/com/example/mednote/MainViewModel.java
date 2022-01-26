package com.example.mednote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mednote.recvi.SintomasItem;
import com.example.mednote.recvi.TratamentoItem;

import java.util.List;

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



    }
}

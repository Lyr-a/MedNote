package com.example.mednote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TratamentoAdapter extends RecyclerView.Adapter {

    TratamentoFragment tratamentoFragment;
    List<TratamentoItem> TraItens;

    public TratamentoAdapter(TratamentoFragment tratamentoFragment,List<TratamentoItem> TraItens){
        this.tratamentoFragment = tratamentoFragment;
        this.TraItens = TraItens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater TraInflater;
        TraInflater = LayoutInflater.from(tratamentoFragment);
        View v = TraInflater.inflate(R.layout.item_tratamento_list, parent, false);
        return new TratamentoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

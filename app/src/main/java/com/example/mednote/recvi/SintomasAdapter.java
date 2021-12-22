package com.example.mednote.recvi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mednote.R;
import com.example.mednote.sinto.SintomaViewHolder;
import com.example.mednote.sinto.SintomasFragment;

import java.util.List;

public class SintomasAdapter extends RecyclerView.Adapter{

    SintomasFragment sintomasFragment;
    List<TratamentoItem> SinItens;

    public SintomasAdapter(SintomasFragment sintomasFragment, List<SintomasItem> sinItens) {
        this.sintomasFragment = sintomasFragment;
        this.SinItens = SinItens;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater SinInflater;
        SinInflater = LayoutInflater.from(sintomasFragment.getContext());
        View v = SinInflater.inflate(R.layout.item_tratamento_list, parent, false);
        return new SintomaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TratamentoItem tratamentoItem = SinItens.get(position);
        View v = holder.itemView;
        TextView Title = v.findViewById(R.id.TvSintomaTitle);
        TextView Desc  = v.findViewById(R.id.TvSintomaDesc);
        Title.setText(tratamentoItem.Title);
        Desc.setText(tratamentoItem.Desc);
    }

    @Override
    public int getItemCount() {
        return SinItens.size();
    }
}

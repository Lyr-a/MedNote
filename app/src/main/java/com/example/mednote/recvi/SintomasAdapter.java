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
    List<SintomasItem> SinItens;

    public SintomasAdapter(SintomasFragment sintomasFragment, List<SintomasItem> SinItens) {
        this.sintomasFragment = sintomasFragment;
        this.SinItens = SinItens;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater SinInflater;
        SinInflater = LayoutInflater.from(sintomasFragment.getContext());
        View v = SinInflater.inflate(R.layout.item_sintoma_list, parent, false);
        return new SintomaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SintomasItem sintomasItem = SinItens.get(position);
        View v = holder.itemView;
        TextView Title = v.findViewById(R.id.TvSinTitle);
        TextView Desc  = v.findViewById(R.id.TvSinDesc);
        Title.setText(sintomasItem.Title);
        Desc.setText(sintomasItem.Desc);
    }

    @Override
    public int getItemCount() {
        return SinItens.size();
    }
}
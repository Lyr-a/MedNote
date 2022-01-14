package com.example.mednote.recvi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mednote.R;
import com.example.mednote.trat.TratamentoZoomActivity;

import java.util.List;

public class TratamentoZoomAdapter extends RecyclerView.Adapter{


    //rodar em torno da lista

    TratamentoZoomActivity tratamentoZoomActivity;
    List<TratamentoZoomItem> TrzItens;


    public TratamentoZoomAdapter(TratamentoZoomActivity tratamentoZoomAdapter, List<TratamentoZoomItem> TrzItens) {
        this.TrzItens = TrzItens;
        this.tratamentoZoomActivity = tratamentoZoomAdapter;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater TrzInflater;
        TrzInflater = LayoutInflater.from(tratamentoZoomActivity);
        View v = TrzInflater.inflate(R.layout.item_zoom_trat_list, parent, false);
        return new TratamentoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TratamentoZoomItem tratamentoZoomItem = TrzItens.get(position);
        View v = holder.itemView;
        ImageView imageView = v.findViewById(R.id.ImvTratZoom);
        imageView.setImageURI(tratamentoZoomItem.photo);
    }

    @Override
    public int getItemCount() {
        return TrzItens.size() - 1;
    }
}

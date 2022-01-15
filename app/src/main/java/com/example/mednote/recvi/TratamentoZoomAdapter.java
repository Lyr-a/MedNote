package com.example.mednote.recvi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mednote.R;
import com.example.mednote.trat.TratamentoZoomActivity;

import java.util.ArrayList;

public class TratamentoZoomAdapter extends RecyclerView.Adapter {

    TratamentoZoomActivity tratamentoZoomActivity;
    ArrayList<TratamentoZoomItem> TzItens;

    public TratamentoZoomAdapter(TratamentoZoomActivity tratamentoZoomActivity, ArrayList<TratamentoZoomItem> TzItens){

        this.tratamentoZoomActivity = tratamentoZoomActivity;
        this.TzItens = TzItens;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater TzInflater;
        TzInflater = LayoutInflater.from(tratamentoZoomActivity);
        View v = TzInflater.inflate(R.layout.item_zoom_trat_list, parent, false);
        return new TratamentoZoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TratamentoZoomItem tratamentoZoomItem = TzItens.get(position);
        View v = holder.itemView;
        ImageView imageView = v.findViewById(R.id.ImvTratZoom);
        imageView.setImageURI(tratamentoZoomItem.Photo);
    }

    @Override
    public int getItemCount() {
        return TzItens.size();
    }
}

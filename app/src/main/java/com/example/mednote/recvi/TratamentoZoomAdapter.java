package com.example.mednote.recvi;

import android.app.Dialog;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog(tratamentoZoomItem.Photo, v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return TzItens.size();
    }


    private void ShareDialog(Uri foto, View v) {

        Dialog dialog = new Dialog(v.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_photo);

        ImageView Imv = dialog.findViewById(R.id.ImvDialog);

        Imv.setImageURI(foto);

        dialog.show();

    }
}

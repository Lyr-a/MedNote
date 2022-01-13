package com.example.mednote.trat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mednote.R;
import com.example.mednote.recvi.TratamentoAdapter;
import com.example.mednote.recvi.TratamentoItem;
import com.example.mednote.sinto.SintomasAddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TratamentoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TratamentoFragment extends Fragment {


    static int NEW_ITEM_REQUEST = 1;
    List<TratamentoItem> TraItens = new ArrayList<>();
    TratamentoAdapter tratamentoAdapter;

    //region BARULHO
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TratamentoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TratamentoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TratamentoFragment newInstance(String param1, String param2) {
        TratamentoFragment fragment = new TratamentoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    //endregion

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_tratamento, container, false);

        FloatingActionButton FbtnTratamentoCreate = v.findViewById(R.id.FbtnTratamentoCreate);



        //endregion

        //region RECYCLER VIEW

        tratamentoAdapter = new TratamentoAdapter(this, TraItens);

        RecyclerView RvTratamento = v.findViewById(R.id.RvTratamento);
        RvTratamento.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RvTratamento.setLayoutManager(layoutManager);
        RvTratamento.setAdapter(tratamentoAdapter);

        //endregion

        //region DADOS FANTASIA

        TratamentoItem novoTratamento1 = new TratamentoItem();
        novoTratamento1.Title = "TRATAMENTO 1";
        novoTratamento1.Desc = "DESCRIÇÃO 1";
        TraItens.add(novoTratamento1);
        TratamentoItem novoTratamento2 = new TratamentoItem();
        novoTratamento2.Title = "TRATAMENTO 2";
        novoTratamento2.Desc = "DESCRIÇÃO 2";
        TraItens.add(novoTratamento2);
        TratamentoItem novoTratamento3 = new TratamentoItem();
        novoTratamento3.Title = "TRATAMENTO 3";
        novoTratamento3.Desc = "DESCRIÇÃO 3";
        TraItens.add(novoTratamento3);
        TratamentoItem novoTratamento4 = new TratamentoItem();
        novoTratamento4.Title = "TRATAMENTO 4";
        novoTratamento4.Desc = "DESCRIÇÃO 4";
        TraItens.add(novoTratamento4);
        TratamentoItem novoTratamento5 = new TratamentoItem();
        novoTratamento5.Title = "TRATAMENTO 5";
        novoTratamento5.Desc = "DESCRIÇÃO 5";
        TraItens.add(novoTratamento5);
        TratamentoItem novoTratamento6 = new TratamentoItem();
        novoTratamento6.Title = "TRATAMENTO 6";
        novoTratamento6.Desc = "DESCRIÇÃO 6";
        TraItens.add(novoTratamento6);
        TratamentoItem novoTratamento7 = new TratamentoItem();
        novoTratamento7.Title = "TRATAMENTO 7";
        novoTratamento7.Desc = "DESCRIÇÃO 7";
        TraItens.add(novoTratamento7);

        //endregion

        FbtnTratamentoCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TratamentoAddActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST){
            if (resultCode == Activity.RESULT_OK){
                if (data != null) {
                    String TTitle = data.getStringExtra("TraTitle");
                    String TDesc = data.getStringExtra("TraDesc");
                    String TDia = data.getStringExtra("TraDia");
                    String THora = data.getStringExtra("TraHora");
                    ArrayList<String> TPhotos = data.getStringArrayListExtra("TraPhotos");

                    TratamentoItem novoTratamento = new TratamentoItem();

                    novoTratamento.Title = TTitle;
                    novoTratamento.Desc = TDesc;
                    novoTratamento.Data = TDia;
                    novoTratamento.Hora = THora;
                    novoTratamento.Photos = TPhotos;

                    TraItens.add(novoTratamento);

                    tratamentoAdapter.notifyItemInserted(TraItens.size()-1);
                }
            }
        }
    }
}
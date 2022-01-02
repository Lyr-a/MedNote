package com.example.mednote.sinto;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mednote.PonteActivity;
import com.example.mednote.R;
import com.example.mednote.logi.Config;
import com.example.mednote.recvi.SintomasAdapter;
import com.example.mednote.recvi.SintomasItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SintomasFragment extends Fragment {


    static int NEW_ITEM_REQUEST = 1;
    List<SintomasItem> SinItens = new ArrayList<>();
    SintomasAdapter sintomasAdapter;;
    private static final int CREATEPDF = 1;

    //region BARULO

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SintomasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SintomasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SintomasFragment newInstance(String param1, String param2) {
        SintomasFragment fragment = new SintomasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sintomas, container, false);

        FloatingActionButton BtnNewSintoma = v.findViewById(R.id.FbtnSintomasCreate);

        /*
        Toolbar toolbar = v.findViewById(R.id.TbMain);
        setSupportActionBar(toolbar);
        //Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //endregion

         */

        //region RECYCLER VIEW
        sintomasAdapter = new SintomasAdapter(this, SinItens);

        RecyclerView RvSintomas = v.findViewById(R.id.RvSintomas);
        RvSintomas.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RvSintomas.setLayoutManager(layoutManager);
        RvSintomas.setAdapter(sintomasAdapter);

        //endregion

        //region DADOS FANTASIA

        SintomasItem novoSintoma1 = new SintomasItem();
        novoSintoma1.Title = "SINTOMA 1";
        novoSintoma1.Desc = "DESCRIÇÃO 1";
        SinItens.add(novoSintoma1);
        SintomasItem novoSintoma2 = new SintomasItem();
        novoSintoma2.Title = "SINTOMA 2";
        novoSintoma2.Desc = "DESCRIÇÃO 2";
        SinItens.add(novoSintoma2);
        SintomasItem novoSintoma3 = new SintomasItem();
        novoSintoma3.Title = "SINTOMA 3";
        novoSintoma3.Desc = "DESCRIÇÃO 3";
        SinItens.add(novoSintoma3);
        SintomasItem novoSintoma4 = new SintomasItem();
        novoSintoma4.Title = "SINTOMA 4";
        novoSintoma4.Desc = "DESCRIÇÃO 4";
        SinItens.add(novoSintoma4);
        SintomasItem novoSintoma5 = new SintomasItem();
        novoSintoma5.Title = "SINTOMA 5";
        novoSintoma5.Desc = "DESCRIÇÃO 5";
        SinItens.add(novoSintoma5);
        SintomasItem novoSintoma6 = new SintomasItem();
        novoSintoma6.Title = "SINTOMA 6";
        novoSintoma6.Desc = "DESCRIÇÃO 6";
        SinItens.add(novoSintoma6);
        SintomasItem novoSintoma7 = new SintomasItem();
        novoSintoma7.Title = "SINTOMA 7";
        novoSintoma7.Desc = "DESCRIÇÃO 7";
        SinItens.add(novoSintoma7);

        //endregion

        BtnNewSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SintomasAddActivity.class);
                startActivityForResult(intent, NEW_ITEM_REQUEST);

            }
        });

        return v;

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        { super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == NEW_ITEM_REQUEST) {
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {

                        String STitle = data.getStringExtra("SinTitle");
                        String SDesc = data.getStringExtra("SinDesc");
                        String SDia = data.getStringExtra("SinDia");
                        String SHora = data.getStringExtra("SinHora");

                        SintomasItem novoSintoma = new SintomasItem();

                        novoSintoma.Title = STitle;
                        novoSintoma.Desc = SDesc;
                        novoSintoma.Data = SDia;
                        novoSintoma.Hora = SHora;

                        SinItens.add(novoSintoma);

                        sintomasAdapter.notifyItemInserted(SinItens.size()-1);
                    }
                }
            }
        }
    }
}
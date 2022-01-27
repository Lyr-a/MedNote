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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mednote.HttpRequest;
import com.example.mednote.R;
import com.example.mednote.Util;
import com.example.mednote.logi.Config;
import com.example.mednote.recvi.TratamentoAdapter;
import com.example.mednote.recvi.TratamentoItem;
import com.example.mednote.sinto.SintomasAddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TratamentoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TratamentoFragment extends Fragment {


    static int NEW_ITEM_REQUEST = 1;
    public List<TratamentoItem> TraItens = new ArrayList<>();
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


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_all_tratamentos.php", "GET", "UTF-8");
                httpRequest.setBasicAuth( Config.getLogin(getContext()), Config.getPassword(getContext()));

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");

                    if(getActivity() == null){
                        return;
                    }
                    if(success == 1) {


                        JSONArray jsonArray = jsonObject.getJSONArray("Tratamento");

                        for (int i = jsonArray.length()-1; i > - 1 ;i--){

                            ArrayList<String> photos = new ArrayList<>();
                            String titulo, desc, data, hora = "";
                            TratamentoItem novoTratamento = new TratamentoItem();
                            JSONObject tratamento = jsonArray.getJSONObject(i);

                            titulo = tratamento.getString("tratamento_title");
                            desc = tratamento.getString("tratamento_desc");
                            data = tratamento.getString("tratamento_data");
                            hora = tratamento.getString("tratamento_hora");

                            novoTratamento.Title = titulo;
                            novoTratamento.Desc = desc;
                            novoTratamento.Data = data;
                            novoTratamento.Hora = hora;
                            novoTratamento.Photos = photos;

                            TraItens.add(novoTratamento);

                            tratamentoAdapter.notifyItemInserted(TraItens.size()-1);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //textView.setText(teste);

                            }
                        });
                    }
                    else {
                        final String error = jsonObject.getString("error");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                            }

                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //endregion

        FbtnTratamentoCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TratamentoAddActivity.class);
                startActivity(i);
            }
        });

        return v;
    }


}
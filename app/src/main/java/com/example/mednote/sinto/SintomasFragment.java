package com.example.mednote.sinto;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mednote.HttpRequest;
import com.example.mednote.R;
import com.example.mednote.Util;
import com.example.mednote.logi.Config;
import com.example.mednote.recvi.SintomasAdapter;
import com.example.mednote.recvi.SintomasItem;
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

public class SintomasFragment extends Fragment {


    static int NEW_ITEM_REQUEST = 1;
    public List<SintomasItem> SinItens = new ArrayList<>();
    SintomasAdapter sintomasAdapter;
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

        //login

        FloatingActionButton BtnNewSintoma = v.findViewById(R.id.FbtnSintomasCreate);


        //region RECYCLER VIEW
        sintomasAdapter = new SintomasAdapter(this, SinItens);
        RecyclerView RvSintomas = v.findViewById(R.id.RvSintomaFragment);
        TextView textView = v.findViewById(R.id.tvTeste);
        RvSintomas.setHasFixedSize(true);

        /*
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        LiveData<List<SintomasItem>> sintomas = mainViewModel.getSintomas();

        sintomas.observe(getViewLifecycleOwner(), new Observer<List<SintomasItem>>() {
            @Override
            public void onChanged(List<SintomasItem> sintomasItems) {

            }
        });

 */

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RvSintomas.setLayoutManager(layoutManager);
        RvSintomas.setAdapter(sintomasAdapter);

        //endregion
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_all_sintomas.php", "GET", "UTF-8");
                httpRequest.setBasicAuth( Config.getLogin(getContext()), Config.getPassword(getContext()));

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    //JSONArray jsonArray = new JSONArray(result);
                    final int success = jsonObject.getInt("success");

                    if(getActivity() == null){
                        return;
                    }
                    if(success == 1) {


                        JSONArray jsonArray = jsonObject.getJSONArray("Sintoma");
                        Log.e("AAAAAAAAAAAAAAAAAAAAAAA", String.valueOf(jsonArray.length()));

                        //int i = 0; i < jsonArray.length();i++

                        for (int i = jsonArray.length()-1; i > - 1 ;i--){
                            String titulo, desc, data, hora = "";
                            SintomasItem novoSintoma = new SintomasItem();
                            JSONObject sintoma = jsonArray.getJSONObject(i);

                            titulo = sintoma.getString("sintoma_title");
                            desc = sintoma.getString("sintoma_desc");
                            data = sintoma.getString("sintoma_data");
                            hora = sintoma.getString("sintoma_hora");

                            novoSintoma.Title = titulo;
                            novoSintoma.Desc = desc;
                            novoSintoma.Data = data;
                            novoSintoma.Hora = hora;

                            SinItens.add(novoSintoma);

                            sintomasAdapter.notifyItemInserted(SinItens.size()-1);
                        }
                        /*
                        JSONArray jsonArray = jsonObject.getJSONArray("Sintoma");
                        Log.e("AAAAAAAAAAAAAAAAAAAAAAA", String.valueOf(jsonArray));
                        JSONObject sintoma = jsonArray.getJSONObject(0);
                        Log.e("AAAAAAAAAAAAAAAAAAAAAAA", String.valueOf(sintoma));
                        String titulo = sintoma.getString("sintoma_title");
                        Log.e("AAAAAAAAAAAAAAAAAAAAAAA", titulo);
                        String desc = sintoma.getString("id_sintoma");
                        Log.e("AAAAAAAAAAAAAAAAAAAAAAA", desc);
                        SintomasItem novoSintoma1 = new SintomasItem();
                        novoSintoma1.Title = titulo;
                        novoSintoma1.Desc = desc;
                        SinItens.add(novoSintoma1);
                        sintomasAdapter.notifyItemInserted(SinItens.size()-1);
                         */

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

                /*
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

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


                 */
            }
        });




        /*



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


         */



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

                        Uri Suri = data.getData();
                        String STitle = data.getStringExtra("SinTitle");
                        String SDesc = data.getStringExtra("SinDesc");
                        String SDia = data.getStringExtra("SinDia");
                        String SHora = data.getStringExtra("SinHora");

                        SintomasItem novoSintoma = new SintomasItem();

                        novoSintoma.photo = Suri;
                        novoSintoma.Title = STitle;
                        novoSintoma.Desc = SDesc;
                        novoSintoma.Data = SDia;
                        novoSintoma.Hora = SHora;

                        SinItens.add(0, novoSintoma);

                        sintomasAdapter.notifyItemInserted(0);
                    }
                }
            }
        }
    }
}
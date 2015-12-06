package com.moonfish.testeleccionesgenerales2015.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.activities.MainActivity;
import com.moonfish.testeleccionesgenerales2015.activities.ResultadosActivity;
import com.moonfish.testeleccionesgenerales2015.adapters.MyRecyclerViewAdapter;
import com.moonfish.testeleccionesgenerales2015.model.Title;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 06/12/2015.
 */
public class ResultadosDetallados extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Object> items = new ArrayList<>();

    public ResultadosDetallados() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resultados_detallados, container, false);
        // Inflate the layout for this fragment
        //RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        addItems();
        return v;
    }

    public void addItems(){

        if(items.size()>0) items.clear();

        if(ResultadosActivity.num_preguntas<20){
            items.add("MENSAJE");
        }
        else{
            items.add(new Title("ECONOMÍA"));
            items.add("GRAFICO_ECONOMIA");
            items.add(new Title("SOCIEDAD"));
            items.add("GRAFICO_SOCIEDAD");
            items.add(new Title("ESTADO"));
            items.add("GRAFICO_ESTADO");

        }


        mAdapter = new MyRecyclerViewAdapter(getActivity(),items);
        mRecyclerView.setAdapter(mAdapter);
    }

}
package com.moonfish.testeleccionesgenerales2015.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Jesus on 06/12/2015.
 */
public class ResultadosDetallados extends Fragment {

    private PieChart pieChart;

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
        pieChart = (PieChart) v.findViewById(R.id.chart);
        //configureChart();
        return v;
    }

}
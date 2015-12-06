package com.moonfish.testeleccionesgenerales2015.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Jesus on 05/12/2015.
 */
public class EstadisticasEncuestas extends Fragment {

    public EstadisticasEncuestas() {
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
        return inflater.inflate(R.layout.fragment_encuestas, container, false);
    }

}

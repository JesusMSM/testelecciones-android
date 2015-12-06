package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Jesus on 06/12/2015.
 */
public class GraficoBarrasViewHolder extends RecyclerView.ViewHolder {
    public BarChart grafico;

    public GraficoBarrasViewHolder(View v) {
        super(v);
        grafico = (BarChart) v.findViewById(R.id.chartDetallado);
    }
}
package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Afll on 08/12/2015.
 */
public class EncuestasGraficoViewHolder extends RecyclerView.ViewHolder{
    public BarChart grafico;

    public EncuestasGraficoViewHolder(View v) {
        super(v);
        grafico = (BarChart) v.findViewById(R.id.chartDetallado);
    }
}

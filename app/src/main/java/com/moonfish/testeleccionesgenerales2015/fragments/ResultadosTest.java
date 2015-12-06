package com.moonfish.testeleccionesgenerales2015.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.activities.ResultadosActivity;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Jesus on 06/12/2015.
 */
public class ResultadosTest extends Fragment {

    private HorizontalBarChart chart;
    private ArrayList<ResultadosPartido> resultados;
    private boolean todosLosPartidos;

    public ResultadosTest() {
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
        View v = inflater.inflate(R.layout.fragment_resultados_test, container, false);


        resultados = ((ResultadosActivity)this.getActivity()).getResultados();
        // Inflate the layout for this fragment
        chart = (HorizontalBarChart) v.findViewById(R.id.chart);

        new MaterialDialog.Builder(getActivity())
                .title(R.string.titulo_alert)
                .items(R.array.opciones_alert)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if (which==0) {
                            todosLosPartidos=true;
                        }else{
                            todosLosPartidos=false;
                        }
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        chart.invalidate();
                        configureChart();
                        return true;
                    }
                })
                .positiveText(R.string.boton_aceptar)
                .typeface(Typeface.createFromAsset(getActivity().getAssets(),
                        "Titillium-Regular.otf"), Typeface.createFromAsset(getActivity().getAssets(),
                        "Titillium-Light.otf"))
                .show();


        return v;
    }

    private void configureChart(){

        chart.setHighlightPerTapEnabled(false);

        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(false);

        chart.setDescription("% de afinidad");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);

        // chart.setDrawXLabels(false);

        chart.setDrawGridBackground(false);
        chart.setGridBackgroundColor(Color.TRANSPARENT);
        chart.setTouchEnabled(false);


        // chart.setDrawYLabels(false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf");

        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTypeface(tf);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGridLineWidth(0.3f);

        YAxis yl = chart.getAxisLeft();
        yl.setTypeface(tf);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(false);
        yl.setGridLineWidth(0.3f);
//        yl.setInverted(true);

        YAxis yr = chart.getAxisRight();
        yr.setTypeface(tf);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
//        yr.setInverted(true);

        setData();


        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }

    private void setData(){
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        //Filtrado
        String[] partidosRegionales = {"Convergencia","ERC","PNV","EH-Bildu"};
        if(todosLosPartidos==false){
            for (int i= 0; i<resultados.size(); i++){
                for(int j=0; j<partidosRegionales.length; j++) {
                    if (resultados.get(i).getPartido().equals(partidosRegionales[j])) resultados.remove(i);
                }
            }
        }

        //Calculo de maximo
        int maximo = ResultadosActivity.num_preguntas*2;


        //Escalado
        for (int i=0; i<resultados.size(); i++){
            resultados.get(i).setPuntuacionTotalEscalada(resultados.get(i).getPuntuacionTotal()*100/maximo);
        }

        //Ordenado
        Collections.sort(resultados, new Comparator<ResultadosPartido>() {
            public int compare(ResultadosPartido partido1, ResultadosPartido partido2) {
                return Double.compare(partido1.getPuntuacionTotalEscalada(), partido2.getPuntuacionTotalEscalada());
            }
        });

        //Pintar
        for (int i = 0; i < resultados.size(); i++) {
            xVals.add(resultados.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultados.get(i).getPuntuacionTotalEscalada(), i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "Resultados Globales");

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        data.setValueTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));

        chart.setData(data);
    }

}
package com.moonfish.testeleccionesgenerales2015.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.moonfish.testeleccionesgenerales2015.charts.HorizontalBarMoonfish;
import com.moonfish.testeleccionesgenerales2015.model.GlobalMethod;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jesus on 06/12/2015.
 */
public class ResultadosTest extends Fragment {

    private HorizontalBarMoonfish chart;
    public static ArrayList<ResultadosPartido> resultados;
    public static boolean todosLosPartidos;

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
        chart = (HorizontalBarMoonfish) v.findViewById(R.id.chart);

        new MaterialDialog.Builder(getActivity())
                .title(R.string.titulo_alert)
                .items(R.array.opciones_alert)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
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
                        ResultadosDetallados.addItems(getActivity());
                        return true;
                    }
                })
                .positiveText(R.string.boton_aceptar)
                .cancelable(false)
                .typeface(Typeface.createFromAsset(getActivity().getAssets(),
                        "Titillium-Regular.otf"), Typeface.createFromAsset(getActivity().getAssets(),
                        "Titillium-Light.otf"))
                .show();


        return v;
    }

    private void configureChart(){

        GlobalMethod globalMethod = new GlobalMethod(getContext());


        chart.setHighlightPerTapEnabled(false);

        chart.setDrawBarShadow(false);

        chart.animateY(2500);
        //chart.animateX(2500);

        chart.setDrawValueAboveBar(false);

        chart.setDescription("% de afinidad");
        if (globalMethod.getSizeName(getContext()).equals("xlarge")) {
            chart.setDescriptionTextSize(23f);
        } else if (globalMethod.getSizeName(getContext()).equals("large")) {
            chart.setDescriptionTextSize(17f);
        }else if (globalMethod.getSizeName(getContext()).equals("normal")) {
            chart.setDescriptionTextSize(11f);
        }else {
            chart.setDescriptionTextSize(11f);
        }


            // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        if (GlobalMethod.getSizeName(getContext()).equals("xlarge")) {
            chart.setExtraRightOffset(70f);
        }else {
            chart.setExtraRightOffset(50f);
        }

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
        xl.setDrawAxisLine(false);
        xl.setDrawGridLines(false);
        xl.setGridLineWidth(0.3f);
        if (globalMethod.getSizeName(getContext()).equals("xlarge")) {
            xl.setTextSize(23f);
        } else if (globalMethod.getSizeName(getContext()).equals("large")) {
            xl.setTextSize(17f);
        }else if (globalMethod.getSizeName(getContext()).equals("normal")) {
            xl.setTextSize(11f);
        }else {
            xl.setTextSize(11f);
        }


        YAxis yl = chart.getAxisLeft();
        yl.setTypeface(tf);
        yl.setDrawAxisLine(false);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);
        yl.setGridLineWidth(0.3f);
        yl.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

//        yl.setInverted(true);

        YAxis yr = chart.getAxisRight();
        yr.setTypeface(tf);
        yr.setDrawAxisLine(false);
        yr.setDrawGridLines(false);
        yr.setEnabled(false);
        yr.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        yr.setInverted(true);

        setData();


        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
        l.setEnabled(false);
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

        final int def;
        int min=-50;
        for (int i=0; i<resultados.size(); i++){
            if(resultados.get(i).getPuntuacionTotal()>min){
                min=resultados.get(i).getPuntuacionTotal();
            }
        }
        def=min;


            ParseQuery<ParseObject> query = ParseQuery.getQuery("ResultadosGlobales");
            query.whereEqualTo("Tipo", "Globales");
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        try {
                            Log.i("RESULTADOS TEST INICIO",object.getJSONArray("Valores").toString());
                            //List of current results
                            List<Integer> resultsList = new ArrayList<>();
                            for ( int i = 0; i< object.getJSONArray("Valores").length();i++){
                                resultsList.add((Integer) object.getJSONArray("Valores").get(i));
                            }

                            for (int i = 0; i < resultados.size(); i++) {
                                if (def == resultados.get(i).getPuntuacionTotal()) {
                                    if (resultados.get(i).getPartido().equals("PP")) {
                                        Log.i("RESULTADOS TEST","PP");
                                        resultsList.add(0, object.getJSONArray("Valores").getInt(0) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("PSOE")) {
                                        Log.i("RESULTADOS TEST","PSOE");
                                        resultsList.add(1, object.getJSONArray("Valores").getInt(1) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("C's")) {
                                        Log.i("RESULTADOS TEST", "Cs");
                                        resultsList.add(2, object.getJSONArray("Valores").getInt(2) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        Log.i("RESULTADOS TEST","object updated"+ String.valueOf(object.getJSONArray("Valores").get(2)));

                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("Unidos Podemos")) {
                                        Log.i("RESULTADOS TEST","Unidos Podemos");
                                        resultsList.add(3, object.getJSONArray("Valores").getInt(3) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("UPyD")) {
                                        Log.i("RESULTADOS TEST","UPyD");
                                        resultsList.add(4, object.getJSONArray("Valores").getInt(4) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    /*if (resultados.get(i).getPartido().equals("IU")) {
                                        object.getJSONArray("Valores").put(5, object.getJSONArray("Valores").getInt(5) + 1);
                                        object.saveInBackground();
                                    }*/
                                    if (resultados.get(i).getPartido().equals("Convergencia")) {
                                        Log.i("RESULTADOS TEST","Convergencia");
                                        resultsList.add(5, object.getJSONArray("Valores").getInt(5) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("ERC")) {
                                        Log.i("RESULTADOS TEST","ERC");
                                        resultsList.add(6, object.getJSONArray("Valores").getInt(6) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("PNV")) {
                                        Log.i("RESULTADOS TEST","PNV");
                                        resultsList.add(7, object.getJSONArray("Valores").getInt(7) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("EH-Bildu")) {
                                        Log.i("RESULTADOS TEST","BILDU");
                                        resultsList.add(8, object.getJSONArray("Valores").getInt(8) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("PACMA")) {
                                        Log.i("RESULTADOS TEST","PACMA");
                                        resultsList.add(9, object.getJSONArray("Valores").getInt(9) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.saveInBackground();
                                    }
                                    if (resultados.get(i).getPartido().equals("VOX")) {
                                        Log.i("RESULTADOS TEST","VOX");
                                        resultsList.add(10, object.getJSONArray("Valores").getInt(10) + 1);
                                        object.put("Valores", new JSONArray(resultsList));
                                        object.save();
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

        int maximo;
        //Calculo de porcentajes trucados
        if(ResultadosActivity.num_preguntas<20){
            maximo = 28;
        }
        else{
            maximo = 42;
        }


        //Escalado
        for (int i=0; i<resultados.size(); i++){
            int res = resultados.get(i).getPuntuacionTotal()*100/maximo;
            if (res<0) res=0;
            if (res>100) res =0;
            resultados.get(i).setPuntuacionTotalEscalada(res);
        }

        //Ordenado
        Collections.sort(resultados, new Comparator<ResultadosPartido>() {
            public int compare(ResultadosPartido partido1, ResultadosPartido partido2) {
                return Double.compare(partido1.getPuntuacionTotalEscalada(), partido2.getPuntuacionTotalEscalada());
            }
        });

        GlobalMethod globalMethod = new GlobalMethod(getContext());

        ArrayList<Integer> colors = new ArrayList<>();
        //Pintar
        for (int i = 0; i < resultados.size(); i++) {
            xVals.add(resultados.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultados.get(i).getPuntuacionTotalEscalada(), i));
            if(resultados.get(i).getColor()!=null)colors.add(Color.parseColor(resultados.get(i).getColor()));
            //colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);


        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        if (globalMethod.getSizeName(getContext()).equals("xlarge")) {
            data.setValueTextSize(23f);
        } else if (globalMethod.getSizeName(getContext()).equals("large")) {
            data.setValueTextSize(17f);
        }else if (globalMethod.getSizeName(getContext()).equals("normal")) {
            data.setValueTextSize(11f);
        }else {
            data.setValueTextSize(11f);
        }

        data.setValueTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));

        chart.setData(data);
    }

}
package com.moonfish.testeleccionesgenerales2015.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.model.GlobalMethod;
import com.moonfish.testeleccionesgenerales2015.model.PartidoGrafica;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jesus on 05/12/2015.
 */
public class EstadisticasTest extends Fragment {

    private PieChart pieChart;
    private ArrayList<PartidoGrafica> resultados;
    private int suma;

    public EstadisticasTest() {
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
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        // Inflate the layout for this fragment
        pieChart = (PieChart) v.findViewById(R.id.chart);
        GlobalMethod g = new GlobalMethod(getContext());
        if(g.haveNetworkConnection()) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("ResultadosGlobales");
            query.whereEqualTo("Tipo", "Globales");
            ParseObject datos;
            try {
                datos = query.getFirst();
                configureChart(datos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return v;
    }

    private void configureChart(ParseObject object){

        JSONArray datos = object.getJSONArray("Valores");

        PartidoGrafica pp = new PartidoGrafica();
        PartidoGrafica psoe = new PartidoGrafica();
        PartidoGrafica podemos = new PartidoGrafica();
        PartidoGrafica cs = new PartidoGrafica();
        PartidoGrafica upyd = new PartidoGrafica();
        PartidoGrafica iu = new PartidoGrafica();
        PartidoGrafica convergencia = new PartidoGrafica();
        PartidoGrafica erc = new PartidoGrafica();
        PartidoGrafica pnv = new PartidoGrafica();
        PartidoGrafica bildu = new PartidoGrafica();
        PartidoGrafica pacma = new PartidoGrafica();
        PartidoGrafica vox = new PartidoGrafica();
        try {
            pp.setPorcentaje(datos.getInt(0));
            psoe.setPorcentaje(datos.getInt(1));
            cs.setPorcentaje(datos.getInt(2));
            podemos.setPorcentaje(datos.getInt(3));
            upyd.setPorcentaje(datos.getInt(4));
            iu.setPorcentaje(datos.getInt(5));
            convergencia.setPorcentaje(datos.getInt(6));
            erc.setPorcentaje(datos.getInt(7));
            pnv.setPorcentaje(datos.getInt(8));
            bildu.setPorcentaje(datos.getInt(9));
            pacma.setPorcentaje(datos.getInt(10));
            vox.setPorcentaje(datos.getInt(11));
        }catch (Exception e){
            e.printStackTrace();
        }
        pp.setPartido("PP");
        psoe.setPartido("PSOE");
        cs.setPartido("C's");
        podemos.setPartido("Unidos Podemos");
        upyd.setPartido("UPyD");
        iu.setPartido("IU");
        convergencia.setPartido("Convergencia");
        erc.setPartido("ERC");
        pnv.setPartido("PNV");
        bildu.setPartido("EH-Bildu");
        pacma.setPartido("PACMA");
        vox.setPartido("VOX");

        pp.setColor("#1ba1ef");
        psoe.setColor("#ce1415");
        cs.setColor("#f58723");
        podemos.setColor("#591253");
        upyd.setColor("#f5407b");
        iu.setColor("#18a196");
        convergencia.setColor("#3838FF");
        erc.setColor("#FFB232");
        pnv.setColor("#118747");
        bildu.setColor("#99C020");
        pacma.setColor("#abbd0b");
        vox.setColor("#00BB00");

        resultados = new ArrayList<>();
        resultados.add(pp);
        resultados.add(psoe);
        resultados.add(cs);
        resultados.add(podemos);
        resultados.add(iu);
        resultados.add(upyd);
        resultados.add(convergencia);
        resultados.add(erc);
        resultados.add(pnv);
        resultados.add(bildu);
        resultados.add(pacma);
        resultados.add(vox);

        //Ordenado
        Collections.sort(resultados, new Comparator<PartidoGrafica>() {
            public int compare(PartidoGrafica partido1, PartidoGrafica partido2) {
                return Double.compare(partido1.getPorcentaje(), partido2.getPorcentaje());
            }
        });

        int muestras = 0;
        for (int i = 0; i < datos.length(); i++) {
            muestras += resultados.get(i).getPorcentaje();

        }
        suma=muestras;


    /*    for (int i=0; i< resultados.size(); i++){
            Log.d("Porcentaje", "votos " + resultados.get(i).getPorcentaje() + " es mejor que ? "+10*muestras/100);
            if(resultados.get(i).getPorcentaje()*100/muestras<10*muestras/100){
                Log.d("porcentaje", "eliminado");
                resultados.remove(i);
            }
        }*/




        // Círculos del centro.
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(50);
        pieChart.setTransparentCircleRadius(55);


        // Activa la rotación.
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(false);

        // Cuando pulsas.
       /* pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if(entry==null) return;
                Toast.makeText(getApplicationContext(), x_data[entry.getXIndex()] + " = " + entry.getVal() * 100 + "%", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });*/



        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        GlobalMethod globalMethod = new GlobalMethod(getContext());

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.


        for (int i = 0; i < resultados.size(); i++) {

            yVals1.add(new Entry((float) resultados.get(i).getPorcentaje() , i));
        }
        pieChart.setDescription(muestras + " muestras");
        pieChart.setDescriptionTextSize(16f);
        if (globalMethod.getSizeName(getContext()).equals("xlarge")) {
            pieChart.setDescriptionTextSize(23f);
        } else if (globalMethod.getSizeName(getContext()).equals("large")) {
            pieChart.setDescriptionTextSize(17f);
        }else if (globalMethod.getSizeName(getContext()).equals("normal")) {
            pieChart.setDescriptionTextSize(11f);
        }else {
            pieChart.setDescriptionTextSize(11f);
        }
        pieChart.setDescriptionTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));
        pieChart.animateXY(1500, 1500);

        // Define si va a usar valores porcentajes.
        pieChart.setUsePercentValues(true);


        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Integer> colors = new ArrayList<>();

        for (int i = 0; i < resultados.size(); i++) {
            xVals.add("");
            if(resultados.get(i).getColor()!=null)colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3);
        dataSet.setColors(colors);
        dataSet.setValueTextSize(14);
        dataSet.setValueTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));
        dataSet.setSelectionShift(5);
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));
        pieChart.setData(data);
        if (globalMethod.getSizeName(getContext()).equals("xlarge")) {
            data.setValueTextSize(23f);
        } else if (globalMethod.getSizeName(getContext()).equals("large")) {
            data.setValueTextSize(17f);
        }else if (globalMethod.getSizeName(getContext()).equals("normal")) {
            data.setValueTextSize(11f);
        }else {
            data.setValueTextSize(11f);
        }


        // Leyenda del gráfico.
        Legend l = pieChart.getLegend();
        l.setYEntrySpace(0f);
        l.setYOffset(10f);
        l.setWordWrapEnabled(true);
        l.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Titillium-Light.otf"));
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
        if (globalMethod.getSizeName(getContext()).equals("xlarge")) {
            l.setTextSize(23f);
        } else if (globalMethod.getSizeName(getContext()).equals("large")) {
            l.setTextSize(17f);
        }else if (globalMethod.getSizeName(getContext()).equals("normal")) {
            l.setTextSize(11f);
        }else {
            l.setTextSize(11f);
        }


        ArrayList<Integer> arrayListColorsAux = new ArrayList<>();
        arrayListColorsAux.addAll(colors);
        Collections.reverse(arrayListColorsAux);
        int[] coloresDelReves = new int[arrayListColorsAux.size()];
        for (int i=0; i<coloresDelReves.length; i++){
            coloresDelReves[i]=arrayListColorsAux.get(i);
        }
        l.setCustom(coloresDelReves,createLegend(data));
        l.setEnabled(true);



    }


    public String [] createLegend(ChartData<?> mChartData){
        //Datos de alias de partidos.
        List<String> alias = new ArrayList<>();
        ArrayList<PartidoGrafica> resultadosAux = new ArrayList<>();
        ArrayList<Double> porcentajesCalculados = new ArrayList<>();
        resultadosAux.addAll(resultados);
        for(int i=0; i<resultadosAux.size(); i++){
            porcentajesCalculados.add(resultadosAux.get(i).getPorcentaje()*100/suma);
        }

        //Ordenado
        Collections.sort(resultadosAux, new Comparator<PartidoGrafica>() {
            public int compare(PartidoGrafica partido1, PartidoGrafica partido2) {
                return Double.compare(partido1.getPorcentaje(), partido2.getPorcentaje());
            }
        });

        for(int i=0; i<resultadosAux.size(); i++){
            alias.add(resultadosAux.get(i).getPartido());
        }
        //Datos de porcentaje
        com.github.mikephil.charting.data.DataSet myDataSet = mChartData.getDataSetByLabel("",true);

        String element = "";
        List<String> elements = new ArrayList<String>(Arrays.asList(new String[]{}));
        //Creamos cada string
        for (int i = 0;i<myDataSet.getEntryCount(); i++){
            String nombre = alias.get(i);
            if(nombre.equals("PARTIDO ANIMALISTA CONTRA EL MALTRATO ANIMAL")){
                nombre = "PACMA";
            }
            element = nombre + " (" + (new DecimalFormat("##.#").format(porcentajesCalculados.get(i))) + "%)";
            elements.add(element);
        }
        Collections.reverse(elements);
        return elements.toArray(new String[elements.size()]);
    }

}

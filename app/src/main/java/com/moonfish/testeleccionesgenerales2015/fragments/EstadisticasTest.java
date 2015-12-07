package com.moonfish.testeleccionesgenerales2015.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.model.GlobalMethod;
import com.moonfish.testeleccionesgenerales2015.model.PartidoGrafica;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Jesus on 05/12/2015.
 */
public class EstadisticasTest extends Fragment {

    private PieChart pieChart;

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
        podemos.setPartido("Podemos");
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

        ArrayList<PartidoGrafica> resultados = new ArrayList<>();
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


    /*    for (int i=0; i< resultados.size(); i++){
            Log.d("Porcentaje", "votos " + resultados.get(i).getPorcentaje() + " es mejor que ? "+10*muestras/100);
            if(resultados.get(i).getPorcentaje()*100/muestras<10*muestras/100){
                Log.d("porcentaje", "eliminado");
                resultados.remove(i);
            }
        }*/

        // Define si va a usar valores porcentajes.
        pieChart.setUsePercentValues(true);


        // Círculos del centro.
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(50);
        pieChart.setTransparentCircleRadius(55);


        // Activa la rotación.
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

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

        // Leyenda del gráfico.
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
        l.setEnabled(false);


        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.


        for (int i = 0; i < resultados.size(); i++) {

            yVals1.add(new Entry((float) resultados.get(i).getPorcentaje() , i));
        }
        pieChart.setDescription(muestras + " muestras");
        pieChart.setDescriptionTextSize(16f);
        pieChart.setDescriptionTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));
        pieChart.animateXY(1500,1500);


        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Integer> colors = new ArrayList<>();

        for (int i = 0; i < resultados.size(); i++) {
            if (yVals1.get(i).getVal()<3) xVals.add("");
            else xVals.add(resultados.get(i).getPartido());
            if(resultados.get(i).getColor()!=null)colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3);
        dataSet.setColors(colors);
        dataSet.setValueTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));
        dataSet.setSelectionShift(5);
        PieData data = new PieData(xVals, dataSet);
        data.setValueTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Titillium-Regular.otf"));
        pieChart.setData(data);


    }


    /*private ArrayList<Party> getPartiesInArea(){
        ArrayList<Party> partiesInArea = new ArrayList<>();
        for(Party p : parties){
            for(PartyAdminArea pa : partyAdminAreas){
                if(pa.getAreaId()==administrativeArea.getAdminAreaId() && p.getPartyId()==pa.getPartyId()){
                    p.setPartyScore(pa.getScore());
                    partiesInArea.add(p);
                    break;
                }
            }
        }
        return partiesInArea;
    }

    private void addData(){
        ArrayList<Entry> y_vals = new ArrayList<>();
        for(int i = 0; i< y_data.length; i++){
            y_vals.add(new Entry(y_data[i], i));
        }
        ArrayList<String> x_vals = new ArrayList<>();

        for(int i = 0; i< x_data.length; i++){
            x_vals.add(x_data[i]);
        }

        // Esto es el espacio entre porciones y lo que se agranda cuando pulsas.
        PieDataSet data_set = new PieDataSet(y_vals, "Partidos");
        data_set.setSliceSpace(3);
        data_set.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<>();
        for(Party p: parties){
            if(p.getPartyScore()!=0) colors.add(p.getColor());
        }


        // Esto con los atributos de los nombres de partidos y porcentajes que aparecen sobre
        // el gráfico.
        data_set.setColors(colors);
        PieData data = new PieData(x_vals, data_set);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    private float[] getPercentages(){
        float total = 0;

        for(Party p : parties){
            total+=p.getPartyScore();
            if(p.getPartyScore()!=0) elements_num++;
        }
        float[] y_data = new float[elements_num];

        int i = 0;
        for(Party p : parties){
            if(p.getPartyScore()!=0){
                y_data[i] = p.getPartyScore()/total;
                i++;
            }
        }
        return y_data;
    }

    private String[] getNames(){
        String[] x_data = new String[elements_num];

        int i = 0;
        for(Party p : parties){
            if(p.getPartyScore()!=0){
                x_data[i] = p.getPartyName();
                i++;
            }

        }
        return x_data;
    }*/

}

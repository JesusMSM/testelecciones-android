package com.moonfish.testeleccionesgenerales2015.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.moonfish.testeleccionesgenerales2015.R;

import java.util.ArrayList;

/**
 * Created by Afll on 05/10/2015.
 */
public class EstadisticasActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        pieChart = (PieChart) findViewById(R.id.chart);

        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(EstadisticasActivity.this);
            }
        });

        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarColor)));

        configureChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureChart(){


        // Define si va a usar valores porcentajes.
        pieChart.setUsePercentValues(true);


        // Círculos del centro.
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(50);
        pieChart.setTransparentCircleRadius(10);

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

        addData();

        // Leyenda del gráfico.
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    private void addData(){
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < 5; i++) {
            yVals1.add(new Entry((float) Math.random() , i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < 5 + 1; i++)
            xVals.add("PP");

        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);
        PieData data = new PieData(xVals, dataSet);
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

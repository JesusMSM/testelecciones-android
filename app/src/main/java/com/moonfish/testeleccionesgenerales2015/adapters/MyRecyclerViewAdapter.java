package com.moonfish.testeleccionesgenerales2015.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.activities.ChooseTestActivity;
import com.moonfish.testeleccionesgenerales2015.activities.ResultadosActivity;
import com.moonfish.testeleccionesgenerales2015.fragments.EstadisticasEncuestas;
import com.moonfish.testeleccionesgenerales2015.fragments.ResultadosTest;
import com.moonfish.testeleccionesgenerales2015.model.Encuesta;
import com.moonfish.testeleccionesgenerales2015.model.GlobalMethod;
import com.moonfish.testeleccionesgenerales2015.model.Mensaje;
import com.moonfish.testeleccionesgenerales2015.model.ResultadoEncuestas;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;
import com.moonfish.testeleccionesgenerales2015.model.Title;
import com.moonfish.testeleccionesgenerales2015.viewholders.EncuestaContentViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.EncuestaHeaderViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.EncuestasGraficoViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.GraficoBarrasViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.MensajeViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.TitleViewHolder;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Afll on 05/12/2015.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Object> items;
    Context context;
    private ArrayList<ResultadosPartido> resultadosAdapter;

    private final int ENCUESTA_HEADER = 0, ENCUESTA_CONTENT = 1, MENSAJE=2, TITULO=3, GRAFICO_ECONOMIA=4, GRAFICO_SOCIEDAD=5, GRAFICO_ESTADO=6, GRAFICO_ENCUESTAS = 7;

    public MyRecyclerViewAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position) instanceof Encuesta){
            return ENCUESTA_HEADER;
        }
        if(items.get(position).equals("ENCUESTA_CONTENT")){
            return ENCUESTA_CONTENT;
        }
        if (items.get(position) instanceof Mensaje) {
            return MENSAJE;
        }
        if (items.get(position) instanceof Title) {
            return TITULO;
        }
        if (items.get(position).equals("GRAFICO_ECONOMIA")) {
            return GRAFICO_ECONOMIA;
        }
        if (items.get(position).equals("GRAFICO_SOCIEDAD")) {
            return GRAFICO_SOCIEDAD;
        }
        if (items.get(position).equals("GRAFICO_ESTADO")) {
            return GRAFICO_ESTADO;
        }
        if (items.get(position) instanceof List){
            return GRAFICO_ENCUESTAS;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (i){
            case ENCUESTA_HEADER:
                View v1 = inflater.inflate(R.layout.recyclerview_item_encuestaheader,viewGroup,false);
                viewHolder = new EncuestaHeaderViewHolder(v1);
                break;
            case ENCUESTA_CONTENT:
                View v2 = inflater.inflate(R.layout.recyclerview_item_encuestacontent,viewGroup,false);
                viewHolder = new EncuestaContentViewHolder(v2);
                break;
            case MENSAJE:
                View v3 = inflater.inflate(R.layout.recyclerview_item_mensaje,viewGroup,false);
                viewHolder = new MensajeViewHolder(v3);
                break;
            case TITULO:
                View v4 = inflater.inflate(R.layout.recyclerview_item_titulo,viewGroup,false);
                viewHolder = new TitleViewHolder(v4);
                break;
            case GRAFICO_ECONOMIA:
                View v5 = inflater.inflate(R.layout.recyclerview_item_grafico,viewGroup,false);
                viewHolder = new GraficoBarrasViewHolder(v5);
                break;
            case GRAFICO_SOCIEDAD:
                View v6 = inflater.inflate(R.layout.recyclerview_item_grafico,viewGroup,false);
                viewHolder = new GraficoBarrasViewHolder(v6);
                break;
            case GRAFICO_ESTADO:
                View v7 = inflater.inflate(R.layout.recyclerview_item_grafico,viewGroup,false);
                viewHolder = new GraficoBarrasViewHolder(v7);
                break;
            case GRAFICO_ENCUESTAS:
                View v8 = inflater.inflate(R.layout.recyclerview_item_grafico,viewGroup,false);
                viewHolder = new EncuestasGraficoViewHolder(v8);
                break;
            default:
                viewHolder=null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()) {
            case ENCUESTA_HEADER:
                EncuestaHeaderViewHolder vh1 = (EncuestaHeaderViewHolder) viewHolder;
                configureEncuestaHeaderViewholder(vh1, i);
                break;
            case ENCUESTA_CONTENT:
                EncuestaContentViewHolder vh2 = (EncuestaContentViewHolder) viewHolder;
                configureEncuestaContentViewHolder(vh2, i);
                break;
            case MENSAJE:
                MensajeViewHolder vh3 = (MensajeViewHolder) viewHolder;
                configureMensajeViewHolder(vh3, i);
                break;
            case TITULO:
                TitleViewHolder vh4 = (TitleViewHolder) viewHolder;
                configureTituloViewHolder(vh4, i);
                break;
            case GRAFICO_ECONOMIA:
                GraficoBarrasViewHolder vh5 = (GraficoBarrasViewHolder) viewHolder;
                configureGraficoEconomiaViewHolder(vh5, i);
                break;
            case GRAFICO_SOCIEDAD:
                GraficoBarrasViewHolder vh6 = (GraficoBarrasViewHolder) viewHolder;
                configureGraficoSociedadViewHolder(vh6, i);
                break;
            case GRAFICO_ESTADO:
                GraficoBarrasViewHolder vh7 = (GraficoBarrasViewHolder) viewHolder;
                configureGraficoEstadoViewHolder(vh7, i);
                break;
            case GRAFICO_ENCUESTAS:
                EncuestasGraficoViewHolder vh8 = (EncuestasGraficoViewHolder) viewHolder;
                configureEncuestasGraficoViewHolder(vh8, i);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void configureMensajeViewHolder(MensajeViewHolder vh, int position){
        Mensaje msg = (Mensaje) items.get(position);
        vh.mensaje.setText(msg.getMensaje());
        switch(msg.getDestino()){
            case 0:
                vh.link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context,ChooseTestActivity.class);
                        context.startActivity(i);
                        ((ResultadosActivity)context).finish();
                    }
                });

        }


        //Fonts
        vh.mensaje.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
    }

    public void configureGraficoEconomiaViewHolder(GraficoBarrasViewHolder vh, int position){

            BarChart grafico = vh.grafico;
        GlobalMethod globalMethod = new GlobalMethod(context);
        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);
        grafico.setClickable(false);
        grafico.setPinchZoom(false);
        grafico.setDoubleTapToZoomEnabled(false);
        grafico.setTouchEnabled(false);

        grafico.setDrawBarShadow(false);
        grafico.setDrawValueAboveBar(true);

        grafico.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        grafico.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);
        // grafico.setDrawYLabels(false);
        grafico.animateY(2500);



        XAxis xAxis = grafico.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setSpaceBetweenLabels(2);
        if (globalMethod.getSizeName(context).equals("xlarge")) {
            xAxis.setTextSize(23f);
        } else if (globalMethod.getSizeName(context).equals("large")) {
            xAxis.setTextSize(17f);
        }else if (globalMethod.getSizeName(context).equals("normal")) {
            xAxis.setTextSize(11f);
        }else {
            xAxis.setTextSize(11f);
        }


        YAxis leftAxis = grafico.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setEnabled(false);

        YAxis rightAxis = grafico.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setEnabled(false);

        Legend l = grafico.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        l.setEnabled(false);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        this.resultadosAdapter = new ArrayList<>();
        this.resultadosAdapter.addAll(ResultadosTest.resultados);

        //Filtrado
        String[] partidosRegionales = {"Convergencia","ERC","PNV","EH-Bildu"};
        if(ResultadosTest.todosLosPartidos==false){
            for (int i= 0; i<resultadosAdapter.size(); i++){
                for(int j=0; j<partidosRegionales.length; j++) {
                    if (resultadosAdapter.get(i).getPartido().equals(partidosRegionales[j])) resultadosAdapter.remove(i);
                }
            }
        }

        //Ordenado
        Collections.sort(resultadosAdapter, new Comparator<ResultadosPartido>() {
            public int compare(ResultadosPartido partido1, ResultadosPartido partido2) {
                return Double.compare(partido2.getPuntuacionEconomia(), partido1.getPuntuacionEconomia());
            }
        });

        ArrayList<Integer> colors = new ArrayList<>();
        //Pintar
        for (int i = 0; i < 4; i++) {
            xVals.add(resultadosAdapter.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultadosAdapter.get(i).getPuntuacionEconomia(), i));
            if(resultadosAdapter.get(i).getColor()!=null)colors.add(Color.parseColor(resultadosAdapter.get(i).getColor()));
            //colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);
        set1.setDrawValues(false);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        if (globalMethod.getSizeName(context).equals("xlarge")) {
            data.setValueTextSize(23f);
        } else if (globalMethod.getSizeName(context).equals("large")) {
            data.setValueTextSize(17f);
        }else if (globalMethod.getSizeName(context).equals("normal")) {
            data.setValueTextSize(11f);
        }else {
            data.setValueTextSize(11f);
        }
        data.setValueTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));;

        grafico.setData(data);

        grafico.invalidate();




    }
    public void configureGraficoEstadoViewHolder(GraficoBarrasViewHolder vh, int position){

        BarChart grafico = vh.grafico;

        GlobalMethod globalMethod = new GlobalMethod(context);

        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);
        grafico.setClickable(false);
        grafico.setPinchZoom(false);
        grafico.setDoubleTapToZoomEnabled(false);
        grafico.setTouchEnabled(false);

        grafico.setDrawBarShadow(false);
        grafico.setDrawValueAboveBar(true);
        grafico.animateY(2500);

        grafico.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        grafico.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);




        XAxis xAxis = grafico.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setDrawAxisLine(false);
        if (globalMethod.getSizeName(context).equals("xlarge")) {
            xAxis.setTextSize(23f);
        } else if (globalMethod.getSizeName(context).equals("large")) {
            xAxis.setTextSize(17f);
        }else if (globalMethod.getSizeName(context).equals("normal")) {
            xAxis.setTextSize(11f);
        }else {
            xAxis.setTextSize(11f);
        }


        YAxis leftAxis = grafico.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setEnabled(false);


        YAxis rightAxis = grafico.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setEnabled(false);

        Legend l = grafico.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        l.setEnabled(false);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        this.resultadosAdapter = new ArrayList<>();
        this.resultadosAdapter.addAll(ResultadosTest.resultados);

        //Filtrado
        String[] partidosRegionales = {"Convergencia","ERC","PNV","EH-Bildu"};
        if(ResultadosTest.todosLosPartidos==false){
            for (int i= 0; i<resultadosAdapter.size(); i++){
                for(int j=0; j<partidosRegionales.length; j++) {
                    if (resultadosAdapter.get(i).getPartido().equals(partidosRegionales[j])) resultadosAdapter.remove(i);
                }
            }
        }

        //Ordenado
        Collections.sort(resultadosAdapter, new Comparator<ResultadosPartido>() {
            public int compare(ResultadosPartido partido1, ResultadosPartido partido2) {
                return Double.compare(partido2.getPuntuacionEstado(), partido1.getPuntuacionEstado());
            }
        });

        ArrayList<Integer> colors = new ArrayList<>();
        //Pintar
        for (int i = 0; i < 4; i++) {
            xVals.add(resultadosAdapter.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultadosAdapter.get(i).getPuntuacionEstado(), i));
            if(resultadosAdapter.get(i).getColor()!=null)colors.add(Color.parseColor(resultadosAdapter.get(i).getColor()));
            //colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);
        set1.setDrawValues(false);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);


        BarData data = new BarData(xVals, dataSets);
        if (globalMethod.getSizeName(context).equals("xlarge")) {
            data.setValueTextSize(23f);
        } else if (globalMethod.getSizeName(context).equals("large")) {
            data.setValueTextSize(17f);
        }else if (globalMethod.getSizeName(context).equals("normal")) {
            data.setValueTextSize(11f);
        }else {
            data.setValueTextSize(11f);
        }
        data.setValueTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));;

        grafico.setData(data);

        grafico.invalidate();




    }
    public void configureGraficoSociedadViewHolder(GraficoBarrasViewHolder vh, int position){

        BarChart grafico = vh.grafico;
        GlobalMethod globalMethod = new GlobalMethod(context);

        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);
        grafico.setClickable(false);
        grafico.setPinchZoom(false);
        grafico.setDoubleTapToZoomEnabled(false);
        grafico.setTouchEnabled(false);

        grafico.setDrawBarShadow(false);
        grafico.setDrawValueAboveBar(true);
        grafico.animateY(2500);

        grafico.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        grafico.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);
        // grafico.setDrawYLabels(false);



        XAxis xAxis = grafico.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setDrawAxisLine(false);
        if (globalMethod.getSizeName(context).equals("xlarge")) {
            xAxis.setTextSize(23f);
        } else if (globalMethod.getSizeName(context).equals("large")) {
            xAxis.setTextSize(17f);
        }else if (globalMethod.getSizeName(context).equals("normal")) {
            xAxis.setTextSize(11f);
        }else {
            xAxis.setTextSize(11f);
        }


        YAxis leftAxis = grafico.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setEnabled(false);

        YAxis rightAxis = grafico.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setEnabled(false);

        Legend l = grafico.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        l.setEnabled(false);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        this.resultadosAdapter = new ArrayList<>();
        this.resultadosAdapter.addAll(ResultadosTest.resultados);

        //Filtrado
        String[] partidosRegionales = {"Convergencia","ERC","PNV","EH-Bildu"};
        if(ResultadosTest.todosLosPartidos==false){
            Log.d("porcentajes", "Es false, los borro");
            for (int i= 0; i<resultadosAdapter.size(); i++){
                for(int j=0; j<partidosRegionales.length; j++) {
                    if (resultadosAdapter.get(i).getPartido().equals(partidosRegionales[j])) resultadosAdapter.remove(i);
                }
            }
        }

        //Ordenado
        Collections.sort(resultadosAdapter, new Comparator<ResultadosPartido>() {
            public int compare(ResultadosPartido partido1, ResultadosPartido partido2) {
                return Double.compare(partido2.getPuntuacionSocial(), partido1.getPuntuacionSocial());
            }
        });

        ArrayList<Integer> colors = new ArrayList<>();
        //Pintar
        for (int i = 0; i < 4; i++) {
            xVals.add(resultadosAdapter.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultadosAdapter.get(i).getPuntuacionSocial(), i));
            if(resultadosAdapter.get(i).getColor()!=null)colors.add(Color.parseColor(resultadosAdapter.get(i).getColor()));
            //colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);
        set1.setDrawValues(false);

        BarData data = new BarData(xVals, dataSets);
        if (globalMethod.getSizeName(context).equals("xlarge")) {
            data.setValueTextSize(23f);
        } else if (globalMethod.getSizeName(context).equals("large")) {
            data.setValueTextSize(17f);
        }else if (globalMethod.getSizeName(context).equals("normal")) {
            data.setValueTextSize(11f);
        }else {
            data.setValueTextSize(11f);
        }
        data.setValueTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));;

        grafico.setData(data);

        grafico.invalidate();




    }
    private void configureTituloViewHolder(TitleViewHolder vh6, int position) {
        final Title title = (Title) items.get(position);
        if (title != null) {
            vh6.title.setText(title.getTitle());
        }

        //Fonts
        vh6.title.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));


    }

    public void configureEncuestaHeaderViewholder(EncuestaHeaderViewHolder vh, final int position){
        Encuesta encuesta = (Encuesta) items.get(position);
        vh.title.setText(encuesta.titulo);
        vh.fecha.setText(encuesta.fecha);
        vh.title.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        vh.fecha.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));

        //Listener SHOW-HIDE
        vh.layout.setOnClickListener(new onHeaderClickListener(vh,position,encuesta.id));
    }

    public void configureEncuestaContentViewHolder(EncuestaContentViewHolder vh, int position){
        List<String> respuestas = new ArrayList<>();
        Encuesta encuesta = null;
        try {
            encuesta = (Encuesta) items.get(position - 1);
            respuestas = encuesta.respuestas;
        }catch (Exception e){
            e.printStackTrace();
        }

        //Ocultamos todas las respuestas
        vh.respuesta1.setVisibility(View.GONE);vh.respuesta2.setVisibility(View.GONE);vh.respuesta3.setVisibility(View.GONE);vh.respuesta4.setVisibility(View.GONE);vh.respuesta5.setVisibility(View.GONE);vh.respuesta6.setVisibility(View.GONE);
        vh.respuesta7.setVisibility(View.GONE);vh.respuesta8.setVisibility(View.GONE);vh.respuesta9.setVisibility(View.GONE);vh.respuesta10.setVisibility(View.GONE);

        //Muestra/Oculta los textview en funcion del numero de respuesta. Artificioswitch (respuestas.size()){
        if (respuestas.size() >= 1) {
                vh.respuesta1.setVisibility(View.VISIBLE);
                vh.respuesta1.setText(respuestas.get(0));
                vh.respuesta1.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(0), position));
                if (respuestas.size() >= 2) {
                    vh.respuesta2.setVisibility(View.VISIBLE);
                    vh.respuesta2.setText(respuestas.get(1));
                    vh.respuesta2.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(1),position));
                    if (respuestas.size() >= 3) {
                        vh.respuesta3.setVisibility(View.VISIBLE);
                        vh.respuesta3.setText(respuestas.get(2));
                        vh.respuesta3.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(2),position));
                        if (respuestas.size() >= 4) {
                            vh.respuesta4.setVisibility(View.VISIBLE);
                            vh.respuesta4.setText(respuestas.get(3));
                            vh.respuesta4.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(3),position));
                            if (respuestas.size() >= 5) {
                                vh.respuesta5.setVisibility(View.VISIBLE);
                                vh.respuesta5.setText(respuestas.get(4));
                                vh.respuesta5.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(4),position));
                                if (respuestas.size() >= 6) {
                                    vh.respuesta6.setVisibility(View.VISIBLE);
                                    vh.respuesta6.setText(respuestas.get(5));
                                    vh.respuesta6.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(5),position));
                                    if (respuestas.size() >= 7) {
                                        vh.respuesta7.setVisibility(View.VISIBLE);
                                        vh.respuesta7.setText(respuestas.get(6));
                                        vh.respuesta7.setOnClickListener(new myOnEncuestaClickListener(encuesta.id,respuestas.get(6),position));
                                        if (respuestas.size() >= 8) {
                                            vh.respuesta8.setVisibility(View.VISIBLE);
                                            vh.respuesta8.setText(respuestas.get(7));
                                            vh.respuesta8.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(7),position));
                                            if (respuestas.size() >= 9) {
                                                vh.respuesta9.setVisibility(View.VISIBLE);
                                                vh.respuesta9.setText(respuestas.get(8));
                                                vh.respuesta9.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(8),position));
                                                if (respuestas.size() >= 10) {
                                                    vh.respuesta10.setVisibility(View.VISIBLE);
                                                    vh.respuesta10.setText(respuestas.get(9));
                                                    vh.respuesta10.setOnClickListener(new myOnEncuestaClickListener(encuesta.id, respuestas.get(9),position));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        //Fonts
        vh.respuesta1.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta2.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta3.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta4.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta5.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta6.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta7.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta8.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta9.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
        vh.respuesta10.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
    }

    public class myOnEncuestaClickListener implements View.OnClickListener{
        String idPreg,respuesta;
        int position;
        myOnEncuestaClickListener(String idPreg,String respuesta,int position){
            this.idPreg = idPreg;
            this.respuesta = respuesta;
            this.position=position;
        }

        @Override
        public void onClick(View v) {
            Log.i("ENCUESTAS","Answered");
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(idPreg, "true").commit();
            //Send to parse
            ParseQuery<ParseObject> query = ParseQuery.getQuery("ResultadosEncuestas");
            try {
                ParseObject object = query.whereEqualTo("idEncuesta", idPreg).whereEqualTo("respuesta",respuesta).getFirst();
                object.increment("puntuacion");
                object.saveInBackground();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //Change viewholder
            items.remove(position);
            items.add(position, new Mensaje("Gracias por participar en esta encuesta. Puede ver los resultados globales en la sección de Estadísticas."));
            notifyItemChanged(position);
        }
    }

    public class onHeaderClickListener implements View.OnClickListener{
        EncuestaHeaderViewHolder ehvh;
        int position;
        String encuestaId;
        onHeaderClickListener(EncuestaHeaderViewHolder ehvh, int position, String encuestaId){
            this.ehvh = ehvh;
            this.position = position;
            this.encuestaId = encuestaId;
        }

        @Override
        public void onClick(View v) {
            if (PreferenceManager.getDefaultSharedPreferences(context).getString("isContentDisplayed"+encuestaId, "false").equals("false")) {//Si no se estan mostrando -> Mostrar
                //ShowContent
                if (PreferenceManager.getDefaultSharedPreferences(context).getString(encuestaId, "false").equals("false")) {
                    Log.i("ENCUESTAS", "Resultados displayed: " + ehvh.isContentDisplayed());
                    items.add(position + 1, "ENCUESTA_CONTENT");
                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("isContentDisplayed"+encuestaId, "true").commit();//Se esta mostrando
                    notifyItemInserted(position + 1);
                    notifyDataSetChanged();

                } if(PreferenceManager.getDefaultSharedPreferences(context).getString(encuestaId, "false").equals("true")) {
                    Log.i("ENCUESTAS", "Mensaje displayed: " + ehvh.isContentDisplayed);
                    items.add(position + 1, new Mensaje("Gracias por participar en esta encuesta. Puede ver los resultados globales en la sección de Estadísticas."));
                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("isContentDisplayed"+encuestaId, "true").commit();//Se esta mostrando
                    notifyItemInserted(position + 1);
                    notifyDataSetChanged();
                }
            }else{//Ocultar
                //HideContent
                Log.i("ENCUESTAS","Hide");
                items.remove(position + 1);
                notifyItemRemoved(position + 1);
                notifyDataSetChanged();
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString("isContentDisplayed"+encuestaId, "false").commit();//No se esta mostrando
            }
        }
    }

    private void configureEncuestasGraficoViewHolder(EncuestasGraficoViewHolder vh, int position) {
        //ID del resultado
        List<ResultadoEncuestas> resEnc = (List<ResultadoEncuestas>) items.get(position);

        BarChart grafico = vh.grafico;

        GlobalMethod globalMethod = new GlobalMethod(context);

        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);
        grafico.setClickable(false);
        grafico.setPinchZoom(false);
        grafico.setDoubleTapToZoomEnabled(false);
        grafico.setTouchEnabled(false);

        grafico.setDrawBarShadow(false);
        grafico.setDrawValueAboveBar(true);


        grafico.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        grafico.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        grafico.setPinchZoom(false);

        grafico.setDrawGridBackground(false);




        XAxis xAxis = grafico.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);
        if (globalMethod.getSizeName(context).equals("xlarge")) {
            xAxis.setTextSize(23f);
        } else if (globalMethod.getSizeName(context).equals("large")) {
            xAxis.setTextSize(17f);
        }else if (globalMethod.getSizeName(context).equals("normal")) {
            xAxis.setTextSize(11f);
        }else {
            xAxis.setTextSize(11f);
        }

        YAxis leftAxis = grafico.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setEnabled(false);

        YAxis rightAxis = grafico.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setEnabled(false);

        Legend l = grafico.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        l.setEnabled(false);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();


        //Ordenado

        /*Collections.sort(resEnc, new Comparator<ResultadoEncuestas>() {
            public int compare(ResultadoEncuestas res1, ResultadoEncuestas res2) {
                return Double.compare(res2.puntuacion, res1.puntuacion);
            }
        });*/

        ArrayList<Integer> colors = new ArrayList<>();
        //Pintar
        for (int i = 0; i < resEnc.size(); i++) {
            xVals.add(resEnc.get(i).niceName);
            yVals1.add(new BarEntry((float) resEnc.get(i).puntuacion, i));
            colors.add(Color.parseColor(resEnc.get(i).color));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);
        set1.setDrawValues(true);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        if (GlobalMethod.getSizeName(context).equals("xlarge")) {
            data.setValueTextSize(23f);
        } else if (GlobalMethod.getSizeName(context).equals("large")) {
            data.setValueTextSize(17f);
        }else if (GlobalMethod.getSizeName(context).equals("normal")) {
            data.setValueTextSize(11f);
        }else {
            data.setValueTextSize(11f);
        }

        data.setValueTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));;

        grafico.setData(data);

        grafico.invalidate();
    }
}

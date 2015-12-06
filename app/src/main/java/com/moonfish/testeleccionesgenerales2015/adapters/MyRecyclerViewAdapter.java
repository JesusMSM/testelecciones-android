package com.moonfish.testeleccionesgenerales2015.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
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
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.fragments.ResultadosTest;
import com.moonfish.testeleccionesgenerales2015.model.GlobalMethod;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;
import com.moonfish.testeleccionesgenerales2015.model.Title;
import com.moonfish.testeleccionesgenerales2015.viewholders.EncuestaContentViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.EncuestaHeaderViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.GraficoBarrasViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.MensajeViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.TitleViewHolder;

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

    private final int ENCUESTA_HEADER = 0, ENCUESTA_CONTENT = 1, MENSAJE=2, TITULO=3, GRAFICO_ECONOMIA=4, GRAFICO_SOCIEDAD=5, GRAFICO_ESTADO=6;

    public MyRecyclerViewAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).equals("ENCUESTA_HEADER")){
            return ENCUESTA_HEADER;
        }
        if(items.get(position).equals("ENCUESTA_CONTENT")){
            return ENCUESTA_CONTENT;
        }
        if (items.get(position).equals("MENSAJE")) {
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
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void configureMensajeViewHolder(MensajeViewHolder vh, int position){

        vh.mensaje.setText("Para ver resultados detallados, haga el test en modo detallado.");

        //Fonts
        vh.mensaje.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Light.otf"));
    }

    public void configureGraficoEconomiaViewHolder(GraficoBarrasViewHolder vh, int position){

            BarChart grafico = vh.grafico;


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



        XAxis xAxis = grafico.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);


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
        for (int i = 0; i < 5; i++) {
            xVals.add(resultadosAdapter.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultadosAdapter.get(i).getPuntuacionEconomia(), i));
            if(resultadosAdapter.get(i).getColor()!=null)colors.add(Color.parseColor(resultadosAdapter.get(i).getColor()));
            //colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);

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
    public void configureGraficoEstadoViewHolder(GraficoBarrasViewHolder vh, int position){

        BarChart grafico = vh.grafico;


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



        XAxis xAxis = grafico.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);


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
        for (int i = 0; i < 5; i++) {
            xVals.add(resultadosAdapter.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultadosAdapter.get(i).getPuntuacionEstado(), i));
            if(resultadosAdapter.get(i).getColor()!=null)colors.add(Color.parseColor(resultadosAdapter.get(i).getColor()));
            //colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);

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
    public void configureGraficoSociedadViewHolder(GraficoBarrasViewHolder vh, int position){

        BarChart grafico = vh.grafico;


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



        XAxis xAxis = grafico.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);


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
                return Double.compare(partido2.getPuntuacionSocial(), partido1.getPuntuacionSocial());
            }
        });

        ArrayList<Integer> colors = new ArrayList<>();
        //Pintar
        for (int i = 0; i < 5; i++) {
            xVals.add(resultadosAdapter.get(i).getPartido());
            yVals1.add(new BarEntry((float) resultadosAdapter.get(i).getPuntuacionSocial(), i));
            if(resultadosAdapter.get(i).getColor()!=null)colors.add(Color.parseColor(resultadosAdapter.get(i).getColor()));
            //colors.add(Color.parseColor(resultados.get(i).getColor()));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(colors);

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
    private void configureTituloViewHolder(TitleViewHolder vh6, int position) {
        final Title title = (Title) items.get(position);
        if (title != null) {
            vh6.title.setText(title.getTitle());
        }

        //Fonts
        vh6.title.setTypeface(Typeface.createFromAsset(context.getAssets(), "Titillium-Regular.otf"));


    }

    public void configureEncuestaHeaderViewholder(final EncuestaHeaderViewHolder vh, final int position){
        vh.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!vh.isContentDisplayed){
                    //ShowContent
                    items.add(position+1, "ENCUESTA_CONTENT");
                    notifyItemInserted(position);
                    notifyDataSetChanged();
                    vh.isContentDisplayed = true;
                }else{
                    //HideContent
                    items.remove(position+1);
                    notifyItemRemoved(position+1);
                    notifyDataSetChanged();
                    vh.isContentDisplayed = false;
                }
            }
        });
    }

    public void configureEncuestaContentViewHolder(EncuestaContentViewHolder vh, int position){

    }
}

package com.moonfish.testeleccionesgenerales2015.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.activities.EncuestasActivity;
import com.moonfish.testeleccionesgenerales2015.adapters.MyRecyclerViewAdapter;
import com.moonfish.testeleccionesgenerales2015.model.ResultadoEncuestas;
import com.moonfish.testeleccionesgenerales2015.model.Title;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 05/12/2015.
 */
public class EstadisticasEncuestas extends Fragment {

    List<Object> items = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static List<ResultadoEncuestas> listResultados = new ArrayList<>();

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
        View v = inflater.inflate(R.layout.fragment_encuestas, container, false);

        //RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.listResultadosEncuestas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {
            getResultsFromParse();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        addItems();

        return v;
    }

    public void addItems(){
        /*for (int i = 0; i<listResultados.size();i++){
            items.add(new Title(listResultados.get(i).tituloPregunta));
            items.add(listResultados.get(i));
        }
        mAdapter = new MyRecyclerViewAdapter(getContext(),items);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    public void getResultsFromParse() throws ParseException {
        //Numero de encuestas
        ParseQuery.getQuery("Encuesta").whereEqualTo("titulo","nEncuestas").getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                int nEncuestas = Integer.parseInt(object.getString("json"));

                for (int i = nEncuestas - 1; i >= 0; i--) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("ResultadosEncuestas");
                    query.whereEqualTo("idEncuesta", String.valueOf(i + 1)).findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            listResultados = new ArrayList<>();

                            for (ParseObject obj : objects) {
                                Log.i("Estadisticas", obj.getString("respuesta"));
                                listResultados.add(new ResultadoEncuestas(obj.getString("idEncuesta"), obj.getString("respuesta"), obj.getInt("puntuacion"), obj.getString("tituloPregunta"), obj.getString("color"), obj.getString("niceName")));
                            }
                            items.add(new Title(objects.get(0).getString("tituloPregunta")));
                            items.add(listResultados);

                            mAdapter = new MyRecyclerViewAdapter(getContext(), items);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    });
                }
            }
        });


    }

}

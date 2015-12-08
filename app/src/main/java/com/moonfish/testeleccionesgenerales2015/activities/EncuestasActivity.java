package com.moonfish.testeleccionesgenerales2015.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.adapters.MyRecyclerViewAdapter;
import com.moonfish.testeleccionesgenerales2015.model.Encuesta;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Afll on 05/10/2015.
 */
public class EncuestasActivity extends AppCompatActivity {

    List<Object> items = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static String jsonEncuestas = "";
    public static List<Encuesta> encuestas = new ArrayList<>(); // <Titulo,List<Preguntas>>

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestas);

        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarColor)));

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.listEncuestas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Encuestas
        getEncuestasFromParse();

        try {
            readEncuestasJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        addItems();
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

    public void addItems() {
        if (items.size() > 0) {
            items.clear();
        } else {
            for (int i = encuestas.size() - 1; i >= 0; i--) {
                items.add(encuestas.get(i));
            }

            mAdapter = new MyRecyclerViewAdapter(this, items);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    /**********FUNCIONES ENCUESTAS********/
    public void getEncuestasFromParse(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Encuesta");
        try {
            ParseObject object = query.whereEqualTo("titulo", "Encuestas Debug").getFirst();
            EncuestasActivity.jsonEncuestas = (String) object.get("json");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void readEncuestasJSON() throws JSONException {
        String id = "";
        String title = "";
        String fecha = "";
        List<String> respuestasList;

        JSONObject jsonObject = new JSONObject(EncuestasActivity.jsonEncuestas);
        JSONObject encuestasObject = jsonObject.getJSONObject("encuestas");
        Iterator i = encuestasObject.keys();
        //Recorremos las Encuestas
        while (i.hasNext()){
            String idEncuesta = (String) i.next();
            JSONObject currentEncuesta = encuestasObject.getJSONObject(idEncuesta);
            //Extraemos el titulo de la encuesta
            id = idEncuesta;
            title = currentEncuesta.getString("titulo");
            fecha = currentEncuesta.getString("fecha");
            Log.i("ENCUESTAS", "Titulo: " + title);
            JSONObject respuestas = currentEncuesta.getJSONObject("respuestas");
            Iterator j = respuestas.keys();
            respuestasList = new ArrayList<>();
            //Recorremos las respuestas
            while (j.hasNext()){
                String idRespuesta = (String) j.next();
                respuestasList.add(respuestas.getString(idRespuesta));
                Log.i("ENCUESTAS", "Respuesta: " + respuestas.getString(idRespuesta));
            }
            if(!EncuestasActivity.encuestas.contains(new Encuesta(id, title, fecha, respuestasList))) {
                EncuestasActivity.encuestas.add(new Encuesta(id, title, fecha, respuestasList));
            }
        }
    }
}

package com.moonfish.testeleccionesgenerales2015.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.fragments.ResultadosDetallados;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;
import com.parse.ParseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Moonfish on 05/10/2015.
 */

public class TestActivity extends AppCompatActivity {

    private Context context;

    private int NUM_PREGUNTAS = 0;

    InterstitialAd mInterstitialAd;

    private String[] preguntas;
    private final int[] arrayNumRespuestas = {4,3,3,3,4, 3,3,3,3,3 ,2,3};
    private String[] respuestas;

    //categorias
    private String economia = "economia";
    private String sociedad = "sociedad";
    private String estado = "estado";

    private int nPreguntaActual = 1;
    private String json;


    //Variables de los partidos donde iremos almacenando los resultados
    public ResultadosPartido pp = new ResultadosPartido();
    public ResultadosPartido psoe = new ResultadosPartido();
    public ResultadosPartido cs = new ResultadosPartido();
    public ResultadosPartido podemos = new ResultadosPartido();
    public ResultadosPartido iu = new ResultadosPartido();
    public ResultadosPartido upyd = new ResultadosPartido();
    public ResultadosPartido convergencia = new ResultadosPartido();
    public ResultadosPartido pnv = new ResultadosPartido();
    public ResultadosPartido erc = new ResultadosPartido();
    public ResultadosPartido bildu = new ResultadosPartido();
    public ResultadosPartido pacma = new ResultadosPartido();
    public ResultadosPartido vox = new ResultadosPartido();

    //Objetos del xml
    private ProgressBar barraProgreso;
    private TextView categoria , pregunta, respuesta1, respuesta2, respuesta3, porcentajeProgreso;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        categoria = (TextView)findViewById(R.id.categoria);
        pregunta = (TextView)findViewById(R.id.pregunta);
        respuesta1 = (TextView)findViewById(R.id.respuesta1);
        respuesta2 = (TextView)findViewById(R.id.respuesta2);
        respuesta3 = (TextView)findViewById(R.id.respuesta3);
        porcentajeProgreso = (TextView)findViewById(R.id.porcentajeProgreso);

        //Typefaces
        categoria.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        pregunta.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));
        respuesta1.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));
        respuesta2.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));
        respuesta3.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));
        porcentajeProgreso.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));


        //Identificamos el tipo de test a través del intent
        Intent intent = getIntent();
        NUM_PREGUNTAS = intent.getIntExtra("numero_preguntas", 0);
        Log.i("puntuacion", "Número de preguntas del test " + NUM_PREGUNTAS);

        //Barra de progreso
        barraProgreso = (ProgressBar) findViewById(R.id.barra_progreso);
        barraProgreso.setMax(NUM_PREGUNTAS);

        Drawable draw= getResources().getDrawable(R.drawable.custom_progressbar);
        barraProgreso.setProgressDrawable(draw);

        json = loadJSONFromAsset("PREGUNTAS_JSON");
        if(json ==null){
            Log.i("puntuacion", "El json es nulo");
        }

        //Intersticial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_onshow_result));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                siguientePregunta();
            }
        });

        requestNewInterstitial();

        siguientePregunta();

    }

    public void siguientePregunta(){

        if(nPreguntaActual>NUM_PREGUNTAS){
            //TEST ACABADO
            //ParseAnalytics
            ParseAnalytics.trackEventInBackground("ONFINISH_TEST");
            //Anuncio muy rico
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }else {
                //Creamos el intent y preparamos la siguiente activity
                Intent i = new Intent(getApplicationContext(), ResultadosActivity.class);
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


                i.putExtra("PP", pp);
                i.putExtra("PSOE", psoe);
                i.putExtra("CS", cs);
                i.putExtra("PODEMOS", podemos);
                i.putExtra("CIUDADANOS", cs);
                i.putExtra("UPYD", upyd);
                i.putExtra("IU", iu);
                i.putExtra("CONVERGENCIA", convergencia);
                i.putExtra("ERC", erc);
                i.putExtra("PNV", pnv);
                i.putExtra("EH-BILDU", bildu);
                i.putExtra("PACMA", pacma);
                i.putExtra("VOX", vox);
                //Pasamos tambien el numero de preguntas del test, para poder aplicar porcentajes
                i.putExtra("numero_preguntas", NUM_PREGUNTAS);
                startActivity(i);
                finish();
            }
        }else {
            //Actualizamos la barra de progreso
            barraProgreso.setProgress(nPreguntaActual-1);
            porcentajeProgreso.setText(((nPreguntaActual-1)*100/NUM_PREGUNTAS) + " %");
            setPregunta();
        }
    }



    //Almacena los objetos Partidos dentro de la Lista Global partidosList a partir de un json
    public void setPregunta(){
        try {
            JSONArray jArray = new JSONArray(json);
                try {
                    Log.i("puntuacion", "El número de pregunta actuales es el " + nPreguntaActual);
                    JSONObject preguntaObject = jArray.getJSONObject(nPreguntaActual-1);
                    //Rellenamos el encabezado y la pregunta
                    categoria.setText(preguntaObject.getString("categoria"));
                    pregunta.setText(preguntaObject.getString("pregunta"));
                    Log.i("puntuacion", "Nueva pregunta:  " + preguntaObject.getString("pregunta"));
                    //Rellenamos las respuestas
                    JSONObject respuesta1Object = preguntaObject.getJSONObject("opcion_1");
                    respuesta1.setText(respuesta1Object.getString("respuesta"));
                    respuesta1.setX(5000);
                    respuesta1.animate().translationX(0).setDuration(0).start();
                    JSONObject respuesta2Object = preguntaObject.getJSONObject("opcion_2");
                    respuesta2.setText(respuesta2Object.getString("respuesta"));
                    respuesta2.setX(5000);
                    respuesta2.animate().translationX(0).setDuration(0).start();
                    JSONObject respuesta3Object = preguntaObject.getJSONObject("opcion_3");
                    respuesta3.setText(respuesta3Object.getString("respuesta"));
                    respuesta3.setX(5000);
                    respuesta3.animate().translationX(0).setDuration(0).start();
                } catch (JSONException e) {
                    Log.i("puntuacion", "Error lectura de JSON");
                }
        } catch (JSONException e) {
            Log.i("puntuacion", "Error lectura de JSONArray");
            e.printStackTrace();
        }
    }


    public void onClick(View w){
        //Leemos el json con las preguntas
        try {
            JSONArray jArray = new JSONArray(json);
            try {
                JSONObject preguntaObject = jArray.getJSONObject(nPreguntaActual-1);
                // Según el id de la view pulsada, creo una nueva respuesta del usuario.
                switch (w.getId()){

                    //CASO: SI PULSA LA OPCIÓN 1
                    case R.id.respuesta1:
                        JSONObject respuesta1Object = preguntaObject.getJSONObject("opcion_1");

                        //Puntuamos al partido en el global
                        pp.addPuntuacionTotal(respuesta1Object.getInt("PP"));
                        psoe.addPuntuacionTotal(respuesta1Object.getInt("PSOE"));
                        cs.addPuntuacionTotal(respuesta1Object.getInt("CIUDADANOS"));
                        podemos.addPuntuacionTotal(respuesta1Object.getInt("PODEMOS"));
                        iu.addPuntuacionTotal(respuesta1Object.getInt("IU"));
                        upyd.addPuntuacionTotal(respuesta1Object.getInt("UPYD"));
                        convergencia.addPuntuacionTotal(respuesta1Object.getInt("CONVERGENCIA"));
                        pnv.addPuntuacionTotal(respuesta1Object.getInt("PNV"));
                        erc.addPuntuacionTotal(respuesta1Object.getInt("ERC"));
                        bildu.addPuntuacionTotal(respuesta1Object.getInt("EH-BILDU"));
                        pacma.addPuntuacionTotal(respuesta1Object.getInt("PACMA"));
                        vox.addPuntuacionTotal(respuesta1Object.getInt("VOX"));

                        //Ahora puntuamos la categoría correspondiente
                        if(preguntaObject.getString("categoria").equals(economia)){

                            pp.addPuntuacionEconomia(respuesta1Object.getInt("PP"));
                            psoe.addPuntuacionEconomia(respuesta1Object.getInt("PSOE"));
                            cs.addPuntuacionEconomia(respuesta1Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionEconomia(respuesta1Object.getInt("PODEMOS"));
                            iu.addPuntuacionEconomia(respuesta1Object.getInt("IU"));
                            upyd.addPuntuacionEconomia(respuesta1Object.getInt("UPYD"));
                            convergencia.addPuntuacionEconomia(respuesta1Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionEconomia(respuesta1Object.getInt("PNV"));
                            erc.addPuntuacionEconomia(respuesta1Object.getInt("ERC"));
                            bildu.addPuntuacionEconomia(respuesta1Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionEconomia(respuesta1Object.getInt("PACMA"));
                            vox.addPuntuacionEconomia(respuesta1Object.getInt("VOX"));

                        } else if(preguntaObject.getString("categoria").equals(sociedad)){

                            pp.addPuntuacionSocial(respuesta1Object.getInt("PP"));
                            psoe.addPuntuacionSocial(respuesta1Object.getInt("PSOE"));
                            cs.addPuntuacionSocial(respuesta1Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionSocial(respuesta1Object.getInt("PODEMOS"));
                            iu.addPuntuacionSocial(respuesta1Object.getInt("IU"));
                            upyd.addPuntuacionSocial(respuesta1Object.getInt("UPYD"));
                            convergencia.addPuntuacionSocial(respuesta1Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionSocial(respuesta1Object.getInt("PNV"));
                            erc.addPuntuacionSocial(respuesta1Object.getInt("ERC"));
                            bildu.addPuntuacionSocial(respuesta1Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionSocial(respuesta1Object.getInt("PACMA"));
                            vox.addPuntuacionSocial(respuesta1Object.getInt("VOX"));

                        } else if(preguntaObject.getString("categoria").equals(estado)){

                            pp.addPuntuacionEstado(respuesta1Object.getInt("PP"));
                            psoe.addPuntuacionEstado(respuesta1Object.getInt("PSOE"));
                            cs.addPuntuacionEstado(respuesta1Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionEstado(respuesta1Object.getInt("PODEMOS"));
                            iu.addPuntuacionEstado(respuesta1Object.getInt("IU"));
                            upyd.addPuntuacionEstado(respuesta1Object.getInt("UPYD"));
                            convergencia.addPuntuacionEstado(respuesta1Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionEstado(respuesta1Object.getInt("PNV"));
                            erc.addPuntuacionEstado(respuesta1Object.getInt("ERC"));
                            bildu.addPuntuacionEstado(respuesta1Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionEstado(respuesta1Object.getInt("PACMA"));
                            vox.addPuntuacionEstado(respuesta1Object.getInt("VOX"));

                        }
                        break;

                    //CASO: SI PULSA LA OPCION 2
                    case R.id.respuesta2:

                        JSONObject respuesta2Object = preguntaObject.getJSONObject("opcion_2");

                        //Puntuamos al partido en el global
                        pp.addPuntuacionTotal(respuesta2Object.getInt("PP"));
                        psoe.addPuntuacionTotal(respuesta2Object.getInt("PSOE"));
                        cs.addPuntuacionTotal(respuesta2Object.getInt("CIUDADANOS"));
                        podemos.addPuntuacionTotal(respuesta2Object.getInt("PODEMOS"));
                        iu.addPuntuacionTotal(respuesta2Object.getInt("IU"));
                        upyd.addPuntuacionTotal(respuesta2Object.getInt("UPYD"));
                        convergencia.addPuntuacionTotal(respuesta2Object.getInt("CONVERGENCIA"));
                        pnv.addPuntuacionTotal(respuesta2Object.getInt("PNV"));
                        erc.addPuntuacionTotal(respuesta2Object.getInt("ERC"));
                        bildu.addPuntuacionTotal(respuesta2Object.getInt("EH-BILDU"));
                        pacma.addPuntuacionTotal(respuesta2Object.getInt("PACMA"));
                        vox.addPuntuacionTotal(respuesta2Object.getInt("VOX"));

                        //Ahora puntuamos la categoría correspondiente
                        if(economia.equals(preguntaObject.getString("categoria"))){

                            pp.addPuntuacionEconomia(respuesta2Object.getInt("PP"));
                            psoe.addPuntuacionEconomia(respuesta2Object.getInt("PSOE"));
                            cs.addPuntuacionEconomia(respuesta2Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionEconomia(respuesta2Object.getInt("PODEMOS"));
                            iu.addPuntuacionEconomia(respuesta2Object.getInt("IU"));
                            upyd.addPuntuacionEconomia(respuesta2Object.getInt("UPYD"));
                            convergencia.addPuntuacionEconomia(respuesta2Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionEconomia(respuesta2Object.getInt("PNV"));
                            erc.addPuntuacionEconomia(respuesta2Object.getInt("ERC"));
                            bildu.addPuntuacionEconomia(respuesta2Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionEconomia(respuesta2Object.getInt("PACMA"));
                            vox.addPuntuacionEconomia(respuesta2Object.getInt("VOX"));

                        } else if(sociedad.equals(preguntaObject.getString("categoria"))){

                            pp.addPuntuacionSocial(respuesta2Object.getInt("PP"));
                            psoe.addPuntuacionSocial(respuesta2Object.getInt("PSOE"));
                            cs.addPuntuacionSocial(respuesta2Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionSocial(respuesta2Object.getInt("PODEMOS"));
                            iu.addPuntuacionSocial(respuesta2Object.getInt("IU"));
                            upyd.addPuntuacionSocial(respuesta2Object.getInt("UPYD"));
                            convergencia.addPuntuacionSocial(respuesta2Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionSocial(respuesta2Object.getInt("PNV"));
                            erc.addPuntuacionSocial(respuesta2Object.getInt("ERC"));
                            bildu.addPuntuacionSocial(respuesta2Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionSocial(respuesta2Object.getInt("PACMA"));
                            vox.addPuntuacionSocial(respuesta2Object.getInt("VOX"));

                        } else if(estado.equals(preguntaObject.getString("categoria"))){

                            pp.addPuntuacionEstado(respuesta2Object.getInt("PP"));
                            psoe.addPuntuacionEstado(respuesta2Object.getInt("PSOE"));
                            cs.addPuntuacionEstado(respuesta2Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionEstado(respuesta2Object.getInt("PODEMOS"));
                            iu.addPuntuacionEstado(respuesta2Object.getInt("IU"));
                            upyd.addPuntuacionEstado(respuesta2Object.getInt("UPYD"));
                            convergencia.addPuntuacionEstado(respuesta2Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionEstado(respuesta2Object.getInt("PNV"));
                            erc.addPuntuacionEstado(respuesta2Object.getInt("ERC"));
                            bildu.addPuntuacionEstado(respuesta2Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionEstado(respuesta2Object.getInt("PACMA"));
                            vox.addPuntuacionEstado(respuesta2Object.getInt("VOX"));

                        }
                        break;

                    //CASO: SI PULSA LA OPCIÓN  3
                    case R.id.respuesta3:
                        JSONObject respuesta3Object = preguntaObject.getJSONObject("opcion_3");

                        //Puntuamos al partido en el global
                        pp.addPuntuacionTotal(respuesta3Object.getInt("PP"));
                        psoe.addPuntuacionTotal(respuesta3Object.getInt("PSOE"));
                        cs.addPuntuacionTotal(respuesta3Object.getInt("CIUDADANOS"));
                        podemos.addPuntuacionTotal(respuesta3Object.getInt("PODEMOS"));
                        iu.addPuntuacionTotal(respuesta3Object.getInt("IU"));
                        upyd.addPuntuacionTotal(respuesta3Object.getInt("UPYD"));
                        convergencia.addPuntuacionTotal(respuesta3Object.getInt("CONVERGENCIA"));
                        pnv.addPuntuacionTotal(respuesta3Object.getInt("PNV"));
                        erc.addPuntuacionTotal(respuesta3Object.getInt("ERC"));
                        bildu.addPuntuacionTotal(respuesta3Object.getInt("EH-BILDU"));
                        pacma.addPuntuacionTotal(respuesta3Object.getInt("PACMA"));
                        vox.addPuntuacionTotal(respuesta3Object.getInt("VOX"));


                        //Ahora puntuamos la categoría correspondiente
                        if(economia.equals(preguntaObject.getString("categoria"))){

                            pp.addPuntuacionEconomia(respuesta3Object.getInt("PP"));
                            psoe.addPuntuacionEconomia(respuesta3Object.getInt("PSOE"));
                            cs.addPuntuacionEconomia(respuesta3Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionEconomia(respuesta3Object.getInt("PODEMOS"));
                            iu.addPuntuacionEconomia(respuesta3Object.getInt("IU"));
                            upyd.addPuntuacionEconomia(respuesta3Object.getInt("UPYD"));
                            convergencia.addPuntuacionEconomia(respuesta3Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionEconomia(respuesta3Object.getInt("PNV"));
                            erc.addPuntuacionEconomia(respuesta3Object.getInt("ERC"));
                            bildu.addPuntuacionEconomia(respuesta3Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionEconomia(respuesta3Object.getInt("PACMA"));
                            vox.addPuntuacionEconomia(respuesta3Object.getInt("VOX"));

                        } else if(sociedad.equals(preguntaObject.getString("categoria"))){

                            pp.addPuntuacionSocial(respuesta3Object.getInt("PP"));
                            psoe.addPuntuacionSocial(respuesta3Object.getInt("PSOE"));
                            cs.addPuntuacionSocial(respuesta3Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionSocial(respuesta3Object.getInt("PODEMOS"));
                            iu.addPuntuacionSocial(respuesta3Object.getInt("IU"));
                            upyd.addPuntuacionSocial(respuesta3Object.getInt("UPYD"));
                            convergencia.addPuntuacionSocial(respuesta3Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionSocial(respuesta3Object.getInt("PNV"));
                            erc.addPuntuacionSocial(respuesta3Object.getInt("ERC"));
                            bildu.addPuntuacionSocial(respuesta3Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionSocial(respuesta3Object.getInt("PACMA"));
                            vox.addPuntuacionSocial(respuesta3Object.getInt("VOX"));

                        } else if(estado.equals(preguntaObject.getString("categoria"))){

                            pp.addPuntuacionEstado(respuesta3Object.getInt("PP"));
                            psoe.addPuntuacionEstado(respuesta3Object.getInt("PSOE"));
                            cs.addPuntuacionEstado(respuesta3Object.getInt("CIUDADANOS"));
                            podemos.addPuntuacionEstado(respuesta3Object.getInt("PODEMOS"));
                            iu.addPuntuacionEstado(respuesta3Object.getInt("IU"));
                            upyd.addPuntuacionEstado(respuesta3Object.getInt("UPYD"));
                            convergencia.addPuntuacionEstado(respuesta3Object.getInt("CONVERGENCIA"));
                            pnv.addPuntuacionEstado(respuesta3Object.getInt("PNV"));
                            erc.addPuntuacionEstado(respuesta3Object.getInt("ERC"));
                            bildu.addPuntuacionEstado(respuesta3Object.getInt("EH-BILDU"));
                            pacma.addPuntuacionEstado(respuesta3Object.getInt("PACMA"));
                            vox.addPuntuacionEstado(respuesta3Object.getInt("VOX"));

                        }
                        break;

                    default:
                        break;
                }

            } catch (JSONException e) {
                Log.i("PartidosJSON", "Error lectura de JSON");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //Actualizamos el contador de pregunta actual
        nPreguntaActual++;

        siguientePregunta();

    }


    //Método que lee un fichero json almacenado en assets
    public String loadJSONFromAsset(String nameFile) {
        String json = null;
        try {

            InputStream is = getAssets().open(nameFile);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

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

    private void requestNewInterstitial() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this)
                .title(R.string.titulo_onback)
                .content(R.string.content_onback)
                .positiveText("Sí")
                .negativeText("No")
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        TestActivity.super.onBackPressed();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .typeface(Typeface.createFromAsset(getAssets(),
                        "Titillium-Regular.otf"), Typeface.createFromAsset(getAssets(),
                        "Titillium-Light.otf"))
                .show();

    }
}

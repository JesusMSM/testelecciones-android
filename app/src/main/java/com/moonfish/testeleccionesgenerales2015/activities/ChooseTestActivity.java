package com.moonfish.testeleccionesgenerales2015.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Afll on 05/10/2015.
 */
public class ChooseTestActivity extends Activity {

    private int NUMERO_PREGUNTAS_ESTANDAR=16;
    private int NUMERO_PREGUNTAS_DETALLADO=24;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetest);

        TextView estandar = (TextView) findViewById(R.id.modoEstandar);
        TextView preguntas = (TextView) findViewById(R.id.preguntas);
        TextView minutos = (TextView) findViewById(R.id.minutos);
        TextView resultados = (TextView) findViewById(R.id.resultados);

        //Modo detallado

        TextView detallado = (TextView) findViewById(R.id.modoDetallado);
        TextView preguntas2 = (TextView) findViewById(R.id.preguntas2);
        TextView minutos2 = (TextView) findViewById(R.id.minutos2);
        TextView resultados2 = (TextView) findViewById(R.id.resultados2);


        estandar.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        preguntas.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));;
        minutos.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));;
        resultados.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));;

        detallado.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        preguntas2.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));;
        minutos2.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));;
        resultados2.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));;

    }

    public void empezarTestEstandar(View v) {
        Intent i = new Intent(getApplicationContext(),TestActivity.class);
        i.putExtra("numero_preguntas", NUMERO_PREGUNTAS_ESTANDAR);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    public void empezarTestDetallado(View v) {
        Intent i = new Intent(getApplicationContext(),TestActivity.class);
        i.putExtra("numero_preguntas", NUMERO_PREGUNTAS_DETALLADO);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();

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
}

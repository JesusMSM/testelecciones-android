package com.moonfish.testeleccionesgenerales2015.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.moonfish.testeleccionesgenerales2015.R;
import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Parse.enableLocalDatastore(getApplicationContext());
            Parse.initialize(this, "4fP3XRn8if2uekAMFMNZHUv27ZDbHuBdVXce3NsV", "4BY9wy4KFTy6VwmvmsIpnJRkfLlVC8CMXTsdbVit");
        }catch(Exception e){
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);

        //Buttons
        Button test = (Button) findViewById(R.id.testButton);
        Button encuestas = (Button) findViewById(R.id.encuestasButton);
        Button estadisticas = (Button) findViewById(R.id.estadisticasButton);
        Button programas = (Button) findViewById(R.id.programasButton);

        test.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        encuestas.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        estadisticas.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        programas.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ChooseTestActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
        encuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),EncuestasActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
        estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),EstadisticasActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);


            }
        });
        programas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ProgramasActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

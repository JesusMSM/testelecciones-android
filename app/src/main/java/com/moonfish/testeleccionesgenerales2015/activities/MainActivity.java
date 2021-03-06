package com.moonfish.testeleccionesgenerales2015.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.model.Encuesta;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    InterstitialAd mInterstitialAd;
    InterstitialAd mInterstitialAd2;

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
        final Button test = (Button) findViewById(R.id.testButton);
        final Button encuestas = (Button) findViewById(R.id.encuestasButton);
        Button estadisticas = (Button) findViewById(R.id.estadisticasButton);
        Button programas = (Button) findViewById(R.id.programasButton);
        TextView titulo = (TextView) findViewById(R.id.titulo);
        TextView contacto = (TextView) findViewById(R.id.contacto);
        TextView fraseContacto = (TextView) findViewById(R.id.fraseContacto);

        contacto.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        titulo.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        test.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        encuestas.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        estadisticas.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        programas.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Semibold.otf"));;
        fraseContacto.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));;

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setType("plain/text");
                    sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"moonfishteam@gmail.com"});
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacto Test Generales 20-D");
                    startActivity(sendIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Intersticial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ontest_click));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                test.callOnClick();
            }
        });

        requestNewInterstitial();

        //Intersticial
        mInterstitialAd2 = new InterstitialAd(this);
        mInterstitialAd2.setAdUnitId(getResources().getString(R.string.interstitial_onencuestas_click));

        mInterstitialAd2.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial2();
                encuestas.callOnClick();
            }
        });

        requestNewInterstitial();
        requestNewInterstitial2();

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {

                    Intent i = new Intent(getApplicationContext(), ChooseTestActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);

                    //ParseAnalytics
                    ParseAnalytics.trackEventInBackground("ONTAP_TEST");
                }
            }
        });
        encuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd2.isLoaded()) {
                    mInterstitialAd2.show();
                } else {
                    Intent i = new Intent(v.getContext(), EncuestasActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);

                    //ParseAnalytics
                    ParseAnalytics.trackEventInBackground("ONTAP_ENCUESTAS");
                }
            }
        });
        estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),EstadisticasActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                //ParseAnalytics
                ParseAnalytics.trackEventInBackground("ONTAP_ESTADISTICAS");
            }
        });
        programas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ProgramasActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                //ParseAnalytics
                ParseAnalytics.trackEventInBackground("ONTAP_PROGRAMAS");
            }
        });

        //Admob
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void requestNewInterstitial() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    private void requestNewInterstitial2() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd2.loadAd(adRequest);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

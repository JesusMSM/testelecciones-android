package com.moonfish.testeleccionesgenerales2015.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.moonfish.testeleccionesgenerales2015.R;

public class ProgramaContentActivity extends AppCompatActivity {

    public String[] partidos = {"PP", "PSOE", "PODEMOS", "Ciudadanos", "UPYD", "IU", "PACMA"};
    public String[] links = {
            "https://docs.google.com/gview?embedded=true&url=http://www.pp.es/sites/default/files/documentos/af_pp_programa-municipales_2015_actualizado-20.03.15.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pp.es/sites/default/files/documentos/af_pp_programa-municipales_2015_actualizado-20.03.15.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pp.es/sites/default/files/documentos/af_pp_programa-municipales_2015_actualizado-20.03.15.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pp.es/sites/default/files/documentos/af_pp_programa-municipales_2015_actualizado-20.03.15.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pp.es/sites/default/files/documentos/af_pp_programa-municipales_2015_actualizado-20.03.15.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pp.es/sites/default/files/documentos/af_pp_programa-municipales_2015_actualizado-20.03.15.pdf"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa_content);

        Intent intent = getIntent();
        int indice = intent.getExtras().getInt("indice");

        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        getSupportActionBar().setTitle("Programa de " + partidos[indice]);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ProgramaContentActivity.this);
            }
        });
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarColor)));


        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(links[indice]);


    }





}

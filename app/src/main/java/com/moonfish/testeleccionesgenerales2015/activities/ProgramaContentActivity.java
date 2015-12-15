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
import android.widget.Toast;

import com.moonfish.testeleccionesgenerales2015.R;

public class ProgramaContentActivity extends AppCompatActivity {

    public String[] partidos = {"PP", "PSOE", "PODEMOS", "Ciudadanos", "UPYD", "IU", "PACMA","CONVERGENCIA","ERC","PNV","EH-Bildu","VOX"};
    public String[] links = {
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/pp/pp.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/psoe-programa-electoral-2015/psoe-programa-electoral-2015.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/programa-elecciones-generales-podemos-2015/programa-elecciones-generales-podemos-2015.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/programa-electoral-1-30/programa-electoral-1-30.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/programa-upyd-elecciones-generales-2015/programa-upyd-elecciones-generales-2015.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/15/programa-completo-iu-elecciones-generales-20d-2015/programa-completo-iu-elecciones-generales-20d-2015.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/programa-electoral-pacma-elecciones-generales-2015/programa-electoral-pacma-elecciones-generales-2015.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/programa-dl-2015/programa-dl-2015.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/e2011-programa/e2011-programa.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/17970-archivo/17970-archivo.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/07/programa-gazteleraz/programa-gazteleraz.pdf",
            "https://docs.google.com/gview?embedded=true&url=http://www.pdf-archive.com/2015/12/15/vox-programa-generales-2015-1-50/vox-programa-generales-2015-1-50.pdf"


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
                onBackPressed();

            }
        });
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarColor)));

        if(indice==3||indice==11) {
            Toast.makeText(this, "Se muestra una versión reducida de este programa al no ser posible cargar el programa completo",
                    Toast.LENGTH_LONG).show();
        }

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        myWebView.loadUrl(links[indice]);


    }





}

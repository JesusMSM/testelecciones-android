package com.moonfish.testeleccionesgenerales2015.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;

public class ResultadosActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        //Identificamos el tipo de test a través del intent
        Intent intent = getIntent();
        pp = intent.getParcelableExtra("PP");
        psoe = intent.getParcelableExtra("PSOE");
        cs = intent.getParcelableExtra("CIUDADANOS");
        podemos = intent.getParcelableExtra("PODEMOS");
        iu = intent.getParcelableExtra("IU");
        upyd = intent.getParcelableExtra("UPYD");
        convergencia= intent.getParcelableExtra("CONVERGENCIA");
        erc = intent.getParcelableExtra("ERC");
        pnv = intent.getParcelableExtra("PNV");
        bildu = intent.getParcelableExtra("EH-BILDU");
        pacma = intent.getParcelableExtra("PACMA");
        vox = intent.getParcelableExtra("VOX");

        Log.i("puntuaciones", "Nombre partido: TOTAL ECONOMÍA SOCIEDAD ESTADO");
        Log.i("puntuaciones", "Puntuaciones pp: " + pp.getPuntuacionTotal() + " " + pp.getPuntuacionEconomia() + " "+ pp.getPuntuacionSocial() + " "+ pp.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones psoe: " + psoe.getPuntuacionTotal() + " " + psoe.getPuntuacionEconomia() + " "+ psoe.getPuntuacionSocial() + " "+ psoe.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones cs: " + cs.getPuntuacionTotal() + " " + cs.getPuntuacionEconomia() + " "+ cs.getPuntuacionSocial() + " "+ cs.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones upyd: " + upyd.getPuntuacionTotal() + " " + upyd.getPuntuacionEconomia() + " "+ upyd.getPuntuacionSocial() + " "+ upyd.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones podemos: " + podemos.getPuntuacionTotal() + " " + podemos.getPuntuacionEconomia() + " "+ podemos.getPuntuacionSocial() + " "+ podemos.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones iu: " + iu.getPuntuacionTotal() + " " + iu.getPuntuacionEconomia() + " "+ iu.getPuntuacionSocial() + " "+ iu.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones convergencia: " + convergencia.getPuntuacionTotal() + " " + convergencia.getPuntuacionEconomia() + " "+ convergencia.getPuntuacionSocial() + " "+ convergencia.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones pnv: " + pnv.getPuntuacionTotal() + " " + pnv.getPuntuacionEconomia() + " "+ pnv.getPuntuacionSocial() + " "+ pnv.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones erc: " + erc.getPuntuacionTotal() + " " + erc.getPuntuacionEconomia() + " "+ erc.getPuntuacionSocial() + " "+ erc.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones bildu: " + bildu.getPuntuacionTotal() + " " + bildu.getPuntuacionEconomia() + " "+ bildu.getPuntuacionSocial() + " "+ bildu.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones pacma: " + pacma.getPuntuacionTotal() + " " + pacma.getPuntuacionEconomia() + " "+ pacma.getPuntuacionSocial() + " "+ pacma.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones vox: " + vox.getPuntuacionTotal() + " " + vox.getPuntuacionEconomia() + " " + vox.getPuntuacionSocial() + " " + vox.getPuntuacionEstado() + " ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultados, menu);
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

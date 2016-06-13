package com.moonfish.testeleccionesgenerales2015.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.fragments.EstadisticasEncuestas;
import com.moonfish.testeleccionesgenerales2015.fragments.EstadisticasTest;
import com.moonfish.testeleccionesgenerales2015.fragments.ResultadosDetallados;
import com.moonfish.testeleccionesgenerales2015.fragments.ResultadosTest;
import com.moonfish.testeleccionesgenerales2015.model.ResultadosPartido;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ResultadosActivity extends AppCompatActivity {

    //Variables de los partidos donde iremos almacenando los resultados
    public ResultadosPartido pp = new ResultadosPartido();
    public ResultadosPartido psoe = new ResultadosPartido();
    public ResultadosPartido cs = new ResultadosPartido();
    public ResultadosPartido podemos = new ResultadosPartido();
    //public ResultadosPartido iu = new ResultadosPartido();
    public ResultadosPartido upyd = new ResultadosPartido();
    public ResultadosPartido convergencia = new ResultadosPartido();
    public ResultadosPartido pnv = new ResultadosPartido();
    public ResultadosPartido erc = new ResultadosPartido();
    public ResultadosPartido bildu = new ResultadosPartido();
    public ResultadosPartido pacma = new ResultadosPartido();
    public ResultadosPartido vox = new ResultadosPartido();

    private ArrayList<ResultadosPartido> resultados;
    public static int num_preguntas;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    //Compartir
    private File picFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
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
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.selectedTabColor));

        tabLayout.setupWithViewPager(viewPager);

        //Identificamos el tipo de test a través del intent
        Intent intent = getIntent();
        pp = intent.getParcelableExtra("PP");
        psoe = intent.getParcelableExtra("PSOE");
        cs = intent.getParcelableExtra("CIUDADANOS");
        podemos = intent.getParcelableExtra("PODEMOS");
        //iu = intent.getParcelableExtra("IU");
        upyd = intent.getParcelableExtra("UPYD");
        convergencia= intent.getParcelableExtra("CONVERGENCIA");
        erc = intent.getParcelableExtra("ERC");
        pnv = intent.getParcelableExtra("PNV");
        bildu = intent.getParcelableExtra("EH-BILDU");
        pacma = intent.getParcelableExtra("PACMA");
        vox = intent.getParcelableExtra("VOX");
        num_preguntas=intent.getIntExtra("numero_preguntas",1);


        resultados = new ArrayList<>();
        resultados.add(pp);
        resultados.add(psoe);
        resultados.add(cs);
        resultados.add(podemos);
        //resultados.add(iu);
        resultados.add(upyd);
        resultados.add(convergencia);
        resultados.add(erc);
        resultados.add(pnv);
        resultados.add(bildu);
        resultados.add(pacma);
        resultados.add(vox);


        Log.i("puntuaciones", "Nombre partido: TOTAL ECONOMÍA SOCIEDAD ESTADO");
        Log.i("puntuaciones", "Puntuaciones pp: " + pp.getPuntuacionTotal() + " " + pp.getPuntuacionEconomia() + " "+ pp.getPuntuacionSocial() + " "+ pp.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones psoe: " + psoe.getPuntuacionTotal() + " " + psoe.getPuntuacionEconomia() + " "+ psoe.getPuntuacionSocial() + " "+ psoe.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones cs: " + cs.getPuntuacionTotal() + " " + cs.getPuntuacionEconomia() + " "+ cs.getPuntuacionSocial() + " "+ cs.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones upyd: " + upyd.getPuntuacionTotal() + " " + upyd.getPuntuacionEconomia() + " "+ upyd.getPuntuacionSocial() + " "+ upyd.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones podemos: " + podemos.getPuntuacionTotal() + " " + podemos.getPuntuacionEconomia() + " "+ podemos.getPuntuacionSocial() + " "+ podemos.getPuntuacionEstado() + " ");
        //Log.i("puntuaciones", "Puntuaciones iu: " + iu.getPuntuacionTotal() + " " + iu.getPuntuacionEconomia() + " "+ iu.getPuntuacionSocial() + " "+ iu.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones convergencia: " + convergencia.getPuntuacionTotal() + " " + convergencia.getPuntuacionEconomia() + " "+ convergencia.getPuntuacionSocial() + " "+ convergencia.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones pnv: " + pnv.getPuntuacionTotal() + " " + pnv.getPuntuacionEconomia() + " "+ pnv.getPuntuacionSocial() + " "+ pnv.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones erc: " + erc.getPuntuacionTotal() + " " + erc.getPuntuacionEconomia() + " "+ erc.getPuntuacionSocial() + " "+ erc.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones bildu: " + bildu.getPuntuacionTotal() + " " + bildu.getPuntuacionEconomia() + " "+ bildu.getPuntuacionSocial() + " "+ bildu.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones pacma: " + pacma.getPuntuacionTotal() + " " + pacma.getPuntuacionEconomia() + " "+ pacma.getPuntuacionSocial() + " "+ pacma.getPuntuacionEstado() + " ");
        Log.i("puntuaciones", "Puntuaciones vox: " + vox.getPuntuacionTotal() + " " + vox.getPuntuacionEconomia() + " " + vox.getPuntuacionSocial() + " " + vox.getPuntuacionEstado() + " ");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ResultadosTest(), "GLOBALES");
        adapter.addFragment(new ResultadosDetallados(), "DETALLADOS");

        viewPager.setAdapter(adapter);
    }

    public ArrayList<ResultadosPartido> getResultados(){
        return this.resultados;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resultados, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        if(id == R.id.share){
            shareit();
        }
        return super.onOptionsItemSelected(item);
    }

    //Cambia de TAB pasándole el número correspondiente
    public void switchFragment(int target){
        viewPager.setCurrentItem(target);
    }

    //Compartir
    public void shareit()
    {
        //ParseAnalytics
        ParseAnalytics.trackEventInBackground("ONSHARE_RESULTS");
        switchFragment(0);
        View view = getWindow().getDecorView();
        view.getRootView();
        String state = Environment.getExternalStorageState();
        try {
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File picDir = new File(Environment.getExternalStorageDirectory() + "/TE20D");
                if (!picDir.exists()) {
                    picDir.mkdir();
                }
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache(true);
                Bitmap bitmap = view.getDrawingCache();

                /**Funciones para detectar el tema y colores/imagenes de fondo**/
                final Canvas canvas = new Canvas(bitmap);

                // Get current theme to know which background to use
                final Resources.Theme theme = this.getTheme();
                final TypedArray ta = theme
                        .obtainStyledAttributes(new int[]{android.R.attr.windowBackground});
                final int res = ta.getResourceId(0, 0);
                final Drawable background = this.getResources().getDrawable(res);

                // Draw background
                background.draw(canvas);

                // Draw views
                view.draw(canvas);

                String fileName = "resultPicture" + ".png";
                picFile = new File(picDir + "/" + fileName);
                try {
                    picFile.createNewFile();
                    FileOutputStream picOut = new FileOutputStream(picFile);

                    //Toño
                    final BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(picFile.getPath(), options);

                    bitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), (int) (bitmap.getHeight()));

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, picOut);

                    picOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Out of memory", Toast.LENGTH_LONG).show();
                }
                view.destroyDrawingCache();
            } else {
                //Error

            }
            String text="";
            switch (viewPager.getCurrentItem()){
                case 0: text="Estos han sido mis resultados en @testelecciones.Descárgala en https://goo.gl/T0C426 #Elecciones26J #Indecisos26J";break;
                case 1:text="Estos han sido mis resultados en @testelecciones.Descárgala en https://goo.gl/T0C426 #Elecciones26J #Indecisos26J";break;
            }
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("image/png");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
            sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(picFile.getAbsolutePath()));
            startActivity(Intent.createChooser(sharingIntent, "Compartir"));
        }catch (Exception e){

        }
    }
}

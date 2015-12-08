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
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.fragments.EstadisticasEncuestas;
import com.moonfish.testeleccionesgenerales2015.fragments.EstadisticasTest;
import com.parse.ParseAnalytics;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afll on 05/10/2015.
 */
public class EstadisticasActivity extends AppCompatActivity {

    private PieChart pieChart;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    //Compartir
    private File picFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        //pieChart = (PieChart) findViewById(R.id.chart);

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

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.selectedTabColor));

        tabLayout.setupWithViewPager(viewPager);

        //configureChart();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EstadisticasTest(), "TEST");
        adapter.addFragment(new EstadisticasEncuestas(), "ENCUESTAS");

        viewPager.setAdapter(adapter);
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

    //Compartir
    public void shareit()
    {
        //ParseAnalytics
        ParseAnalytics.trackEventInBackground("ONSHARE_ESTADISTICAS");

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
                case 0: text="Así van las estadísticas generales en @testelecciones https://goo.gl/T0C426 #Elecciones20D #Indecisos20D";break;
                case 1:text="Así van las encuestas en @testelecciones https://goo.gl/T0C426 #Elecciones20D #Indecisos20D";break;
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

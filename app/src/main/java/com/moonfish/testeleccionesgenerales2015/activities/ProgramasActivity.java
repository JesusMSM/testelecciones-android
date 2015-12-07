package com.moonfish.testeleccionesgenerales2015.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.adapters.PartidosProgramasAdapter;
import com.moonfish.testeleccionesgenerales2015.model.PartidoProgramas;

import java.util.ArrayList;

/**
 * Created by Afll on 05/10/2015.
 */
public class ProgramasActivity extends AppCompatActivity {

    private ArrayList<PartidoProgramas> partidos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programas);

        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setTitle(R.string.titulo_programas);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ProgramasActivity.this);
            }
        });
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarColor)));

        setupRecyclerView();


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
    private void setupRecyclerView() {

        partidos = new ArrayList<>();
        partidos.add(new PartidoProgramas(R.drawable.logo_pp));
        partidos.add(new PartidoProgramas(R.drawable.logo_psoe));
        partidos.add(new PartidoProgramas(R.drawable.logo_podemos));
        partidos.add(new PartidoProgramas(R.drawable.logo_ciudadanos));
        partidos.add(new PartidoProgramas(R.drawable.logo_upyd));
        partidos.add(new PartidoProgramas(R.drawable.logo_iu));
        partidos.add(new PartidoProgramas(R.drawable.logo_pacma));
        partidos.add(new PartidoProgramas(R.drawable.logo_convergencia));
        partidos.add(new PartidoProgramas(R.drawable.logo_erc));
        partidos.add(new PartidoProgramas(R.drawable.logo_pnv));
        partidos.add(new PartidoProgramas(R.drawable.logo_bildu));
        partidos.add(new PartidoProgramas(R.drawable.logo_vox));


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Let’s a grid with 2 columns.
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        PartidosProgramasAdapter adapter = new PartidosProgramasAdapter(partidos);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        //populateRecyclerView(recyclerView);

        final View toolbarContainer = findViewById(R.id.toolbar_container);
        final View toolbar = findViewById(R.id.toolbar);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (Math.abs(toolbarContainer.getTranslationY()) > toolbar.getHeight()) {
                        hideToolbar();
                    } else {
                        showToolbar();
                    }
                }
            }
            private void showToolbar() {
                toolbarContainer
                        .animate()
                        .translationY(0)
                        .start();
            }
            private void hideToolbar() {
                toolbarContainer
                        .animate()
                        .translationY(-toolbar.getBottom())
                        .start();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    hideToolbarBy(dy);
                } else {
                    showToolbarBy(dy);
                }
            }

            private void hideToolbarBy(int dy) {
                if (cannotHideMore(dy)) {
                    toolbarContainer.setTranslationY(-toolbar.getBottom());
                } else {
                    toolbarContainer.setTranslationY(toolbarContainer.getTranslationY() - dy);
                }
            }
            private boolean cannotHideMore(int dy) {
                return Math.abs(toolbarContainer.getTranslationY() - dy) > toolbar.getBottom();
            }
            private void showToolbarBy(int dy) {
                if (cannotShowMore(dy)) {
                    toolbarContainer.setTranslationY(0);
                } else {
                    toolbarContainer.setTranslationY(toolbarContainer.getTranslationY() - dy);
                }
            }
            private boolean cannotShowMore(int dy) {
                return toolbarContainer.getTranslationY() - dy > 0;
            }
        });
    }

}

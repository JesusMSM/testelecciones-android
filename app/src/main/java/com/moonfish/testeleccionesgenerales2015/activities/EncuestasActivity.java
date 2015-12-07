package com.moonfish.testeleccionesgenerales2015.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.adapters.MyRecyclerViewAdapter;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afll on 05/10/2015.
 */
public class EncuestasActivity extends AppCompatActivity {

    List<Object> items = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public String jsonEncuestas = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestas);

        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(EncuestasActivity.this);
            }
        });

        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarColor)));

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.listEncuestas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //getEncuestasFromParse();


        addItems();
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

    public void addItems(){
        items.add("ENCUESTA_HEADER");
        items.add("ENCUESTA_HEADER");
        items.add("ENCUESTA_HEADER");

        mAdapter = new MyRecyclerViewAdapter(this,items);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getEncuestasFromParse(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Encuesta");
        try {
            ParseObject object = query.whereEqualTo("title", "Encuestas Debug").getFirst();
            jsonEncuestas = (String) object.get("json");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void readEncuestasJSON(){

    }
}

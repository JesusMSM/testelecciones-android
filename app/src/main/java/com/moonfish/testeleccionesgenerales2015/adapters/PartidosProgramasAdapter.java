package com.moonfish.testeleccionesgenerales2015.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.activities.ProgramaContentActivity;
import com.moonfish.testeleccionesgenerales2015.activities.ProgramasActivity;
import com.moonfish.testeleccionesgenerales2015.model.PartidoProgramas;

import java.util.List;

/**
 * Created by JesúsManuel on 17/10/2015.
 */
public class PartidosProgramasAdapter extends RecyclerView.Adapter<PartidosProgramasAdapter.PartidoViewHolder>{



    public static class PartidoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        ImageView personPhoto;
        Context context;

        PartidoViewHolder(Context c, View itemView) {
            super(itemView);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            context = c;
            cv = (CardView)itemView.findViewById(R.id.cv);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(context,"The Item Clicked is: "+getPosition(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(v.getContext(),ProgramaContentActivity.class);
            i.putExtra("indice", getPosition());
            context.startActivity(i);

        }
    }
    List<PartidoProgramas> partidos;

    public PartidosProgramasAdapter(List<PartidoProgramas> partidos){
        this.partidos = partidos;
    }

    @Override
    public int getItemCount() {
        return partidos.size();
    }

    @Override
    public PartidoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_partido, viewGroup, false);
        PartidoViewHolder pvh = new PartidoViewHolder(viewGroup.getContext(),v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(PartidoViewHolder partidoViewHolder, int i) {
        partidoViewHolder.personPhoto.setImageResource(partidos.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
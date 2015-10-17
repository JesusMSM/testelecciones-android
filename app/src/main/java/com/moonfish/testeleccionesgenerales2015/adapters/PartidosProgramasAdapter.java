package com.moonfish.testeleccionesgenerales2015.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.model.PartidoProgramas;

import java.util.List;

/**
 * Created by JesúsManuel on 17/10/2015.
 */
public class PartidosProgramasAdapter extends RecyclerView.Adapter<PartidosProgramasAdapter.PartidoViewHolder>{

    public static class PartidoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView personPhoto;

        PartidoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
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
        PartidoViewHolder pvh = new PartidoViewHolder(v);
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
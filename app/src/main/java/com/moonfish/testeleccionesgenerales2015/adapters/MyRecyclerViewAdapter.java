package com.moonfish.testeleccionesgenerales2015.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonfish.testeleccionesgenerales2015.R;
import com.moonfish.testeleccionesgenerales2015.viewholders.EncuestaContentViewHolder;
import com.moonfish.testeleccionesgenerales2015.viewholders.EncuestaHeaderViewHolder;

import java.util.List;

/**
 * Created by Afll on 05/12/2015.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Object> items;
    Context context;

    private final int ENCUESTA_HEADER = 0, ENCUESTA_CONTENT = 1;

    public MyRecyclerViewAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).equals("ENCUESTA_HEADER")){
            return ENCUESTA_HEADER;
        }
        if(items.get(position).equals("ENCUESTA_CONTENT")){
            return ENCUESTA_CONTENT;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (i){
            case ENCUESTA_HEADER:
                View v1 = inflater.inflate(R.layout.recyclerview_item_encuestaheader,viewGroup,false);
                viewHolder = new EncuestaHeaderViewHolder(v1);
                break;
            case ENCUESTA_CONTENT:
                View v2 = inflater.inflate(R.layout.recyclerview_item_encuestacontent,viewGroup,false);
                viewHolder = new EncuestaContentViewHolder(v2);
                break;
            default:
                viewHolder=null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()) {
            case ENCUESTA_HEADER:
                EncuestaHeaderViewHolder vh1 = (EncuestaHeaderViewHolder) viewHolder;
                configureEncuestaHeaderViewholder(vh1, i);
                break;
            case ENCUESTA_CONTENT:
                EncuestaContentViewHolder vh2 = (EncuestaContentViewHolder) viewHolder;
                configureEncuestaContentViewHolder(vh2,i);
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void configureEncuestaHeaderViewholder(final EncuestaHeaderViewHolder vh, final int position){
        vh.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!vh.isContentDisplayed){
                    //ShowContent
                    items.add(position+1, "ENCUESTA_CONTENT");
                    notifyItemInserted(position);
                    notifyDataSetChanged();
                    vh.isContentDisplayed = true;
                }else{
                    //HideContent
                    items.remove(position+1);
                    notifyItemRemoved(position+1);
                    notifyDataSetChanged();
                    vh.isContentDisplayed = false;
                }
            }
        });
    }

    public void configureEncuestaContentViewHolder(EncuestaContentViewHolder vh, int position){

    }
}

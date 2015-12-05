package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Afll on 05/12/2015.
 */
public class EncuestaContentViewHolder extends RecyclerView.ViewHolder{

    public TextView title;
    public EncuestaContentViewHolder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.titleContent);
    }
}

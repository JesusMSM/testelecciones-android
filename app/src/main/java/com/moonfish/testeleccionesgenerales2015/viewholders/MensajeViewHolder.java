package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Jesus on 06/12/2015.
 */
public class MensajeViewHolder extends RecyclerView.ViewHolder{
    public TextView mensaje;

    public MensajeViewHolder(View v) {
        super(v);
        mensaje = (TextView) v.findViewById(R.id.mensaje);
    }
}
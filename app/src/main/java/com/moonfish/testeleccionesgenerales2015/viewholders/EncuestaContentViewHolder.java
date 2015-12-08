package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Afll on 05/12/2015.
 */
public class EncuestaContentViewHolder extends RecyclerView.ViewHolder{

    public TextView respuesta1,respuesta2,respuesta3,respuesta4,respuesta5,respuesta6,respuesta7,respuesta8,respuesta9,respuesta10;
    public EncuestaContentViewHolder(View itemView) {
        super(itemView);
        respuesta1= (TextView) itemView.findViewById(R.id.respuesta1);
        respuesta2= (TextView) itemView.findViewById(R.id.respuesta2);
        respuesta3= (TextView) itemView.findViewById(R.id.respuesta3);
        respuesta4= (TextView) itemView.findViewById(R.id.respuesta4);
        respuesta5= (TextView) itemView.findViewById(R.id.respuesta5);
        respuesta6= (TextView) itemView.findViewById(R.id.respuesta6);
        respuesta7= (TextView) itemView.findViewById(R.id.respuesta7);
        respuesta8= (TextView) itemView.findViewById(R.id.respuesta8);
        respuesta9= (TextView) itemView.findViewById(R.id.respuesta9);
        respuesta10= (TextView) itemView.findViewById(R.id.respuesta10);
    }
}

package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Afll on 05/12/2015.
 */
public class EncuestaHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView title,fecha;
    public LinearLayout layout;
    public boolean isContentDisplayed = false;

    public EncuestaHeaderViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.titleHeader);
        fecha = (TextView) itemView.findViewById(R.id.fecha);
        layout = (LinearLayout) itemView.findViewById(R.id.layoutClickable);
    }
}

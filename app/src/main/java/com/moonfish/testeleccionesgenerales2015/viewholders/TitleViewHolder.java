package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Jesus on 06/12/2015.
 */
public class TitleViewHolder extends RecyclerView.ViewHolder{
    public TextView title;
    public LinearLayout link;
    public TitleViewHolder(View v) {
        super(v);
        title = (TextView) v.findViewById(R.id.title);
        link = (LinearLayout) v.findViewById(R.id.title_layout);

    }

}
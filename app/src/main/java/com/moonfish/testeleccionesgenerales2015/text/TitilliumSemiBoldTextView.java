package com.moonfish.testeleccionesgenerales2015.text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by carlosolmedo on 23/11/15.
 */
public class TitilliumSemiBoldTextView extends TextView {

    public TitilliumSemiBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "Titillium-Semibold.otf");
        setTypeface(font);
    }
}

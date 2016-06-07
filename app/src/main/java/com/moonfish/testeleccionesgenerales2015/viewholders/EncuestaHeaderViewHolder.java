package com.moonfish.testeleccionesgenerales2015.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonfish.testeleccionesgenerales2015.R;

/**
 * Created by Afll on 05/12/2015.
 */
public class EncuestaHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView title,fecha,hasRespondido;
    public LinearLayout layout;
    public LinearLayout respuestasLayout;
    public LinearLayout respuestasDoneLayout;
    public boolean isContentDisplayed = false;

    public boolean isContentDisplayed() {
        return isContentDisplayed;
    }

    public void setIsContentDisplayed(boolean isContentDisplayed) {
        this.isContentDisplayed = isContentDisplayed;
    }

    public EncuestaHeaderViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.titleHeader);
        fecha = (TextView) itemView.findViewById(R.id.fecha);
        layout = (LinearLayout) itemView.findViewById(R.id.layoutClickable);
        respuestasLayout = (LinearLayout) itemView.findViewById(R.id.respuestasLayout);
        respuestasDoneLayout = (LinearLayout) itemView.findViewById(R.id.respuestasDoneLayout);
        hasRespondido = (TextView) itemView.findViewById(R.id.hasRespondido);

    }

    //Formateadores para la fecha ( hace X minutos)
    public static String getTimeAgo(String timeCTE) {
        Log.i("FECHA_TIME AGO", timeCTE);
        long time = 0;

        try{
            long epoch = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").parse(timeCTE).getTime();
            time=epoch;
        }catch(Exception e){
            e.printStackTrace();
        }
        final long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < C.MINUTE_MILLIS) {
            return "ahora";
        } else if (diff < 2 * C.MINUTE_MILLIS) {
            return "hace un minuto";
        } else if (diff < 50 * C.MINUTE_MILLIS) {
            return "hace "+diff / C.MINUTE_MILLIS + " minutos";
        } else if (diff < 90 * C.MINUTE_MILLIS) {
            return "hace una hora";
        } else if (diff < 24 * C.HOUR_MILLIS) {
            return "hace "+diff / C.HOUR_MILLIS + " horas";
        } else if (diff < 48 * C.HOUR_MILLIS) {
            return "ayer";
        } else {
            return "hace "+diff / C.DAY_MILLIS + " d\u00edas";
        }
    }
    public class C {
        /** One second (in milliseconds) */
        public static final int _A_SECOND = 1000;
        /** One minute (in milliseconds) */
        public static final int MINUTE_MILLIS = 60 * _A_SECOND;
        /** One hour (in milliseconds) */
        public static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        /** One day (in milliseconds) */
        public static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    }
}

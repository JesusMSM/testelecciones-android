package com.moonfish.testeleccionesgenerales2015.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jorge_cmata on 3/12/15.
 */
public class ResultadosPartido implements Parcelable{
    public int puntuacionTotal;
    public int puntuacionEconomia;
    public int puntuacionSocial;
    public int puntuacionEstado;

    public ResultadosPartido(){
        this.puntuacionTotal = 0;
        this.puntuacionEconomia = 0;
        this.puntuacionSocial = 0;
        this.puntuacionEstado = 0;
    }

    public ResultadosPartido(Parcel in) {
        puntuacionTotal = in.readInt();
        puntuacionEconomia = in.readInt();
        puntuacionSocial = in.readInt();
        puntuacionEstado = in.readInt();
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public void addPuntuacionTotal(int puntuacionPregunta){
        this.puntuacionTotal += puntuacionPregunta;
    }

    public int getPuntuacionEconomia() {
        return puntuacionEconomia;
    }

    public void setPuntuacionEconomia(int puntuacionEconomia) {
        this.puntuacionEconomia = puntuacionEconomia;
    }

    public void addPuntuacionEconomia(int puntuacionPregunta){
        this.puntuacionEconomia += puntuacionPregunta;
    }

    public int getPuntuacionSocial() {
        return puntuacionSocial;
    }

    public void setPuntuacionSocial(int puntuacionSocial) {
        this.puntuacionSocial = puntuacionSocial;
    }

    public void addPuntuacionSocial(int puntuacionPregunta){
        this.puntuacionSocial += puntuacionPregunta;
    }

    public int getPuntuacionEstado() {
        return puntuacionEstado;
    }

    public void setPuntuacionEstado(int puntuacionEstado) {
        this.puntuacionEstado = puntuacionEstado;
    }

    public void addPuntuacionEstado(int puntuacionPregunta){
        this.puntuacionEstado += puntuacionPregunta;
    }
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(puntuacionTotal);
        dest.writeInt(puntuacionEconomia);
        dest.writeInt(puntuacionSocial);
        dest.writeInt(puntuacionEstado);
    }

    public static final Parcelable.Creator<ResultadosPartido> CREATOR = new Parcelable.Creator<ResultadosPartido>()
    {
        public ResultadosPartido createFromParcel(Parcel in)
        {
            return new ResultadosPartido(in);
        }
        public ResultadosPartido[] newArray(int size)
        {
            return new ResultadosPartido[size];
        }
    };

}

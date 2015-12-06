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
    public String partido;
    public int puntuacionTotalEscalada;
    public int puntuacionEconomiaEscalada;
    public int puntuacionSocialEscalada;
    public int puntuacionEstadoEscalada;

    public String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPuntuacionTotalEscalada() {
        return puntuacionTotalEscalada;
    }

    public void setPuntuacionTotalEscalada(int puntuacionTotalEscalada) {
        this.puntuacionTotalEscalada = puntuacionTotalEscalada;
    }

    public int getPuntuacionEconomiaEscalada() {
        return puntuacionEconomiaEscalada;
    }

    public void setPuntuacionEconomiaEscalada(int puntuacionEconomiaEscalada) {
        this.puntuacionEconomiaEscalada = puntuacionEconomiaEscalada;
    }

    public int getPuntuacionSocialEscalada() {
        return puntuacionSocialEscalada;
    }

    public void setPuntuacionSocialEscalada(int puntuacionSocialEscalada) {
        this.puntuacionSocialEscalada = puntuacionSocialEscalada;
    }

    public int getPuntuacionEstadoEscalada() {
        return puntuacionEstadoEscalada;
    }

    public void setPuntuacionEstadoEscalada(int puntuacionEstadoEscalada) {
        this.puntuacionEstadoEscalada = puntuacionEstadoEscalada;
    }

    public ResultadosPartido(){
        this.puntuacionTotal = 0;
        this.puntuacionEconomia = 0;
        this.puntuacionSocial = 0;
        this.puntuacionEstado = 0;

        this.partido = "";
        this.color = "";
        puntuacionTotalEscalada=0;
        puntuacionEconomiaEscalada=0;
        puntuacionSocialEscalada=0;
        puntuacionEstadoEscalada=0;
    }

    public ResultadosPartido(Parcel in) {
        puntuacionTotal = in.readInt();
        puntuacionEconomia = in.readInt();
        puntuacionSocial = in.readInt();
        puntuacionEstado = in.readInt();
        partido = in.readString();
        color = in.readString();
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

    public void addPartido(String partido){
        this.partido = partido;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(puntuacionTotal);
        dest.writeInt(puntuacionEconomia);
        dest.writeInt(puntuacionSocial);
        dest.writeInt(puntuacionEstado);
        dest.writeString(partido);
        dest.writeString(color);
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

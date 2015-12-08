package com.moonfish.testeleccionesgenerales2015.model;

/**
 * Created by MOONFISH on 04/09/2015.
 */
public class Mensaje {
    private String mensaje;
    private int destino;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public Mensaje(String mensaje, int destino) {

        this.mensaje = mensaje;
        this.destino = destino;
    }

    public Mensaje(String mensaje) {

        this.mensaje = mensaje;
    }
}
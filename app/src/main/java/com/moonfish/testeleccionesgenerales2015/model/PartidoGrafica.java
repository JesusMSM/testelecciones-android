package com.moonfish.testeleccionesgenerales2015.model;

/**
 * Created by Jesus on 07/12/2015.
 */
public class PartidoGrafica {
    private String nombre;
    private String color;
    private int porcentaje;

    public PartidoGrafica() {
    }

    public PartidoGrafica(String nombre, String color, int porcentaje) {
        this.nombre = nombre;
        this.color = color;
        this.porcentaje = porcentaje;
    }

    public String getPartido() {
        return nombre;
    }

    public void setPartido(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}

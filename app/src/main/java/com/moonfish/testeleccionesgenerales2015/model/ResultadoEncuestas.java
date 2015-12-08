package com.moonfish.testeleccionesgenerales2015.model;

/**
 * Created by Afll on 08/12/2015.
 */
public class ResultadoEncuestas {
    public int id;
    public String nombreRespuesta;
    public int puntuacion;
    public String tituloPregunta;
    public String color;

    public ResultadoEncuestas (String id, String nombreRespuesta, int puntuacion, String tituloPregunta,String color){
        this.nombreRespuesta = nombreRespuesta;
        this.puntuacion = puntuacion;
        this.tituloPregunta = tituloPregunta;
        this.id = Integer.parseInt(id);
        this.color = color;
    }
}

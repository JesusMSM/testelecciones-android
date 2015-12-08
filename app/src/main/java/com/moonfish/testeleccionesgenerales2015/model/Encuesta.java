package com.moonfish.testeleccionesgenerales2015.model;

import java.util.List;

/**
 * Created by Afll on 07/12/2015.
 */
public class Encuesta {
    public String id,titulo,fecha;
    public List<String> respuestas;

    public Encuesta(String id,String titulo,String fecha, List<String> respuestas){
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.respuestas = respuestas;
    }
}

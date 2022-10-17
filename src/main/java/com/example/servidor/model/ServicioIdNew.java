package com.example.servidor.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ServicioIdNew implements Serializable {
    private String nombre;

    private String centroDeportivo;

    public ServicioIdNew() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(String centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}

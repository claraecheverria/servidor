package com.example.servidor.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
//@Embeddable
public class ServicioId implements Serializable {
//    @Column(nullable = false)
    private String nombre;
//    @ManyToOne
//    @JoinColumn(name = "centro_deportivo_servicio_nombre", nullable = false)
    private CentroDeportivo centroDeportivoServicio;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CentroDeportivo getCentroDeportivoServicio() {
        return centroDeportivoServicio;
    }

    public void setCentroDeportivoServicio(CentroDeportivo centroDeportivoServicio) {
        this.centroDeportivoServicio = centroDeportivoServicio;
    }

    public ServicioId() {
    }

    public ServicioId(String nombre, CentroDeportivo centroDeportivoServicio) {
        this.nombre = nombre;
        this.centroDeportivoServicio = centroDeportivoServicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicioId that = (ServicioId) o;
        return nombre.equals(that.nombre) && centroDeportivoServicio.equals(that.centroDeportivoServicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, centroDeportivoServicio);
    }
}

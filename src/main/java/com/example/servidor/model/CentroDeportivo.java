package com.example.servidor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "centro_deportivo")
public class CentroDeportivo {
    @Id
    private String nombre;

    @OneToMany(mappedBy = "centroDeportivo")
    private List<UserCentroDeportivo> usersCentroDep;

    public CentroDeportivo() {
    }

    public CentroDeportivo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

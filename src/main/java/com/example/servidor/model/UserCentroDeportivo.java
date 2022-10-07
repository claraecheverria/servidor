package com.example.servidor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CENTRO_DEP")
public class UserCentroDeportivo extends User{

    @ManyToOne
    @JoinColumn(name = "centro_dep_nombre")
    private CentroDeportivo centroDeportivo;

    public UserCentroDeportivo(String email, Long cedula, String nombre, String password, Long telefono, CentroDeportivo centroDeportivo) {
        super(email, cedula, nombre, password, telefono);
        this.centroDeportivo = centroDeportivo;
    }

    public UserCentroDeportivo() {
    }

    public CentroDeportivo getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivo centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}

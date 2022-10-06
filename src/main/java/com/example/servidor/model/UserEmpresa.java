package com.example.servidor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("EMPRESA")
public class UserEmpresa extends User{

    @ManyToOne
    @JoinColumn(name = "empresa_nombre")
    private Empresa empresa;

    public UserEmpresa(String email, Long cedula, String nombre, String password, Long telefono, Empresa empresa) {
        super(email, cedula, nombre, password, telefono);
        this.empresa = empresa;
    }

    public UserEmpresa() {
    }
}

package com.example.servidor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("EMPRESA")
public class UserEmpresa extends User{

    @ManyToOne
    @JoinColumn(name = "empresa_nombre")
    @NotNull
    private Empresa empresa;

    public UserEmpresa(String email, Long cedula, String nombre, String password, Long telefono, Empresa empresa) {
        super(email, cedula, nombre, password, telefono);
        this.empresa = empresa;
    }

    public UserEmpresa() {
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}

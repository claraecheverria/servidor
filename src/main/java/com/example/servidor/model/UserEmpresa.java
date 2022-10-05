package com.example.servidor.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserEmpresa extends User{

    @ManyToOne
    @JoinColumn(name = "empresa_nombre")
    private Empresa empresa;
}

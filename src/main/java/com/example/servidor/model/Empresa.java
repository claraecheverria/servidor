package com.example.servidor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="empresa")
public class Empresa {
    @Id
    private String nombre;

    @OneToMany(mappedBy = "empresa")
    private List<UserEmpresa> usersEmpresa;

    @OneToMany(mappedBy = "empresaEmpl")
    private List<UserEmpleado> usersEmpleados;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }
}

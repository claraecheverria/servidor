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

    private String rut;
    private String razonSocial;
    private String direccion;

    @OneToMany(mappedBy = "empresa")
    private List<UserEmpresa> usersEmpresa;

    @OneToMany(mappedBy = "empresaEmpl")
    private List<UserEmpleado> usersEmpleados;

    //CONSTRUCTORES


    public Empresa(String nombre, String rut, String razonSocial, String direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
    }

    public Empresa() {
    }

    //GETTERS Y SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public String getRUT() {
        return rut;
    }

    public void setRUT(String RUT) {
        this.rut = RUT;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

package com.example.servidor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private String rut;
    private String razonSocial;
    private String direccion;

    @OneToMany(mappedBy = "centroDeportivo")
//    @JsonManagedReference
    @JsonIgnoreProperties("centroDeportivo")
    private List<UserCentroDeportivo> usersCentroDep;

    @OneToMany(mappedBy = "centroDeportivoServicio")
//    @JsonManagedReference
    @JsonIgnoreProperties("centroDeportivoServicio")
    private List<Servicio> serviciosCentroDep;

    //CONSTRUCTORES
    public CentroDeportivo() {
    }

    public CentroDeportivo(String nombre, String rut, String razonSocial, String direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

//    public List<UserCentroDeportivo> getUsersCentroDep() {
//        return usersCentroDep;
//    }
//
//    public void setUsersCentroDep(List<UserCentroDeportivo> usersCentroDep) {
//        this.usersCentroDep = usersCentroDep;
//    }
//
//    public List<Servicio> getServiciosCentroDep() {
//        return serviciosCentroDep;
//    }
//
//    public void setServiciosCentroDep(List<Servicio> serviciosCentroDep) {
//        this.serviciosCentroDep = serviciosCentroDep;
//    }

    @Override
    public String toString() {
        return "CentroDeportivo{" +
                "nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", direccion='" + direccion + '\'' +
                ", usersCentroDep=" + usersCentroDep +
                ", serviciosCentroDep=" + serviciosCentroDep +
                '}';
    }
}

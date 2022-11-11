package com.example.servidor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueTodo", columnNames = { "fecha", "servicio_nombre","servicio_centro_dep" ,"userEmpleado" }) })
public class Ingreso {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @JsonIgnoreProperties("ingresos")
    @ManyToOne
    @JoinColumn(name = "servicio_nombre", referencedColumnName = "nombre")
    @JoinColumn(name = "servicio_centro_dep", referencedColumnName = "centro_dep_nombre")
    private Servicio servicio;

    @JsonIgnoreProperties("ingresos")
    @ManyToOne
    @JoinColumn(name = "userEmpleado")
    private UserEmpleado userEmpleado;

    //CONSTRUCTORES

    public Ingreso() {
    }

    public Ingreso(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Servicio servicio, UserEmpleado userEmpleado) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.servicio = servicio;
        this.userEmpleado = userEmpleado;
    }

    //GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public UserEmpleado getUserEmpleado() {
        return userEmpleado;
    }

    public void setUserEmpleado(UserEmpleado userEmpleado) {
        this.userEmpleado = userEmpleado;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}

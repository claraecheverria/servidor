package com.example.servidor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueFechaCanchaYHoras", columnNames = { "fecha", "cancha_nombre","cancha_centro_dep" ,"horaInicio", "horaFin" }) })
public class Reserva {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    @ManyToOne
    @JoinColumn(name = "cancha_nombre", referencedColumnName = "nombre")
    @JoinColumn(name = "cancha_centro_dep", referencedColumnName = "centro_dep_nombre")
    private Cancha cancha;

    @ManyToMany(mappedBy = "reservasHechas")
    private List<UserEmpleado> usuariosInvitados;

    //CONSTRUCTORES
    public Reserva() {
    }

    public Reserva(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Cancha cancha, List<UserEmpleado> usuariosInvitados) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cancha = cancha;
        this.usuariosInvitados = usuariosInvitados;
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

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public List<UserEmpleado> getUsuariosInvitados() {
        return usuariosInvitados;
    }

    public void setUsuariosInvitados(List<UserEmpleado> usuariosInvitados) {
        this.usuariosInvitados = usuariosInvitados;
    }
}

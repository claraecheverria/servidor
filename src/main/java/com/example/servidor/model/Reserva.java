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

    @ManyToOne
    @JoinColumn(name = "email_creador", referencedColumnName = "email")
    private UserEmpleado userEmplCreador;

    @ManyToMany
    @JoinTable(
            name = "reservas_empl_invitados",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "empl_id", referencedColumnName = "email"))
    private List<UserEmpleado> usuariosInvitados;

    private boolean fueIngresada;

    //CONSTRUCTORES
    public Reserva() {
    }

    public Reserva(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Cancha cancha, UserEmpleado userEmplCreador, List<UserEmpleado> usuariosInvitados) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cancha = cancha;
        this.userEmplCreador = userEmplCreador;
        this.usuariosInvitados = usuariosInvitados;
        this.fueIngresada = false;
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

    public UserEmpleado getUserEmplCreador() {
        return userEmplCreador;
    }

    public void setUserEmplCreador(UserEmpleado userEmplCreador) {
        this.userEmplCreador = userEmplCreador;
    }

    public boolean isFueIngresada() {
        return fueIngresada;
    }

    public void setFueIngresada(boolean fueIngresada) {
        this.fueIngresada = fueIngresada;
    }
}

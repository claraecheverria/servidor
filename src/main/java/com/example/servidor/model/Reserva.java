package com.example.servidor.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Reserva {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
//    @JoinColumn(name = "cancha_nombre")
    @JoinColumn(name = "cancha_nombre", referencedColumnName = "nombre")
    @JoinColumn(name = "cancha_centro_dep", referencedColumnName = "centro_dep_nombre")
    private Cancha cancha;

    @ManyToMany(mappedBy = "reservasHechas")
    private List<UserEmpleado> usuariosInvitados;
}

package com.example.servidor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("CANCHA")
public class Cancha extends Servicio{
    private int cupos;

    @JsonIgnoreProperties("cancha")
    @OneToMany(mappedBy = "cancha")
    private List<Reserva> reservas;


    //CONSTRUCTORES

    public Cancha() {
    }

    public Cancha(String name, CentroDeportivo centroDeportivoServicio, Long precio, Set<DiasDeLaSemana> dias, LocalTime horaInicio, LocalTime horaFin, String descripcion, String tipo, Set<Imagen> imagenes, int cupos) {
        super(name, centroDeportivoServicio, precio, dias, horaInicio, horaFin, descripcion, tipo, imagenes);
        this.cupos = cupos;
    }

    public Cancha(String nombre, String centroDepNombre) {
        super(nombre, centroDepNombre);
    }
    //GETTERS Y SETTERS

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }
}

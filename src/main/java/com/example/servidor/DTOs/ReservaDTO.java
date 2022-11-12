package com.example.servidor.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDTO {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String canchaNombre;

    private String centroDepNombre;

    //CONSTRUCTORES

    public ReservaDTO() {
    }

    public ReservaDTO(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String canchaNombre, String centroDepNombre) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.canchaNombre = canchaNombre;
        this.centroDepNombre = centroDepNombre;
    }

    //GETTERS Y SETTERS


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

    public String getCanchaNombre() {
        return canchaNombre;
    }

    public void setCanchaNombre(String canchaNombre) {
        this.canchaNombre = canchaNombre;
    }

    public String getCentroDepNombre() {
        return centroDepNombre;
    }

    public void setCentroDepNombre(String centroDepNombre) {
        this.centroDepNombre = centroDepNombre;
    }
}

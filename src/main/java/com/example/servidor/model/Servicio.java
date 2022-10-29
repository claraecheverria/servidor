package com.example.servidor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("SERVICIO")
@Table(name = "servicio")
public class Servicio {

    @EmbeddedId
    private ServicioIdNew key;

    @ManyToOne
    @JoinColumn(name = "centro_dep_nombre")
    @MapsId("centroDeportivo")
//    @JsonBackReference
    private CentroDeportivo centroDeportivoServicio;
    private Long precio;

    @ElementCollection(targetClass = DiasDeLaSemana.class)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "servicio_dias", joinColumns = {@JoinColumn(name = "servicio_nombre", referencedColumnName = "nombre"), @JoinColumn(name = "servicio_centro_dep", referencedColumnName = "centro_dep_nombre")})
    private Set<DiasDeLaSemana> dias;

    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String descripcion;
    private String tipo; //este va a tener una opcion para seleccionar cuando se cree para hacer luego los filtros por tipo

    @ManyToMany(mappedBy = "serviciosFavoritos")
    private List<UserEmpleado> favoritos;

//    @OneToMany(mappedBy = "servicio")
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Imagen.class)
    @JoinTable(name = "servicio_imagenes", joinColumns = {@JoinColumn(name = "servicio_nombre", referencedColumnName = "nombre"), @JoinColumn(name = "servicio_centro_dep", referencedColumnName = "centro_dep_nombre")})
    private Set<Imagen> imagenes;


    //CONSTRUCTORES

    public Servicio() {
    }

    public Servicio(String name, CentroDeportivo centroDeportivoServicio, Long precio, Set<DiasDeLaSemana> dias, LocalTime horaInicio, LocalTime horaFin, String descripcion, String tipo, Set<Imagen> imagenes) {
        this.key = new ServicioIdNew();
        this.key.setNombre(name);
        this.key.setCentroDeportivo(centroDeportivoServicio.getNombre());
        this.centroDeportivoServicio = centroDeportivoServicio;
        this.precio = precio;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagenes = imagenes;
    }
    //GETTERS Y SETTERS


    public ServicioIdNew getKey() {
        return key;
    }

    public void setKey(ServicioIdNew key) {
        this.key = key;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Set<DiasDeLaSemana> getDias() {
        return dias;
    }

    public void setDias(Set<DiasDeLaSemana> dias) {
        this.dias = dias;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<UserEmpleado> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<UserEmpleado> favoritos) {
        this.favoritos = favoritos;
    }


    public CentroDeportivo getCentroDeportivoServicio() {
        return centroDeportivoServicio;
    }

    public void setCentroDeportivoServicio(CentroDeportivo centroDeportivoServicio) {
        this.centroDeportivoServicio = centroDeportivoServicio;
    }

    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
}

package com.example.servidor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("EMPLEADO")
public class UserEmpleado extends User{

    private LocalDate vencimientoCarne;

    private Long saldo;

    @ManyToOne
    @JoinColumn(name = "empresa_nombre")
    @NotNull
    private Empresa empresaEmpl;

    @ManyToMany
    @JoinTable(
            name = "servicios_fav",
            joinColumns = @JoinColumn(name = "empl_id"),
            inverseJoinColumns = {@JoinColumn(name = "servicio_nombre", referencedColumnName = "nombre"), @JoinColumn(name = "servicio_centro_dep", referencedColumnName = "centro_dep_nombre")})
    private List<Servicio> serviciosFavoritos;

    @OneToMany(mappedBy = "userEmplCreador")
    private List<Reserva> reservasHechas;

    @ManyToMany(mappedBy = "usuariosInvitados")
    private List<Reserva> reservasInvitados;

    @OneToMany(mappedBy = "userEmpleado")
    private List<Ingreso> ingresos;

    //CONSTRUCTORES
    public UserEmpleado(String email, Long cedula, String nombre, String password, Long telefono, LocalDate vencimientoCarne, Long saldo, Empresa empresaEmpl) {
        super(email, cedula, nombre, password, telefono);
        this.vencimientoCarne = vencimientoCarne;
        this.saldo = saldo;
        this.empresaEmpl = empresaEmpl;
//        this.serviciosFavoritos = new List<Servicio>();
    }

    public UserEmpleado() {
    }

    //GETTERS Y SETTERS
    public LocalDate getVencimientoCarne() {
        return vencimientoCarne;
    }

    public void setVencimientoCarne(LocalDate vencimientoCarne) {
        this.vencimientoCarne = vencimientoCarne;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Empresa getEmpresaEmpl() {
        return empresaEmpl;
    }

    public void setEmpresaEmpl(Empresa empresaEmpl) {
        this.empresaEmpl = empresaEmpl;
    }

    public List<Servicio> getServiciosFavoritos() {
        return serviciosFavoritos;
    }

    public void setServiciosFavoritos(List<Servicio> serviciosFavoritos) {
        this.serviciosFavoritos = serviciosFavoritos;
    }

    public List<Reserva> getReservasHechas() {
        return reservasInvitados;
    }

    public void setReservasHechas(List<Reserva> reservasHechas) {
        this.reservasInvitados = reservasHechas;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }
}

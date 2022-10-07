package com.example.servidor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("EMPLEADO")
public class UserEmpleado extends User{

//    @NotBlank(message = "vencimiento is mandatory")
    private LocalDate vencimientoCarne;

    private Long saldo;

    @ManyToOne
    @JoinColumn(name = "empresa_nombre")
    private Empresa empresaEmpl;
    public UserEmpleado(String email, Long cedula, String nombre, String password, Long telefono, LocalDate vencimientoCarne, Long saldo, Empresa empresaEmpl) {
        super(email, cedula, nombre, password, telefono);
        this.vencimientoCarne = vencimientoCarne;
        this.saldo = saldo;
        this.empresaEmpl = empresaEmpl;
    }

    public UserEmpleado() {
    }
}

package com.example.servidor.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class UserEmpleado extends User{

    @NotBlank(message = "vencimiento is mandatory")
    private LocalDate vencimientoCarne;

    private Long saldo;

    @ManyToOne
    @JoinColumn(name = "empresa_nombre")
    private Empresa empresaEmpl;
}

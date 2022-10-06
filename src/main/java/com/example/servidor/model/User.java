package com.example.servidor.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
@Table(name="users")
public abstract class User {
    @Id
    @Email
    private String email;

//    @NotBlank(message = "Cedula is mandatory")
    private Long cedula;
//    @NotBlank(message = "Name is mandatory")
    private String nombre;

//    @NotBlank(message = "Password is mandatory")
    private String password;
//    @Min(9)
    private Long telefono;


    public User(String nombre, String email, Long telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    public User(String email, Long cedula, String nombre, String password, Long telefono) {
        this.email = email;
        this.cedula = cedula;
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
    }

    public User() {//jpa no te deja tener un constrctor con pasando cosas

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

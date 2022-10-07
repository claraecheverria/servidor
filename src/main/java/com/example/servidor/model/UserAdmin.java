package com.example.servidor.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class UserAdmin extends User{
    public UserAdmin(String email, Long cedula, String nombre, String password, Long telefono) {
        super(email, cedula, nombre, password, telefono);
    }

    public UserAdmin() {
    }
}

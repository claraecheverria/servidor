package com.example.servidor.model;

import javax.persistence.*;

@Entity
public class Imagen {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    @JoinColumn()
    private Servicio servicio;


    public Imagen() {
    }

    public Imagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

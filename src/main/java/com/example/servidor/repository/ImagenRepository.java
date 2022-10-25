package com.example.servidor.repository;

import com.example.servidor.model.Imagen;
import org.springframework.data.repository.CrudRepository;

public interface ImagenRepository extends CrudRepository<Imagen, Long> {
}

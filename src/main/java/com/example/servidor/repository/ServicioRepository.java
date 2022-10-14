package com.example.servidor.repository;

import com.example.servidor.model.Servicio;
import com.example.servidor.model.ServicioId;
import com.example.servidor.model.ServicioIdNew;
import org.springframework.data.repository.CrudRepository;

public interface ServicioRepository extends CrudRepository<Servicio, ServicioIdNew> {
}

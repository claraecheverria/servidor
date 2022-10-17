package com.example.servidor.repository;

import com.example.servidor.model.Servicio;
import com.example.servidor.model.ServicioIdNew;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicioRepository extends CrudRepository<Servicio, ServicioIdNew> {
    @Query(value = "SELECT *, type FROM servicio s WHERE s.type = :type",
            nativeQuery = true)
    List<Servicio> findAllServicios(@Param("type") String type);
}

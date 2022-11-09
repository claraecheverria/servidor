package com.example.servidor.repository;

import com.example.servidor.model.Cancha;
import com.example.servidor.model.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva,Long> {

//    @Query(value = "SELECT * FROM reserva r WHERE s.type = :type",
//            nativeQuery = true)
//    List<Reserva> findAllServiciosCancha(@Param("type") String type);
    List<Reserva> findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivo(LocalDate fecha, String nombre, String centroDeportivo);

}

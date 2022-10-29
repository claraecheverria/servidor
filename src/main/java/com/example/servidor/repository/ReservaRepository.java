package com.example.servidor.repository;

import com.example.servidor.model.Cancha;
import com.example.servidor.model.Reserva;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva,Long> {
    List<Reserva> findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivo(LocalDate fecha, String nombre, String centroDeportivo);

}

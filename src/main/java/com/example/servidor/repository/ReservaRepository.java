package com.example.servidor.repository;

import com.example.servidor.model.Cancha;
import com.example.servidor.model.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva,Long> {
    List<Reserva> findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivo(LocalDate fecha, String nombre, String centroDeportivo);
//    @Query(value = "SELECT r FROM Reserva r INNER JOIN Reservas_empl e ON r.id = e.reserva_id WHERE e.empl_id = :email and r.fecha = :fecha and r.cancha_nombre = :canchaNombre and r.cancha_centro_dep = :centroDepNombre",
//            nativeQuery = true)
//    List<Reserva> findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivoAndEmailUserEmpl(
//            @Param("email") String email, @Param("fecha") LocalDate fecha, @Param("canchaNombre") String canchaNombre, @Param("centroDepNombre") String centroDepNombre);

    @Query(value = "SELECT r.* FROM Reserva r , Reservas_empl e WHERE e.reserva_id = r.id and e.empl_id = :email and r.fecha = :fecha and r.cancha_nombre = :canchaNombre and r.cancha_centro_dep = :centroDepNombre",
            nativeQuery = true)
    List<Reserva> findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivoAndEmailUserEmpl(
            @Param("email") String email, @Param("fecha") LocalDate fecha, @Param("canchaNombre") String canchaNombre, @Param("centroDepNombre") String centroDepNombre);

}

package com.example.servidor.repository;

import com.example.servidor.model.Cancha;
import com.example.servidor.model.Servicio;
import com.example.servidor.model.ServicioIdNew;
import com.example.servidor.model.UserEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServicioRepository extends CrudRepository<Servicio, ServicioIdNew> {
    @Query(value = "SELECT *, type FROM servicio s WHERE s.type = :type",
            nativeQuery = true)
    List<Servicio> findAllServicios(@Param("type") String type);

    @Query(value = "SELECT *, type FROM servicio s WHERE s.type = :type",
            nativeQuery = true)
    List<Cancha> findAllServiciosCancha(@Param("type") String type);

    @Query(value = "SELECT * FROM servicio s WHERE s.type = :type and s.centro_dep_nombre = :centroDepNombre",
    nativeQuery = true)
    List<Servicio> findServiciosByCentroDepAndType(@Param("type") String type, @Param("centroDepNombre") String centroDepNombre);

    @Query(value = "SELECT * FROM servicio s WHERE s.type = :type and s.centro_dep_nombre = :centroDepNombre",
            nativeQuery = true)
    List<Cancha> findCanchasByCentroDepAndType(@Param("type") String type, @Param("centroDepNombre") String centroDepNombre);

    @Query("select s from Servicio s inner join s.favoritos favoritos where favoritos.email = ?1")
    List<Servicio> findByFavoritos_Email(String email);

    List<Servicio> findByKey_CentroDeportivo(String centroDeportivo);

    Optional<Servicio> findByKey_NombreAndKey_CentroDeportivo(String nombre, String centroDeportivo);

    void deleteByFavoritosAndKey_NombreAndKey_CentroDeportivo(UserEmpleado favoritos, String nombre, String centroDeportivo);
}

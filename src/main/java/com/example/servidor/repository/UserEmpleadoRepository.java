package com.example.servidor.repository;

import com.example.servidor.model.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEmpleadoRepository extends UserRepository{
    @Query(value = "SELECT * FROM servicios_fav s WHERE s.empl_id = :email",
            nativeQuery = true)
    List<String[]> findServiciosFavByUserEmplId(
            @Param("email") String email);


}

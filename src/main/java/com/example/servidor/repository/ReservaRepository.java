package com.example.servidor.repository;

import com.example.servidor.model.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva,Long> {
}

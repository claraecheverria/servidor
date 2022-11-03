package com.example.servidor.service;

import com.example.servidor.model.Reserva;
import com.example.servidor.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    public void saveReserva(Reserva reserva){
        reservaRepository.save(reserva);
    }

    @Transactional
    public List<Reserva> obtenerReservasPorFechaYId(LocalDate fecha, String nombreCancha, String centroDepNombre){
        return reservaRepository.findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivo(fecha,nombreCancha,centroDepNombre);
    }

}

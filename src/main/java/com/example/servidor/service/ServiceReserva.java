package com.example.servidor.service;

import com.example.servidor.model.Cancha;
import com.example.servidor.model.Reserva;
import com.example.servidor.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public void saveReserva(Reserva reserva){
        reservaRepository.save(reserva);
    }

    public Optional<Reserva> obtenerReservaPorId(Long id){
        return reservaRepository.findById(id);
    }

    @Transactional
    public List<Reserva> obtenerReservasPorFechaYId(LocalDate fecha, String nombreCancha, String centroDepNombre){
        return reservaRepository.findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivo(fecha,nombreCancha,centroDepNombre);
    }

    @Transactional
    public List<Reserva> obtenerReservasPorFechaYCanchaYEmail (String emailUserEmpl, LocalDate fecha, String nombreCancha, String centroDepNombre){
        return reservaRepository.findByFechaAndCancha_Key_NombreAndCancha_Key_CentroDeportivoAndEmailUserEmpl(emailUserEmpl, fecha, nombreCancha, centroDepNombre);
    }


}

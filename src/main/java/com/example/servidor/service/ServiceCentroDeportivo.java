package com.example.servidor.service;

import com.example.servidor.model.CentroDeportivo;
import com.example.servidor.repository.CentroDeportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCentroDeportivo {
    @Autowired
    private CentroDeportivoRepository centroDeportivoRepository;

    public void guardarCentroDep (CentroDeportivo centroDeportivo){
        centroDeportivoRepository.save(centroDeportivo);
    }
    public List<CentroDeportivo> listarCentrosDep(){
        return (List<CentroDeportivo>) centroDeportivoRepository.findAll();
    }
    public Optional<CentroDeportivo> obtenerCentroDepPorId(String id){
        return centroDeportivoRepository.findById(id);
    }

}

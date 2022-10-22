package com.example.servidor.service;

import com.example.servidor.model.Servicio;
import com.example.servidor.model.ServicioIdNew;
import com.example.servidor.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServicio {
    @Autowired
    private ServicioRepository servicioRepository;

    public void saveServicioCentroDep (Servicio servicio){
        System.out.println("EStoy acaaaa");
        servicioRepository.save(servicio);
    }

    public Optional<Servicio> obtenerServicioById (ServicioIdNew id){
        return servicioRepository.findById(id);
    }

    public List<Servicio> listaServicios(String type){
        return servicioRepository.findAllServicios(type);
    }

    public List<Servicio> listaServiciosAll(){
        return (List<Servicio>) servicioRepository.findAll();
    }

}

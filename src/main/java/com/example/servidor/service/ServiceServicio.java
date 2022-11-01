package com.example.servidor.service;

import com.example.servidor.model.Servicio;
import com.example.servidor.model.ServicioIdNew;
import com.example.servidor.repository.CanchaRepository;
import com.example.servidor.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServicio {
    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private CanchaRepository canchaRepository;

    public void saveServicioCentroDep (Servicio servicio){
        servicioRepository.save(servicio);
    }

    public Optional<Servicio> obtenerServicioById (ServicioIdNew id){
        return servicioRepository.findById(id);
    }

    public Optional<Servicio> obtenerServicioPorNombreYCentroDep (String nombre, String centroDepNombre){
        return servicioRepository.findByKey_NombreAndKey_CentroDeportivo(nombre,centroDepNombre);
    }

    @Transactional
    public List<Servicio> listaServicios(String type){
        return servicioRepository.findAllServicios(type);
    }

    public List<Servicio> listaServiciosAll(){
        return (List<Servicio>) servicioRepository.findAll();
    }
   public List<Servicio> listaServiciosByCentroDep(String centroDep){
        return servicioRepository.findByKey_CentroDeportivo(centroDep);
    }

}

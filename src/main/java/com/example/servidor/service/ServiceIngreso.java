package com.example.servidor.service;

import com.example.servidor.model.Ingreso;
import com.example.servidor.repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceIngreso {
    @Autowired
    private IngresoRepository ingresoRepository;

    public  void saveIngreso(Ingreso ingreso){
        ingresoRepository.save(ingreso);
    }
}

package com.example.servidor.service;

import com.example.servidor.model.Empresa;
import com.example.servidor.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmpresa {

    @Autowired
    private EmpresaRepository empresaRepository;

    public void guardarEmpresa (Empresa empresa){
        empresaRepository.save(empresa);
    }
    public List<Empresa> listarEmpresas (){
        return (List<Empresa>) empresaRepository.findAll();
    }

    public Optional<Empresa> obtenerEmpresaPorId (String nombreEmpresa){
        return empresaRepository.findById(nombreEmpresa);
    }
}

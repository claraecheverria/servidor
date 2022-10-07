package com.example.servidor.service;

import com.example.servidor.model.*;
import com.example.servidor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAll {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private UserEmpresaRepository userEmpresaRepository;

    @Autowired
    private CentroDeportivoRepository centroDeportivoRepository;

    @Autowired
    private UserCentroDeportivoRepository userCentroDeportivoRepository;

    public void guardarEmpresa (Empresa empresa){
        empresaRepository.save(empresa);
    }
    public List<Empresa> listarEmpresas (){
        return (List<Empresa>) empresaRepository.findAll();
    }
    public void saveUserEmpresa ( UserEmpresa user){
        userEmpresaRepository.save(user);
    }

    public void guardarCentroDep (CentroDeportivo centroDeportivo){
        centroDeportivoRepository.save(centroDeportivo);
    }
    public List<CentroDeportivo> listarCentrosDep(){
        return (List<CentroDeportivo>) centroDeportivoRepository.findAll();
    }

    public void saveUserCentroDep (UserCentroDeportivo userCentroDeportivo){
        userCentroDeportivoRepository.save(userCentroDeportivo);
    }
    public List<User> listaUsuarios (){
        return (List<User>) userEmpresaRepository.findAll();
    }

}

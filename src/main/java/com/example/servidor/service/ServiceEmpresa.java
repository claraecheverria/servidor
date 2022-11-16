package com.example.servidor.service;

import com.example.servidor.model.Empresa;
import com.example.servidor.model.UserEmpleado;
import com.example.servidor.model.UserEmpresa;
import com.example.servidor.repository.EmpresaRepository;
import com.example.servidor.repository.UserEmpresaRepository;
import com.example.servidor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmpresa {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private UserEmpresaRepository userEmpresaRepository;

    @Autowired
    private UserRepository userRepository;



    public void guardarEmpresa (Empresa empresa){
        empresaRepository.save(empresa);
    }
    public List<Empresa> listarEmpresas(){
        return (List<Empresa>) empresaRepository.findAll();
    }

    public List<UserEmpleado> listaEmpleadosDeEmpresa(String empresaNombre){
        return userRepository.findUserEmpleadosByEmpresa(empresaNombre);
    }


    public Optional<Empresa> obtenerEmpresaPorId (String nombreEmpresa){
        return empresaRepository.findById(nombreEmpresa);
    }
    public void saveUserEmpresa ( UserEmpresa user){
        userEmpresaRepository.save(user);
    }

}

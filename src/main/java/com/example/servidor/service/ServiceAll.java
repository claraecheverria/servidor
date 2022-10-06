package com.example.servidor.service;

import com.example.servidor.model.Empresa;
import com.example.servidor.model.User;
import com.example.servidor.model.UserEmpresa;
import com.example.servidor.repository.EmpresaRepository;
import com.example.servidor.repository.UserEmpresaRepository;
import com.example.servidor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAll {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private UserEmpresaRepository userEmpresaRepository;

    public void guardarEmpresa (Empresa empresa){
        empresaRepository.save(empresa);
    }
    public List<Empresa> listarEmpresas (){
        return (List<Empresa>) empresaRepository.findAll();
    }
    public void saveUserEmpresa ( UserEmpresa user){
        userEmpresaRepository.save(user);
    }
    public List<User> listaUsuarios (){
        return (List<User>) userEmpresaRepository.findAll();
    }

}

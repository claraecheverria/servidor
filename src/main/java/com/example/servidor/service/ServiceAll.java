package com.example.servidor.service;

import com.example.servidor.model.*;
import com.example.servidor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAll {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEmpresaRepository userEmpresaRepository;
    @Autowired
    private CentroDeportivoRepository centroDeportivoRepository;
    @Autowired
    private UserCentroDeportivoRepository userCentroDeportivoRepository;
    @Autowired
    private UserEmpleadoRepository userEmpleadoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

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

    public Optional<CentroDeportivo> obtenerCentroDepPorId(String id){
        return centroDeportivoRepository.findById(id);
    }
    public List<String[]> obtenerUsuarioPorIdyPassword (User user){
        return userRepository.findUserByEmailAndPasswordNamedParamsNative(user.getEmail(), user.getPassword());
    }

    public UserEmpleado obtenerUserEmplPorId (String email){
        return  userRepository.findUserEmpleadoByEmail(email);
    }
    public void saveUserEmpleado (UserEmpleado userEmpleado){
        userEmpleadoRepository.save(userEmpleado);
    }

    public boolean userExiste(User user){
        return userRepository.existsById(user.getEmail());
    }

    public void saveUserCentroDep (UserCentroDeportivo userCentroDeportivo){
        userCentroDeportivoRepository.save(userCentroDeportivo);
    }
    public void saveServicioCentroDep (Servicio servicio){
        System.out.println("EStoy acaaaa");
        servicioRepository.save(servicio);
    }

    public Optional<Servicio> obtenerServicioById (ServicioIdNew id){
        return servicioRepository.findById(id);
    }
    public List<User> listaUsuarios (){
        return (List<User>) userEmpresaRepository.findAll();
    }
    public List<Servicio> listaServicios(String type){
        return servicioRepository.findAllServicios(type);
    }

}

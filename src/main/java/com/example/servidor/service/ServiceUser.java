package com.example.servidor.service;

import com.example.servidor.model.*;
import com.example.servidor.repository.UserCentroDeportivoRepository;
import com.example.servidor.repository.UserEmpleadoRepository;
import com.example.servidor.repository.UserEmpresaRepository;
import com.example.servidor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEmpresaRepository userEmpresaRepository;
    @Autowired
    private UserCentroDeportivoRepository userCentroDeportivoRepository;
    @Autowired
    private UserEmpleadoRepository userEmpleadoRepository;


    public void saveUserEmpresa ( UserEmpresa user){
        userEmpresaRepository.save(user);
    }

    public List<String[]> obtenerUsuarioPorIdyPassword (User user){
        return userRepository.findUserByEmailAndPasswordNamedParamsNative(user.getEmail(), user.getPassword());
    }

    public List<String[]> obtenerServiciosFav(String email){
        return userEmpleadoRepository.findServiciosFavByUserEmplId(email);
    }
//    public UserEmpleado obtenerUserEmplPorId (String email){
//        return  userRepository.findUserEmpleadoByEmail(email);
//    }
    public Optional<User> obtenerUserPorId (String email){
        return  userRepository.findById(email);
    }
    @Transactional
    public void saveUserEmpleado (UserEmpleado userEmpleado){
        userEmpleadoRepository.save(userEmpleado);
        System.out.println("user guradado!!");
    }

    public boolean userExiste(String emailUser){
        return userRepository.existsById(emailUser);
    }

    public void saveUserCentroDep (UserCentroDeportivo userCentroDeportivo){
        userCentroDeportivoRepository.save(userCentroDeportivo);
    }

    public List<User> listaUsuarios (){
        return (List<User>) userEmpresaRepository.findAll();
    }

}

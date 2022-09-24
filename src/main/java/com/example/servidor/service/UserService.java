package com.example.servidor.service;

import com.example.servidor.model.User;
import com.example.servidor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

//    @Transactional
    public void saveUser ( User user){
        boolean existe = userRepository.existsById(user.getEmail());
//        if (!existe){
//            //comunicar login incorrecto
//        }else {
            System.out.println("usuario guardado!!!!!!!");
            userRepository.save(user);
//        }

    }

    public void probando (){
        System.out.println("estoy aca!!!!!!!");
    }

    public ArrayList<User> obtenerUsuarios(){
        return (ArrayList<User>) userRepository.findAll();
    }
}

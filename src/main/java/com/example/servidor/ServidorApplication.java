package com.example.servidor;

import com.example.servidor.model.Empresa;
import com.example.servidor.model.UserEmpresa;
import com.example.servidor.repository.UserEmpresaRepository;
import com.example.servidor.repository.UserRepository;
import com.example.servidor.service.ServiceAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServidorApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ServidorApplication.class, args);
//        Empresa nuevaEmpresa = new Empresa("Empresa1");
//        UserEmpresa nuevo = new UserEmpresa("clara@gmail.com", 52324016L,"Clara", "12345", 2222L, nuevaEmpresa);
//        UserRepository userEmpresaRepo = configurableApplicationContext.getBean(UserEmpresaRepository.class);
//        userEmpresaRepo.save(nuevo);
        SpringApplication.run(ServidorApplication.class, args);
    }

}

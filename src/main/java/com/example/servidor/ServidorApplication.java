package com.example.servidor;

import com.example.servidor.model.*;
import com.example.servidor.repository.*;
import com.example.servidor.service.ServiceAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ServidorApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ServidorApplication.class, args);
//        Empresa nuevaEmpresa = new Empresa("Empresa1");
//        UserEmpresa nuevo = new UserEmpresa("clara@gmail.com", 52324016L,"Clara", "12345", 2222L, nuevaEmpresa);
//        UserRepository userEmpresaRepo = configurableApplicationContext.getBean(UserEmpresaRepository.class);
//        userEmpresaRepo.save(nuevo);

//        SpringApplication.run(ServidorApplication.class, args);

        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ServidorApplication.class, args);
        Empresa emp1 = new Empresa("Empresa1", "111", "151","WTC Montevideo");
        Empresa emp2 = new Empresa("Empresa2", "222", "252","WTC Montevideo");
        Empresa emp3 = new Empresa("Empresa3", "333", "353","WTC Montevideo");
        Empresa emp4 = new Empresa("Empresa4", "444", "454","WTC Montevideo");
        UserEmpresa usr1 = new UserEmpresa("empr1@gmail.com", 11111116L,"Pedro", "12345", 1211L, emp1);
        UserEmpresa usr2 = new UserEmpresa("empr2@gmail.com", 22222226L,"Pepe", "12345", 1211L, emp2);
        UserEmpresa usr3 = new UserEmpresa("empr3@gmail.com", 33333336L,"Pablo", "12345", 1211L, emp3);
        UserEmpresa usr4 = new UserEmpresa("empr4@gmail.com", 44444446L,"Pia", "12345", 1211L, emp4);
        UserEmpleado userrr = new UserEmpleado("empl1@gmail.com", 55555556L, "Clara", "12345", 1211L, LocalDate.of(2022,11,22), 5000L, emp1);
        UserRepository userRepository = configurableApplicationContext.getBean(UserEmpresaRepository.class);
        EmpresaRepository empresaRepository = configurableApplicationContext.getBean(EmpresaRepository.class);
        empresaRepository.save(emp1);
        empresaRepository.save(emp2);
        empresaRepository.save(emp3);
        empresaRepository.save(emp4);
        userRepository.save(usr1);
        userRepository.save(usr2);
        userRepository.save(usr3);
        userRepository.save(usr4);
        userRepository.save(userrr);

        CentroDeportivo centro1 = new CentroDeportivo("Centro1", "111", "191", "Rivera 534");
        CentroDeportivo centro2 = new CentroDeportivo("Centro2", "222", "292", "Cavia 2866");
        CentroDeportivo centro3 = new CentroDeportivo("Centro3", "333", "393", "Av. Brasil 2243");
        CentroDeportivo centro4 = new CentroDeportivo("Centro4", "444", "494", "Cno. de los horneros");
        UserCentroDeportivo user1 = new UserCentroDeportivo("centro1@gmail.com", 11111116L,"Ana", "12345", 1211L, centro1);
        UserCentroDeportivo user2 = new UserCentroDeportivo("centro2@gmail.com", 22222226L,"Amelia", "12345", 1211L, centro2);
        UserCentroDeportivo user3 = new UserCentroDeportivo("centro3@gmail.com", 33333336L,"Alicia", "12345", 1211L, centro3);
        UserCentroDeportivo user4 = new UserCentroDeportivo("centro4@gmail.com", 44444446L,"Anuel", "12345", 1211L, centro4);
        CentroDeportivoRepository centroDeportivoRepository = configurableApplicationContext.getBean(CentroDeportivoRepository.class);
        centroDeportivoRepository.save(centro1);
        centroDeportivoRepository.save(centro2);
        centroDeportivoRepository.save(centro3);
        centroDeportivoRepository.save(centro4);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        Set<DiasDeLaSemana> dias1 = new HashSet<>();
        Set<DiasDeLaSemana> dias2 = new HashSet<>();
        Set<DiasDeLaSemana> dias3 = new HashSet<>();
        dias2.add(DiasDeLaSemana.Lunes);
        dias2.add(DiasDeLaSemana.Miercoles);
        dias2.add(DiasDeLaSemana.Viernes);
        dias1.add(DiasDeLaSemana.Martes);
        dias1.add(DiasDeLaSemana.Jueves);
        dias3.add(DiasDeLaSemana.Lunes);
        dias3.add(DiasDeLaSemana.Martes);
        dias3.add(DiasDeLaSemana.Miercoles);
        dias3.add(DiasDeLaSemana.Jueves);
        dias3.add(DiasDeLaSemana.Viernes);


        Servicio serv11 = new Servicio("Pilates", centro1,250L,dias1, LocalTime.of(10,0), LocalTime.of(11,0),"clase de pilates para principiantes", "tipo", null);
        Servicio serv21 = new Servicio("Pilates", centro2,300L,dias1, LocalTime.of(10,0), LocalTime.of(11,0),"clase de pilates para principiantes", "tipo", null);
        Servicio serv31 = new Servicio("Pilates", centro3,350L,dias1, LocalTime.of(10,0), LocalTime.of(11,0),"clase de pilates para principiantes", "tipo", null);

        Servicio serv12 = new Servicio("Yoga", centro1,250L,dias2, LocalTime.of(10,0), LocalTime.of(11,0),"clase de yoga para principiantes", "tipo", null);
        Servicio serv22 = new Servicio("Yoga", centro2,300L,dias2, LocalTime.of(10,0), LocalTime.of(11,0),"clase de yoga para principiantes", "tipo", null);
        Servicio serv32 = new Servicio("Yoga", centro3,350L,dias2, LocalTime.of(10,0), LocalTime.of(11,0),"clase de yoga para principiantes", "tipo", null);

        Cancha cancha41 = new Cancha("Cancha 1",centro4, 500L,dias3,LocalTime.of(9,0), LocalTime.of(22,0), "cancha de fútbol", "cancha", null, 22);
        Cancha cancha42 = new Cancha("Cancha 2",centro4, 500L,dias3,LocalTime.of(9,0), LocalTime.of(20,0), "cancha de basket cerrada", "cancha",null, 12);

        ServicioRepository servicioRepository = configurableApplicationContext.getBean(ServicioRepository.class);
        servicioRepository.save(serv11);
        servicioRepository.save(serv21);
        servicioRepository.save(serv31);
        servicioRepository.save(serv12);
        servicioRepository.save(serv22);
        servicioRepository.save(serv32);
        servicioRepository.save(cancha41);
        servicioRepository.save(cancha42);
    }



    public void crearEmpresas(ConfigurableApplicationContext configurableApplicationContext){
        Empresa emp1 = new Empresa("Empresa1", "111", "151","WTC Montevideo");
        Empresa emp2 = new Empresa("Empresa2", "222", "252","WTC Montevideo");
        Empresa emp3 = new Empresa("Empresa3", "333", "353","WTC Montevideo");
        Empresa emp4 = new Empresa("Empresa4", "444", "454","WTC Montevideo");
        UserEmpresa usr1 = new UserEmpresa("empr1@gmail.com", 11111116L,"Pedro", "12345", 1211L, emp1);
        UserEmpresa usr2 = new UserEmpresa("empr2@gmail.com", 22222226L,"Pepe", "12345", 1211L, emp2);
        UserEmpresa usr3 = new UserEmpresa("empr3@gmail.com", 33333336L,"Pablo", "12345", 1211L, emp3);
        UserEmpresa usr4 = new UserEmpresa("empr4@gmail.com", 44444446L,"Pia", "12345", 1211L, emp4);
        UserRepository userRepository = configurableApplicationContext.getBean(UserEmpresaRepository.class);
        EmpresaRepository empresaRepository = configurableApplicationContext.getBean(EmpresaRepository.class);
        empresaRepository.save(emp1);
        empresaRepository.save(emp2);
        empresaRepository.save(emp3);
        empresaRepository.save(emp4);
        userRepository.save(usr1);
        userRepository.save(usr2);
        userRepository.save(usr3);
        userRepository.save(usr4);
    }

    public void crearCentrosDep(ConfigurableApplicationContext configurableApplicationContext){
        CentroDeportivo centro1 = new CentroDeportivo("Centro1", "111", "191", "Rivera 534");
        CentroDeportivo centro2 = new CentroDeportivo("Centro2", "222", "292", "Cavia 2866");
        CentroDeportivo centro3 = new CentroDeportivo("Centro3", "333", "393", "Av. Brasil 2243");
        CentroDeportivo centro4 = new CentroDeportivo("Centro4", "444", "494", "Cno. de los horneros");
        UserCentroDeportivo usr1 = new UserCentroDeportivo("centro1@gmail.com", 11111116L,"Ana", "12345", 1211L, centro1);
        UserCentroDeportivo usr2 = new UserCentroDeportivo("centro2@gmail.com", 22222226L,"Amelia", "12345", 1211L, centro2);
        UserCentroDeportivo usr3 = new UserCentroDeportivo("centro3@gmail.com", 33333336L,"Alicia", "12345", 1211L, centro3);
        UserCentroDeportivo usr4 = new UserCentroDeportivo("centro4@gmail.com", 44444446L,"Anuel", "12345", 1211L, centro4);
        UserRepository userRepository = configurableApplicationContext.getBean(UserCentroDeportivoRepository.class);
        CentroDeportivoRepository centroDeportivoRepository = configurableApplicationContext.getBean(CentroDeportivoRepository.class);
        centroDeportivoRepository.save(centro1);
        centroDeportivoRepository.save(centro2);
        centroDeportivoRepository.save(centro3);
        centroDeportivoRepository.save(centro4);
        userRepository.save(usr1);
        userRepository.save(usr2);
        userRepository.save(usr3);
        userRepository.save(usr4);

        Set<DiasDeLaSemana> dias1 = new HashSet<>();
        Set<DiasDeLaSemana> dias2 = new HashSet<>();
        Set<DiasDeLaSemana> dias3 = new HashSet<>();
        dias2.add(DiasDeLaSemana.Lunes);
        dias2.add(DiasDeLaSemana.Miercoles);
        dias2.add(DiasDeLaSemana.Viernes);
        dias1.add(DiasDeLaSemana.Martes);
        dias1.add(DiasDeLaSemana.Jueves);
        dias3.add(DiasDeLaSemana.Lunes);
        dias3.add(DiasDeLaSemana.Martes);
        dias3.add(DiasDeLaSemana.Miercoles);
        dias3.add(DiasDeLaSemana.Jueves);
        dias3.add(DiasDeLaSemana.Viernes);


        Servicio serv11 = new Servicio("Pilates", centro1,250L,dias1, LocalTime.of(10,0), LocalTime.of(11,0),"clase de pilates para principiantes", "tipo", null);
        Servicio serv21 = new Servicio("Pilates", centro2,300L,dias1, LocalTime.of(10,0), LocalTime.of(11,0),"clase de pilates para principiantes", "tipo", null);
        Servicio serv31 = new Servicio("Pilates", centro3,350L,dias1, LocalTime.of(10,0), LocalTime.of(11,0),"clase de pilates para principiantes", "tipo", null);

        Servicio serv12 = new Servicio("Yoga", centro1,250L,dias2, LocalTime.of(10,0), LocalTime.of(11,0),"clase de yoga para principiantes", "tipo", null);
        Servicio serv22 = new Servicio("Yoga", centro2,300L,dias2, LocalTime.of(10,0), LocalTime.of(11,0),"clase de yoga para principiantes", "tipo", null);
        Servicio serv32 = new Servicio("Yoga", centro3,350L,dias2, LocalTime.of(10,0), LocalTime.of(11,0),"clase de yoga para principiantes", "tipo", null);

        Cancha cancha41 = new Cancha("Cancha 1",centro4, 500L,dias3,LocalTime.of(9,0), LocalTime.of(22,0), "cancha de fútbol", "cancha", null, 22);
        Cancha cancha42 = new Cancha("Cancha 2",centro4, 500L,dias3,LocalTime.of(9,0), LocalTime.of(20,0), "cancha de basket cerrada", "cancha",null, 12);

        ServicioRepository servicioRepository = configurableApplicationContext.getBean(ServicioRepository.class);
        servicioRepository.save(serv11);
        servicioRepository.save(serv21);
        servicioRepository.save(serv31);
        servicioRepository.save(serv12);
        servicioRepository.save(serv22);
        servicioRepository.save(serv32);
        servicioRepository.save(cancha41);
        servicioRepository.save(cancha42);

    }



}

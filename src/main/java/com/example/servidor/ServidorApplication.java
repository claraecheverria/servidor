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
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ServidorApplication.class, args);
        Empresa emp1 = new Empresa("ComLabs", "111", "Communication Labs Services", "WTC Montevideo");
        Empresa emp2 = new Empresa("TecnoUY", "222", "Technologies Uruguay SA", "WTC Montevideo");
//        Empresa emp3 = new Empresa("Empresa3", "333", "353", "WTC Montevideo");
//        Empresa emp4 = new Empresa("Empresa4", "444", "454", "WTC Montevideo");
        UserEmpresa usr1 = new UserEmpresa("comlabs@gmail.com", 11111116L, "Pedro Rodriguez", "12345", 01065065L, emp1);
        UserEmpresa usr2 = new UserEmpresa("tecnouy@gmail.com", 22222226L, "Francisco Perez", "12345", 01274123L, emp2);
//        UserEmpresa usr3 = new UserEmpresa("empr3@gmail.com", 33333336L, "Pablo", "12345", 1211L, emp3);
//        UserEmpresa usr4 = new UserEmpresa("empr4@gmail.com", 44444446L, "Pia", "12345", 1211L, emp4);
        UserEmpleado userrr = new UserEmpleado("comlabs1@gmail.com", 55555556L, "Camilo Lopez", "12345", 1252711L, LocalDate.of(2022, 12, 25), 5000L, emp1);
        UserEmpleado userrr2 = new UserEmpleado("comlabs2@gmail.com", 66666666L, "Cecilia Fiorino", "12345", 12184101L, LocalDate.of(2022, 12, 22), 5000L, emp1);
        UserEmpleado userrr3 = new UserEmpleado("tecnouy1@gmail.com", 12121212L, "Claudia Campos", "12345", 12198641L, LocalDate.of(2022, 11, 19), 5500L, emp2);
        UserRepository userRepository = configurableApplicationContext.getBean(UserEmpresaRepository.class);
        EmpresaRepository empresaRepository = configurableApplicationContext.getBean(EmpresaRepository.class);
        empresaRepository.save(emp1);
        empresaRepository.save(emp2);
//        empresaRepository.save(emp3);
//        empresaRepository.save(emp4);
        userRepository.save(usr1);
        userRepository.save(usr2);
//        userRepository.save(usr3);
//        userRepository.save(usr4);
        userRepository.save(userrr);
        userRepository.save(userrr2);
        userRepository.save(userrr3);
        UserAdmin admin = new UserAdmin("admin@gmail.com", 4512311L, "SUper Administrador", "12345", 1211L);
        userRepository.save(admin);

        CentroDeportivo centro1 = new CentroDeportivo("SuperGYM", "111", "Super gimnasio SA", "Rivera 534");
        CentroDeportivo centro2 = new CentroDeportivo("Strech Studio", "222", "Strech Studio SAS", "Cavia 2866");
//        CentroDeportivo centro3 = new CentroDeportivo("Centro3", "333", "393", "Av. Brasil 2243");
//        CentroDeportivo centro4 = new CentroDeportivo("Centro4", "444", "494", "Cno. de los horneros");
        UserCentroDeportivo user1 = new UserCentroDeportivo("supergym@gmail.com", 77777776L, "Ana Doldo", "12345", 1211L, centro1);
        UserCentroDeportivo user2 = new UserCentroDeportivo("strech@gmail.com", 88888886L, "Amelia Santos", "12345", 1211L, centro2);
//        UserCentroDeportivo user3 = new UserCentroDeportivo("centro3@gmail.com", 99999996L, "Alicia", "12345", 1211L, centro3);
//        UserCentroDeportivo user4 = new UserCentroDeportivo("centro4@gmail.com", 21111116L, "Anuel", "12345", 1211L, centro4);
        CentroDeportivoRepository centroDeportivoRepository = configurableApplicationContext.getBean(CentroDeportivoRepository.class);
        centroDeportivoRepository.save(centro1);
        centroDeportivoRepository.save(centro2);
//        centroDeportivoRepository.save(centro3);
//        centroDeportivoRepository.save(centro4);
        userRepository.save(user1);
        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
//
//        Set<DiasDeLaSemana> dias1 = new HashSet<>();
//        Set<DiasDeLaSemana> dias2 = new HashSet<>();
//        Set<DiasDeLaSemana> dias3 = new HashSet<>();
//        dias2.add(DiasDeLaSemana.Lunes);
//        dias2.add(DiasDeLaSemana.Miercoles);
//        dias2.add(DiasDeLaSemana.Viernes);
//        dias1.add(DiasDeLaSemana.Martes);
//        dias1.add(DiasDeLaSemana.Jueves);
//        dias3.add(DiasDeLaSemana.Lunes);
//        dias3.add(DiasDeLaSemana.Martes);
//        dias3.add(DiasDeLaSemana.Miercoles);
//        dias3.add(DiasDeLaSemana.Jueves);
//        dias3.add(DiasDeLaSemana.Viernes);
//
//
//        Servicio serv11 = new Servicio("Pilates", centro1, 250L, dias1, LocalTime.of(10, 0), LocalTime.of(11, 0), "clase de pilates para principiantes", "tipo", null);
//        Servicio serv21 = new Servicio("Pilates", centro2, 300L, dias1, LocalTime.of(10, 0), LocalTime.of(11, 0), "clase de pilates para principiantes", "tipo", null);
//        Servicio serv31 = new Servicio("Pilates", centro3, 350L, dias1, LocalTime.of(10, 0), LocalTime.of(11, 0), "clase de pilates para principiantes", "tipo", null);
//
//        Servicio serv12 = new Servicio("Yoga", centro1, 250L, dias2, LocalTime.of(10, 0), LocalTime.of(11, 0), "clase de yoga para principiantes", "tipo", null);
//        Servicio serv22 = new Servicio("Yoga", centro2, 300L, dias2, LocalTime.of(10, 0), LocalTime.of(11, 0), "clase de yoga para principiantes", "tipo", null);
//        Servicio serv32 = new Servicio("Yoga", centro3, 350L, dias2, LocalTime.of(10, 0), LocalTime.of(11, 0), "clase de yoga para principiantes", "tipo", null);
//
//        Cancha cancha41 = new Cancha("Cancha 1", centro4, 500L, dias3, LocalTime.of(9, 0), LocalTime.of(22, 0), "cancha de fútbol", "cancha", null, 22);
//        Cancha cancha42 = new Cancha("Cancha 2", centro4, 500L, dias3, LocalTime.of(9, 0), LocalTime.of(20, 0), "cancha de basket cerrada", "cancha", null, 12);
//
//        ServicioRepository servicioRepository = configurableApplicationContext.getBean(CanchaRepository.class);
//        servicioRepository.save(serv11);
//        servicioRepository.save(serv21);
//        servicioRepository.save(serv31);
//        servicioRepository.save(serv12);
//        servicioRepository.save(serv22);
//        servicioRepository.save(serv32);
//        servicioRepository.save(cancha41);
//        servicioRepository.save(cancha42);
    }



}
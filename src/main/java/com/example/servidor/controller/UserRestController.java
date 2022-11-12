package com.example.servidor.controller;

import com.example.servidor.DTOs.ServicioDTO;
import com.example.servidor.model.*;
import com.example.servidor.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private List<String[]> userADevolver;

    public List<String[]> getUserADevolver() {
        return userADevolver;
    }

    @Autowired
    private ServiceUser serviceUser;
    @Autowired
    private ServiceReserva serviceReserva;
    @Autowired
    private ServiceServicio serviceServicio;
    @Autowired
    private ServiceCentroDeportivo serviceCentroDeportivo;
    @Autowired
    private ServiceEmpresa serviceEmpresa;
    @GetMapping("/listausers")
    List<User> all() {
        return serviceUser.listaUsuarios();
    }
    @GetMapping("/devuelveUser")//para login, queda guardado toda la info en userADevolver
    List<String[]> devuelveUser(){
        return userADevolver;
    }
    @PostMapping("/userParaCheck")//para login
    public void userParaCheck(@RequestBody User user){
        userADevolver = serviceUser.obtenerUsuarioPorIdyPassword(user);
    }

    @GetMapping("/checkExisteUser")//para hacer reserva ver si invitados existen
    public boolean checkExisteUser(@RequestParam("email") String emailUser){
        return serviceUser.userExiste(emailUser);
    }

    @GetMapping("/reservasEnFecha")
    public List<Reserva> reservasEnFechaYServicio (@RequestParam("fecha")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
                                                   @RequestParam("servicio") String servicioNombre,
                                                   @RequestParam("centroDep") String centroDepNombre){
        List<Reserva> reservas = serviceReserva.obtenerReservasPorFechaYId(fecha,servicioNombre,centroDepNombre);

//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        objectMapper.findAndRegisterModules();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        objectMapper.setDateFormat(df);
//        FilterProvider filters = new SimpleFilterProvider().addFilter("filter sin imagenes", SimpleBeanPropertyFilter.serializeAllExcept("imagenes"));
//        ObjectWriter writer = objectMapper.writer(filters);
//        try {
//            System.out.println(writer.writeValueAsString(reservas));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        List<Reserva> reservasConMenosDatos = new ArrayList<>();
//        for(int i=0; i<reservas.size(); i++){
//            Reserva currentReserva = reservas.get(i);
//            Cancha currentCancha = currentReserva.getCancha();
//            Cancha nuevaCanchaMenosInfo = new Cancha(currentCancha.getKey().getNombre(),currentCancha.getCentroDeportivoServicio().getNombre());
//            currentReserva.setCancha(nuevaCanchaMenosInfo);
//            reservasConMenosDatos.add(currentReserva);
//        }
//        System.out.println(reservas.size());
//        System.out.println(reservasConMenosDatos.size());
        return reservas;
    }

    @Transactional
    @PostMapping("/hacerReserva")//falta probar si funciona
    public void hacerReserva(@RequestBody Reserva reserva) {
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Reserva> listReservasUser = currentUser.getReservasHechas();
        List<UserEmpleado> listaEmplQueLlega = reserva.getUsuariosInvitados();
        List<UserEmpleado> listaEmplAGuardar = new ArrayList<>();
        for(int i = 0; i<listaEmplQueLlega.size(); i++){
            UserEmpleado curreeeentUser = listaEmplQueLlega.get(i);
            UserEmpleado userConTodosLosDatos = (UserEmpleado) serviceUser.obtenerUserPorId(curreeeentUser.getEmail()).get();
            listaEmplAGuardar.add(userConTodosLosDatos);
        }
        reserva.setUsuariosInvitados(listaEmplAGuardar);
        Set<Imagen> imagenesVacias = new HashSet<>();
//        reserva.getCancha().setImagenes(imagenesVacias);
        listReservasUser.add(reserva);
        currentUser.setReservasHechas(listReservasUser);
        serviceReserva.saveReserva(reserva);
        serviceUser.saveUserEmpleado(currentUser);
    }

    @GetMapping("/listaServicios")//valido para todos los empleados
    List<Servicio> listServicios (){

        List<Servicio> listaQuery = serviceServicio.listaServicios("SERVICIO");
        List<Servicio> listaSinFav = new ArrayList<>();
        for (int i = 0; i< listaQuery.size(); i++){
            Servicio currentServ = listaQuery.get(i);
            Servicio nuevoServ = new Servicio(currentServ.getKey().getNombre(), currentServ.getCentroDeportivoServicio(), currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo(), currentServ.getImagenes());
//            for (Imagen imagen : nuevoServ.getImagenes()){
//                System.out.println(imagen);
//            }
            listaSinFav.add(nuevoServ);
        }
        return listaSinFav;
    }

    @GetMapping("/listaServiciosCancha")//valido para todos los empleados
    List<Cancha> listServiciosCancha (){
//        List<Servicio> listaQuery = serviceServicio.listaServicios("CANCHA");
        List<Cancha> listaQuery = serviceServicio.listaServiciosCancha("CANCHA");

        List<Cancha> listaSinFav = new ArrayList<>();
        for (int i = 0; i< listaQuery.size(); i++){
            Cancha currentServ = listaQuery.get(i);
            Cancha nuevaCancha = new Cancha(currentServ.getKey().getNombre(), currentServ.getCentroDeportivoServicio(), currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo(), currentServ.getImagenes(),currentServ.getCupos());
//            Servicio nuevoServ = new Servicio(currentServ.getKey().getNombre(), currentServ.getCentroDeportivoServicio(), currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo(), currentServ.getImagenes());
            listaSinFav.add(nuevaCancha);
        }
        return listaSinFav;
    }

    @PostMapping("/agregarServicioFav")//falta probar si funciona
    public void agregarServicioFav(@RequestBody Servicio servicio){
        String emailUser = userADevolver.get(0)[1];
//        UserEmpleado currentUser = serviceAll.obtenerUserEmplPorId(emailUser);
        UserEmpleado currentUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Servicio> listaFavs = currentUser.getServiciosFavoritos();
        listaFavs.add(servicio);
        currentUser.setServiciosFavoritos(listaFavs);
        serviceUser.saveUserEmpleado(currentUser);
    }

    @PostMapping("/eliminarServicioFav")//falta probar si funciona
    public void eliminarServicioFav(@RequestBody Servicio servicio){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
//        UserEmpleado currentUser = serviceAll.obtenerUserEmplPorId(emailUser);
        List<Servicio> listaFavs = currentUser.getServiciosFavoritos();
        if (listaFavs.contains(servicio)){
            listaFavs.remove(servicio);
        }
        currentUser.setServiciosFavoritos(listaFavs);
        serviceUser.saveUserEmpleado(currentUser);
    }

    @GetMapping("/serviciosFavDeUnUser")
    public List<ServicioDTO> serviciosFavDeUnUser(){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado unUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Servicio> listaFavs = unUser.getServiciosFavoritos();
        List<ServicioDTO> listaFavs2 = new ArrayList<>();
        for (int i=0; i<listaFavs.size(); i++){
            Servicio currentServ = listaFavs.get(i);
            ServicioDTO nuevoServDTO = new ServicioDTO(currentServ.getKey().getNombre(), currentServ.getKey().getCentroDeportivo(),currentServ.getCentroDeportivoServicio().getDireccion(),currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo());
            listaFavs2.add(nuevoServDTO);
        }
        return  listaFavs2;
    }

    @GetMapping("/serviciosFavPrueba")
    public List<ServicioDTO> serviciosFavPrueba(){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado unUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Servicio> listaFavs = serviceServicio.listaFavssByEmailUser(emailUser);
        List<ServicioDTO> listaFavs2 = new ArrayList<>();
        for (int i=0; i<listaFavs.size(); i++){
            Servicio currentServ = listaFavs.get(i);
            ServicioDTO nuevoServDTO = new ServicioDTO(currentServ.getKey().getNombre(), currentServ.getKey().getCentroDeportivo(),currentServ.getCentroDeportivoServicio().getDireccion(),currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo());
            listaFavs2.add(nuevoServDTO);
        }
        return  listaFavs2;
    }



}

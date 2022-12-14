package com.example.servidor.controller;

import com.example.servidor.DTOs.CanchaDTO;
import com.example.servidor.DTOs.ReservaDTO;
import com.example.servidor.DTOs.ServicioDTO;
import com.example.servidor.model.*;
import com.example.servidor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/saldo")
    public String devuelveSaldoUser(){
        return userADevolver.get(0)[6];
    }
    @PostMapping("/userParaCheck")//para login
    public void userParaCheck(@RequestBody User user){
        userADevolver = serviceUser.obtenerUsuarioPorIdyPassword(user);
    }

    @GetMapping("/checkExisteUser")//para hacer reserva ver si invitados existen
    public boolean checkExisteUser(@RequestParam("email") String emailUser){
        return serviceUser.userExiste(emailUser);
    }

    @GetMapping("/reservasEnFechaDTO")
    public List<ReservaDTO> reservasEnFechaYServicioDTO (@RequestParam("fecha")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
                                                   @RequestParam("servicio") String servicioNombre,
                                                   @RequestParam("centroDep") String centroDepNombre){
        List<Reserva> reservas = serviceReserva.obtenerReservasPorFechaYId(fecha,servicioNombre,centroDepNombre);
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (int i= 0; i< reservas.size(); i++){
            Reserva currentReserva = reservas.get(i);
            ReservaDTO nuevaReservaDTO = new ReservaDTO(currentReserva.getFecha(), currentReserva.getHoraInicio(), currentReserva.getHoraFin(),currentReserva.getCancha().getKey().getNombre(), currentReserva.getCancha().getKey().getCentroDeportivo(), null);
            reservasDTO.add(nuevaReservaDTO);
        }
        return reservasDTO;
    }

    @Transactional
    @GetMapping("/pruebaaaa2")
    public List<ReservaDTO> pruebbaaa2 (){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Reserva> listReservasUser = currentUser.getReservasHechas();
        List<ReservaDTO> reservaDTOList = new ArrayList<>();
        for (int i= 0; i<listReservasUser.size(); i++){
            Reserva currentReserva = listReservasUser.get(i);
            ReservaDTO newReservaDTO = new ReservaDTO(currentReserva.getFecha(), currentReserva.getHoraInicio(), currentReserva.getHoraFin(), currentReserva.getCancha().getKey().getNombre(), currentReserva.getCancha().getKey().getCentroDeportivo(), currentReserva.getUsuariosInvitados());
            reservaDTOList.add(newReservaDTO);
        }
        return reservaDTOList;
    }

    @Transactional
    @PostMapping("/hacerReservaDTO")//falta probar si funciona
    public void hacerReservaDTO(@RequestBody ReservaDTO reservaDTO) {
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Reserva> listReservasUser = currentUser.getReservasHechas();
        Cancha estaCancha = (Cancha) serviceServicio.obtenerServicioPorNombreYCentroDep(reservaDTO.getCanchaNombre(), reservaDTO.getCentroDepNombre()).get();
        List<UserEmpleado> listaEmplQueLlega = reservaDTO.getUsuariosInvitados();
        List<UserEmpleado> listaEmplAGuardar = new ArrayList<>();
        for(int i = 0; i<listaEmplQueLlega.size(); i++){
            UserEmpleado curreeeentUser = listaEmplQueLlega.get(i);
            UserEmpleado userConTodosLosDatos = (UserEmpleado) serviceUser.obtenerUserPorId(curreeeentUser.getEmail()).get();
            listaEmplAGuardar.add(userConTodosLosDatos);
        }
        Reserva nuevaReserva = new Reserva(reservaDTO.getFecha(), reservaDTO.getHoraInicio(), reservaDTO.getHoraFin(), estaCancha, currentUser, listaEmplAGuardar);
        listReservasUser.add(nuevaReserva);
        currentUser.setReservasHechas(listReservasUser);
        serviceReserva.saveReserva(nuevaReserva);
        serviceUser.saveUserEmpleado(currentUser);
    }
    @GetMapping("/listaServiciosDTO")//valido para todos los empleados
    List<ServicioDTO> listServiciosDTO(){
        List<Servicio> listaQuery = serviceServicio.listaServicios("SERVICIO");
        List<ServicioDTO> listaDTO = new ArrayList<>();
        for (int i = 0; i< listaQuery.size(); i++){
            Servicio currentServ = listaQuery.get(i);
            ServicioDTO nuevoServ = new ServicioDTO(currentServ.getKey().getNombre(), currentServ.getKey().getCentroDeportivo(), currentServ.getCentroDeportivoServicio().getDireccion(), currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(),currentServ.getDescripcion(), currentServ.getTipo(),currentServ.getImagenes());
            listaDTO.add(nuevoServ);
        }
        return listaDTO;
    }
    @GetMapping("/listaServiciosCanchaDTO")//valido para todos los empleados
    List<CanchaDTO> listServiciosCanchaDTO(){
        List<Cancha> listaQuery = serviceServicio.listaServiciosCancha("CANCHA");
        List<CanchaDTO> listaDTO = new ArrayList<>();
        for (int i = 0; i< listaQuery.size(); i++){
            Cancha currentServ = listaQuery.get(i);
            CanchaDTO nuevaCancha = new CanchaDTO(currentServ.getKey().getNombre(), currentServ.getKey().getCentroDeportivo(),currentServ.getCentroDeportivoServicio().getDireccion(),currentServ.getPrecio(),currentServ.getDias(),currentServ.getHoraInicio(),currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo(),currentServ.getImagenes(),currentServ.getCupos());
            listaDTO.add(nuevaCancha);
        }
        return listaDTO;
    }
    @Transactional
    @PostMapping("/agregarServicioFavDTO")//falta probar si funciona
    public void agregarServicioFavDTO(@RequestBody ServicioDTO servicioDTO){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Servicio> listaFavs = currentUser.getServiciosFavoritos();
        Servicio servicio = serviceServicio.obtenerServicioPorNombreYCentroDep(servicioDTO.getNombreServicio(), servicioDTO.getNombreCentroDep()).get();
        listaFavs.add(servicio);
        currentUser.setServiciosFavoritos(listaFavs);
        serviceUser.saveUserEmpleado(currentUser);
    }

    @Transactional
    @PostMapping("/eliminarServicioFav")
    public void eliminarServicioFav(@RequestBody ServicioDTO servicioDTO){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Servicio> serviciosFavs = currentUser.getServiciosFavoritos();
        for (int i= 0; i<serviciosFavs.size();i++){
            Servicio current = serviciosFavs.get(i);
            if (current.getKey().getNombre().equals(servicioDTO.getNombreServicio()) && current.getKey().getCentroDeportivo().equals(servicioDTO.getNombreCentroDep())){
                serviciosFavs.remove(current);
            }
        }
        currentUser.setServiciosFavoritos(serviciosFavs);
        serviceUser.saveUserEmpleado(currentUser);
    }

    @GetMapping("/serviciosFavDeUnUserDTO")
    public List<ServicioDTO> serviciosFavDeUnUserDTO(){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado unUser = (UserEmpleado) serviceUser.obtenerUserPorId(emailUser).get();
        List<Servicio> listaFavs = serviceServicio.listaFavssByEmailUser(emailUser);
        List<ServicioDTO> listaFavs2 = new ArrayList<>();
        for (int i=0; i<listaFavs.size(); i++){
            Servicio currentServ = listaFavs.get(i);
            ServicioDTO nuevoServDTO = new ServicioDTO(currentServ.getKey().getNombre(), currentServ.getKey().getCentroDeportivo(),currentServ.getCentroDeportivoServicio().getDireccion(),currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo(), currentServ.getImagenes());
            listaFavs2.add(nuevoServDTO);
        }
        return  listaFavs2;
    }



}

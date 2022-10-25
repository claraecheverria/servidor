package com.example.servidor.controller;

import com.example.servidor.model.Cancha;
import com.example.servidor.model.Servicio;
import com.example.servidor.model.User;
import com.example.servidor.model.UserEmpleado;
import com.example.servidor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private List<String[]> userADevolver;
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

    @GetMapping("/listaServicios")//valido para todos los empleados
    List<Servicio> listServicios (){

        List<Servicio> listaQuery = serviceServicio.listaServicios("SERVICIO");
        List<Servicio> listaSinFav = new ArrayList<>();
        for (int i = 0; i< listaQuery.size(); i++){
            Servicio currentServ = listaQuery.get(i);
            Servicio nuevoServ = new Servicio(currentServ.getKey().getNombre(), currentServ.getCentroDeportivoServicio(), currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo(), currentServ.getImagenes());
            listaSinFav.add(nuevoServ);
        }
        return listaSinFav;

//        return serviceAll.listaServicios("SERVICIO");
    }

    @GetMapping("/listaServiciosCancha")//valido para todos los empleados
    List<Servicio> listServiciosCancha (){

        List<Servicio> listaQuery = serviceServicio.listaServicios("CANCHA");
        List<Servicio> listaSinFav = new ArrayList<>();
        for (int i = 0; i< listaQuery.size(); i++){
            Servicio currentServ = listaQuery.get(i);
            Servicio nuevoServ = new Servicio(currentServ.getKey().getNombre(), currentServ.getCentroDeportivoServicio(), currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(), currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo(), currentServ.getImagenes());
            listaSinFav.add(nuevoServ);
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
    public List<String[]> serviciosFavDeUnUser(){
        String emailUser = userADevolver.get(0)[1];
        List<String[]> listaFavs = serviceUser.obtenerServiciosFav(emailUser);

        return  listaFavs;
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<String> registerUser (@Valid @RequestBody User user){
////        serviceAll.saveUser(user);
//        return ResponseEntity.ok("User is valid");
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }


}

package com.example.servidor.controller;

import com.example.servidor.model.Servicio;
import com.example.servidor.model.User;
import com.example.servidor.model.UserEmpleado;
import com.example.servidor.service.ServiceAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private List<String[]> userADevolver;
    @Autowired
    private ServiceAll serviceAll;
    @GetMapping("/listausers")
    List<User> all() {
        return serviceAll.listaUsuarios();
    }
    @GetMapping("/devuelveUser")//para login, queda guardado toda la info en userADevolver
    List<String[]> devuelveUser(){
        return userADevolver;
    }
    @PostMapping("/userParaCheck")//para login
    public void userParaCheck(@RequestBody User user){
        userADevolver = serviceAll.obtenerUsuarioPorIdyPassword(user);
    }

    @GetMapping("/listaServicios")//valido para todos los empleados
    List<Servicio> listServicios (){
        return serviceAll.listaServicios("SERVICIO");
    }

    @GetMapping("/listaServiciosCancha")//valido para todos los empleados
    List<Servicio> listServiciosCancha (){
        return serviceAll.listaServicios("CANCHA");
    }

    @PostMapping("/agregarServicioFav")//falta probar si funciona
    public void agregarServicioFav(@RequestBody Servicio servicio){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = serviceAll.obtenerUserEmplPorId(emailUser);
        List<Servicio> listaFavs = currentUser.getServiciosFavoritos();
        listaFavs.add(servicio);
        currentUser.setServiciosFavoritos(listaFavs);
        serviceAll.saveUserEmpleado(currentUser);
    }

    @PostMapping("/eliminarServicioFav")//falta probar si funciona
    public void eliminarServicioFav(@RequestBody Servicio servicio){
        String emailUser = userADevolver.get(0)[1];
        UserEmpleado currentUser = serviceAll.obtenerUserEmplPorId(emailUser);
        List<Servicio> listaFavs = currentUser.getServiciosFavoritos();
        if (listaFavs.contains(servicio)){
            listaFavs.remove(servicio);
        }
        currentUser.setServiciosFavoritos(listaFavs);
        serviceAll.saveUserEmpleado(currentUser);
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

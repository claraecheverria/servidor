package com.example.servidor.controller;

import com.example.servidor.model.CentroDeportivo;
import com.example.servidor.model.Servicio;
import com.example.servidor.model.UserCentroDeportivo;
import com.example.servidor.service.ServiceAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/centroDeportivo")
public class CentroDepRestController {

    @Autowired
    private ServiceAll serviceAll;

    @GetMapping("/listaCentrosDep")// para admin
    List<CentroDeportivo> listaCentrosDep() {
        return serviceAll.listarCentrosDep();
    }

    @PostMapping("/crearCentroDep")//para admin
    public void crearCentroDep(@Valid @RequestBody CentroDeportivo centroDeportivo){
        serviceAll.guardarCentroDep(centroDeportivo);
    }
    @PostMapping("/crearUserCentroDep")//para admin
    public void crearUserCentroDep (@Valid @RequestBody UserCentroDeportivo userCentroDeportivo){
        CentroDeportivo esteCentro = userCentroDeportivo.getCentroDeportivo();
        UserCentroDeportivo guardarEste = new UserCentroDeportivo(userCentroDeportivo.getEmail(), userCentroDeportivo.getCedula(), userCentroDeportivo.getNombre(), userCentroDeportivo.getPassword(), userCentroDeportivo.getTelefono(), esteCentro);
        serviceAll.saveUserCentroDep(guardarEste);
    }
    @PostMapping("/crearServicioCentroDep")//no funciona
    public void crearServicioCentroDep (@Valid @RequestBody Servicio servicio){
        CentroDeportivo esteCentro = servicio.getCentroDeportivoServicio();
        System.out.println("Estoy aqui!!!!");
//        Servicio guradarEste = new Servicio(servicio.getNombre(), servicio.getPrecio(), servicio.getHorario(), servicio.getDescripcion(), servicio.getTipo(), esteCentro);
//        serviceAll.saveServicioCentroDep(guradarEste);
    }

    @GetMapping("/prueba")
    public String prueba(){
        CentroDeportivo unCentro = serviceAll.obtenerCentroDepPorId("SuperGYM").get();

        Servicio unServicio = new Servicio("yogaa", unCentro, 555L, "lunes11hs", "clase de yoga para principiantes", "Clases" );
        System.out.println("HOLA");
        System.out.println(unCentro.getNombre());
        serviceAll.saveServicioCentroDep(unServicio);
        return "prueba check";
    }
}

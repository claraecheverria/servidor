package com.example.servidor.controller;

import com.example.servidor.model.CentroDeportivo;
import com.example.servidor.model.UserCentroDeportivo;
import com.example.servidor.service.ServiceAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/centroDeportivo")
public class CentroDepRestController {

    @Autowired
    private ServiceAll serviceAll;

    @GetMapping("/listaCentrosDep")
    List<CentroDeportivo> listaCentrosDep() {
        return serviceAll.listarCentrosDep();
    }

    @PostMapping("/crearCentroDep")
    public void crearCentroDep(@Valid @RequestBody CentroDeportivo centroDeportivo){
        serviceAll.guardarCentroDep(centroDeportivo);
    }
    @PostMapping("/crearUserCentroDep")
    public void crearUserCentroDep (@Valid @RequestBody UserCentroDeportivo userCentroDeportivo){
        CentroDeportivo esteCentro = userCentroDeportivo.getCentroDeportivo();
        UserCentroDeportivo guardarEste = new UserCentroDeportivo(userCentroDeportivo.getEmail(), userCentroDeportivo.getCedula(), userCentroDeportivo.getNombre(), userCentroDeportivo.getPassword(), userCentroDeportivo.getTelefono(), esteCentro);
        serviceAll.saveUserCentroDep(guardarEste);
    }
}

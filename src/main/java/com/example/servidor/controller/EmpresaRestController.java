package com.example.servidor.controller;

import com.example.servidor.model.Empresa;
import com.example.servidor.model.UserEmpresa;
import com.example.servidor.service.ServiceAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {
    @Autowired
    private ServiceAll serviceAll;

    @GetMapping("/listaempresas")
    List<Empresa> listaEmpresas() {
        return serviceAll.listarEmpresas();
    }

    @PostMapping("/crearEmpresa")
    public void crearEmpresa(@Valid @RequestBody Empresa empresa){
        serviceAll.guardarEmpresa(empresa);
    }
    @PostMapping("/crearUserEmpresa")
    public void crearUserEmpresa (@Valid @RequestBody UserEmpresa userEmpresa){
        Empresa estaEmpresa = userEmpresa.getEmpresa();
        UserEmpresa guardarEste = new UserEmpresa(userEmpresa.getEmail(), userEmpresa.getCedula(), userEmpresa.getNombre(), userEmpresa.getPassword(), userEmpresa.getTelefono(), estaEmpresa);
        serviceAll.saveUserEmpresa(guardarEste);
    }
}

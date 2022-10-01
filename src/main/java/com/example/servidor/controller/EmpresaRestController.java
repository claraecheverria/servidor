package com.example.servidor.controller;

import com.example.servidor.model.Empresa;
import com.example.servidor.model.User;
import com.example.servidor.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listaempresas")
    List<Empresa> listaEmpresas() {
        return empresaService.listarEmpresas();
    }

    @PostMapping
    public void crearEmpresa(@RequestBody Empresa empresa){
        empresaService.guardarEmpresa(empresa);
    }
}

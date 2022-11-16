package com.example.servidor.controller;

import com.example.servidor.DTOs.UserEmpleadoDTO;
import com.example.servidor.model.Empresa;
import com.example.servidor.model.UserEmpleado;
import com.example.servidor.model.UserEmpresa;
import com.example.servidor.service.ServiceAll;
import com.example.servidor.service.ServiceEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private ServiceEmpresa serviceEmpresa;

    @GetMapping("/listaempresas")
    List<Empresa> listaEmpresas() {
        return serviceEmpresa.listarEmpresas();
    }

    @GetMapping("/listaEmpleadosDeEmpresa")//funciona
    List<UserEmpleadoDTO> listaEmpleadosDeEmpresa() {
        String empresaNombre = userRestController.getUserADevolver().get(0)[9];
        List<UserEmpleado> empleados = serviceEmpresa.listaEmpleadosDeEmpresa(empresaNombre);
        List<UserEmpleadoDTO> empleadosDTO = new ArrayList<>();
        for (int i=0;i<empleados.size();i++){
            UserEmpleado current = empleados.get(i);
            UserEmpleadoDTO nuevoEMplDTO = new UserEmpleadoDTO(current.getEmail(), current.getCedula(), current.getNombre(), current.getPassword(), current.getTelefono(), current.getVencimientoCarne(), current.getSaldo(), current.getEmpresaEmpl().getNombre());
            empleadosDTO.add(nuevoEMplDTO);
        }
        return empleadosDTO;
    }

    @PostMapping("/crearEmpresa")
    public void crearEmpresa(@Valid @RequestBody Empresa empresa){
        serviceEmpresa.guardarEmpresa(empresa);
    }
    @PostMapping("/crearUserEmpresa")
    public void crearUserEmpresa (@Valid @RequestBody UserEmpresa userEmpresa){
        Empresa estaEmpresa = userEmpresa.getEmpresa();
        UserEmpresa guardarEste = new UserEmpresa(userEmpresa.getEmail(), userEmpresa.getCedula(), userEmpresa.getNombre(), userEmpresa.getPassword(), userEmpresa.getTelefono(), estaEmpresa);
        serviceEmpresa.saveUserEmpresa(guardarEste);
    }
}

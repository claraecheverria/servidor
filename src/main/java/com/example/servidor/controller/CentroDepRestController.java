package com.example.servidor.controller;

import com.example.servidor.model.*;
import com.example.servidor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/centroDeportivo")
public class CentroDepRestController {

    @Autowired
    private UserRestController userRestController;
    @Autowired
    private ServiceUser serviceUser;
    @Autowired
    private ServiceCentroDeportivo serviceCentroDeportivo;
    @Autowired
    private ServiceServicio serviceServicio;
    @Autowired
    private ServiceIngreso serviceIngreso;

    @GetMapping("/listaCentrosDep")// para admin
    List<CentroDeportivo> listaCentrosDep() {
        return serviceCentroDeportivo.listarCentrosDep();
    }

    @PostMapping("/crearCentroDep")//para admin
    public void crearCentroDep(@Valid @RequestBody CentroDeportivo centroDeportivo){
        serviceCentroDeportivo.guardarCentroDep(centroDeportivo);
    }
    @PostMapping("/crearUserCentroDep")//para admin
    public void crearUserCentroDep (@Valid @RequestBody UserCentroDeportivo userCentroDeportivo){
        CentroDeportivo esteCentro = userCentroDeportivo.getCentroDeportivo();
        UserCentroDeportivo guardarEste = new UserCentroDeportivo(userCentroDeportivo.getEmail(), userCentroDeportivo.getCedula(), userCentroDeportivo.getNombre(), userCentroDeportivo.getPassword(), userCentroDeportivo.getTelefono(), esteCentro);
        serviceUser.saveUserCentroDep(guardarEste);
    }
    @PostMapping("/crearServicioCentroDep")//no funciona?
    public void crearServicioCentroDep (@Valid @RequestBody Servicio servicio){
        CentroDeportivo unCentro = serviceCentroDeportivo.obtenerCentroDepPorId(servicio.getCentroDeportivoServicio().getNombre()).get();
        Set<Imagen> imagenes = servicio.getImagenes();
        Set<Imagen> imagenesAGuardar = new HashSet<>();
        int currentIndex = 0;
        for (Imagen element : imagenes) {
            System.out.println("Element at index" +currentIndex +" is: "+ element.getImagen());
            Imagen nuevaImagen = new Imagen(element.getImagen());
            imagenesAGuardar.add(nuevaImagen);
            if (currentIndex == imagenes.size()){
                break;
            }
            currentIndex++;
        }
        Servicio guardarEste = new Servicio(servicio.getKey().getNombre(), unCentro, servicio.getPrecio(), servicio.getDias(),servicio.getHoraInicio(),servicio.getHoraFin(), servicio.getDescripcion(), servicio.getTipo(), imagenesAGuardar);
        serviceServicio.saveServicioCentroDep(guardarEste);
    }

    @GetMapping("/listaServiciosEsteCentroDep")//no se usa ?
    public List<Servicio> listaServiciosEsteCentroDep(){
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        List<Servicio> servicios = serviceServicio.listaServiciosByCentroDep(centroDepNombre);
        return servicios;
    }

    @GetMapping("/listaServiciosUnCentroDep")
    public List<Servicio> listaServiciosUnCentroDep(){
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        List<Servicio> servicios = serviceServicio.listaServiciosByCentroDepAndType("SERVICIO", centroDepNombre);
        return servicios;
    }

    @GetMapping("/listaCanchasUnCentroDep")
    public List<Cancha> listaCanchasUnCentroDep(){
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        List<Cancha> canchas = serviceServicio.listaCanchasByCentroDepAndType("CANCHA", centroDepNombre);
        return canchas;
    }

    @PostMapping("/guardarIngreso")
    public void guardarIngreso(@RequestBody Ingreso ingreso){
        System.out.println(ingreso.getServicio().getClass());
        String emailUserEmpl = ingreso.getUserEmpleado().getEmail();
        UserEmpleado esteUsrEmpl = (UserEmpleado) serviceUser.obtenerUserPorId(emailUserEmpl).get();
        Ingreso nuevoIngreso = new Ingreso(ingreso.getFecha(), ingreso.getHoraInicio(),ingreso.getHoraFin(),ingreso.getServicio(), esteUsrEmpl, ingreso.getImporte());
        serviceIngreso.saveIngreso(nuevoIngreso);
        Long saldo = esteUsrEmpl.getSaldo();
        saldo = saldo - ingreso.getImporte();
        esteUsrEmpl.setSaldo(saldo);
        serviceUser.saveUserEmpleado(esteUsrEmpl);
    }
    @PostMapping("/guardarFoto")//para admin
    public void crearGuardarFoto (@RequestBody String encodedString){
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        List<Servicio> servicios = serviceServicio.listaServiciosByCentroDep(centroDepNombre);
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        Imagen nuevaImagen = new Imagen(encodedString);
        Set<Imagen> setImagenes = new HashSet<>();
        setImagenes.add(nuevaImagen);
        for (int i = 0; i< servicios.size(); i++){
            Servicio unServ = servicios.get(i);
            unServ.setImagenes(setImagenes);
            serviceServicio.saveServicioCentroDep(unServ);
        }
        System.out.println("Imagen guardadaaa");
    }

    @GetMapping("/prueba")
    public String prueba(){
        CentroDeportivo unCentro = serviceCentroDeportivo.obtenerCentroDepPorId("SuperGYM").get();
        ServicioIdNew nuevaId = new ServicioIdNew();
        nuevaId.setNombre("Pilates");
        nuevaId.setCentroDeportivo("SuperGYM");
        Servicio unServicio = serviceServicio.obtenerServicioById(nuevaId).get();
        Set<DiasDeLaSemana> diass = new HashSet<>();
        Set<DiasDeLaSemana> diass2 = unServicio.getDias();
        diass2.add(DiasDeLaSemana.Lunes);
        diass2.add(DiasDeLaSemana.Miercoles);
        unServicio.setDias(diass2);
        unServicio.setPrecio(300L);
        System.out.println(diass2.size());
//        Servicio unServicio = new Servicio("yogaa", unCentro, 555L, "lunes11hs", "clase de yoga para principiantes", "Clases" );
        System.out.println("HOLA");
        System.out.println(unServicio.getDescripcion());
        serviceServicio.saveServicioCentroDep(unServicio);
        return "prueba check";
    }
}

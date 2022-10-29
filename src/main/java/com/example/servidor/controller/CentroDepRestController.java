package com.example.servidor.controller;

import com.example.servidor.model.*;
import com.example.servidor.service.ServiceAll;
import com.example.servidor.service.ServiceCentroDeportivo;
import com.example.servidor.service.ServiceServicio;
import com.example.servidor.service.ServiceUser;
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
        CentroDeportivo esteCentro = servicio.getCentroDeportivoServicio();
        CentroDeportivo unCentro = serviceCentroDeportivo.obtenerCentroDepPorId(servicio.getCentroDeportivoServicio().getNombre()).get();
//        System.out.println(esteCentro.getDireccion());
//        System.out.println("Estoy aqui!!!!");
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
    @PostMapping("/guardarFoto")//para admin
    public void crearGuardarFoto (@RequestBody String encodedString){
        System.out.println(encodedString);
        System.out.println(userRestController.getUserADevolver().get(0)[8]);
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        List<Servicio> servicios = serviceServicio.listaServiciosByCentroDep(centroDepNombre);
        Servicio unServ = servicios.get(0);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        Imagen nuevaImagen = new Imagen(encodedString);
        System.out.println(nuevaImagen.getImagen());
        Set<Imagen> setImagenes = new HashSet<>();
        setImagenes.add(nuevaImagen);
        unServ.setImagenes(setImagenes);
        System.out.println(setImagenes.size());
        System.out.println(unServ.getKey().getNombre());
        System.out.println(unServ.getImagenes().size());
//        unServ.setImagen(encodedString);
        serviceServicio.saveServicioCentroDep(unServ);
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

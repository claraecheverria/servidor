package com.example.servidor.controller;

import com.example.servidor.DTOs.CanchaDTO;
import com.example.servidor.DTOs.IngresoDTO;
import com.example.servidor.DTOs.ReservaDTO;
import com.example.servidor.DTOs.ServicioDTO;
import com.example.servidor.model.*;
import com.example.servidor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private ServiceReserva serviceReserva;

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

    @PostMapping("/crearServicioCentroDepDTO")//no funciona?
    public void crearServicioCentroDepDTO (@RequestBody ServicioDTO servicioDTO){
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        CentroDeportivo unCentro = serviceCentroDeportivo.obtenerCentroDepPorId(centroDepNombre).get();
        Servicio guardarEste = new Servicio(servicioDTO.getNombreServicio(), unCentro, servicioDTO.getPrecio(), servicioDTO.getDias(), servicioDTO.getHoraInicio(), servicioDTO.getHoraFin(), servicioDTO.getDescripcion(), servicioDTO.getTipo(), servicioDTO.getImagenes());
        serviceServicio.saveServicioCentroDep(guardarEste);
    }

    @GetMapping("/listaServiciosUnCentroDepDTO")
    public List<ServicioDTO> listaServiciosUnCentroDepDTO(){
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        List<Servicio> servicios = serviceServicio.listaServiciosByCentroDepAndType("SERVICIO", centroDepNombre);
        List<ServicioDTO> serviciosDTO = new ArrayList<>();
        for (int i= 0;i<servicios.size();i++){
            Servicio currentServ = servicios.get(i);
            ServicioDTO nuevoServDTO = new ServicioDTO(currentServ.getKey().getNombre(), currentServ.getKey().getCentroDeportivo(), currentServ.getCentroDeportivoServicio().getDireccion(), currentServ.getPrecio(), currentServ.getDias(), currentServ.getHoraInicio(),currentServ.getHoraFin(), currentServ.getDescripcion(), currentServ.getTipo());
            serviciosDTO.add(nuevoServDTO);
        }
        return serviciosDTO;
    }

    @GetMapping("/listaCanchasUnCentroDepDTO")
    public List<CanchaDTO> listaCanchasUnCentroDepDTO(){
        String centroDepNombre = userRestController.getUserADevolver().get(0)[8];
        List<Cancha> canchas = serviceServicio.listaCanchasByCentroDepAndType("CANCHA", centroDepNombre);
        List<CanchaDTO> canchasDTO = new ArrayList<>();
        for (int i = 0; i<canchas.size();i++){
            Cancha currentCancha = canchas.get(i);
            CanchaDTO nuevaCanchaDTO = new CanchaDTO(currentCancha.getKey().getNombre(), currentCancha.getKey().getCentroDeportivo(), currentCancha.getCentroDeportivoServicio().getDireccion(), currentCancha.getPrecio(), currentCancha.getDias(), currentCancha.getHoraInicio(), currentCancha.getHoraFin(), currentCancha.getDescripcion(), currentCancha.getTipo(),currentCancha.getCupos());
            canchasDTO.add(nuevaCanchaDTO);
        }
        return canchasDTO;
    }

    @PostMapping("/guardarIngresoServicioDTO")
    @ResponseBody
    public boolean guardarIngresoServicioDTO(@RequestBody IngresoDTO ingresoDTO){
        boolean carneEnFecha = false;
        String emailUserEmpl = ingresoDTO.getEmailUserEmpleado();
        UserEmpleado esteUsrEmpl = (UserEmpleado) serviceUser.obtenerUserPorId(emailUserEmpl).get();
        Servicio esteServ = serviceServicio.obtenerServicioPorNombreYCentroDep(ingresoDTO.getServicioNombre(), ingresoDTO.getCentroDepNombre()).get();
        Ingreso nuevoIngreso = new Ingreso(ingresoDTO.getFecha(), ingresoDTO.getHoraInicio(), ingresoDTO.getHoraFin(), esteServ, esteUsrEmpl, ingresoDTO.getImporte());
        if (ingresoDTO.getFecha().isBefore(esteUsrEmpl.getVencimientoCarne()) || ingresoDTO.getFecha().isEqual(esteUsrEmpl.getVencimientoCarne())){
            serviceIngreso.saveIngreso(nuevoIngreso);
            Long saldo = esteUsrEmpl.getSaldo();
            saldo = saldo - ingresoDTO.getImporte();
            esteUsrEmpl.setSaldo(saldo);
            serviceUser.saveUserEmpleado(esteUsrEmpl);
            carneEnFecha = true;
        }

        return carneEnFecha;
    }

    @PostMapping("/guardarIngresoCanchaDTO")
    @ResponseBody
    public boolean guardarIngresoCanchaDTO(@RequestBody IngresoDTO ingresoDTO){//no funciona por cascade
        String emailUserEmpl = ingresoDTO.getEmailUserEmpleado();
        UserEmpleado esteUsrEmpl = (UserEmpleado) serviceUser.obtenerUserPorId(emailUserEmpl).get();
        Cancha esteServ = (Cancha) serviceServicio.obtenerServicioPorNombreYCentroDep(ingresoDTO.getServicioNombre(), ingresoDTO.getCentroDepNombre()).get();
        Ingreso nuevoIngreso = new Ingreso(ingresoDTO.getFecha(), ingresoDTO.getHoraInicio(), ingresoDTO.getHoraFin(), esteServ, esteUsrEmpl, ingresoDTO.getImporte());
        List<Reserva> reservasCliente = serviceReserva.obtenerReservasPorFechaYCanchaYEmail(ingresoDTO.getEmailUserEmpleado(), ingresoDTO.getFecha(), ingresoDTO.getServicioNombre(), ingresoDTO.getCentroDepNombre());
        Boolean tieneReserva = false;
        for (int i= 0;i< reservasCliente.size();i++){
            Reserva current = reservasCliente.get(i);
            if (current.getHoraInicio() == ingresoDTO.getHoraInicio() && current.getHoraFin()==ingresoDTO.getHoraFin()){
                current.setFueIngresada(true);
                tieneReserva= true;
                serviceIngreso.saveIngreso(nuevoIngreso);
                Long saldo = esteUsrEmpl.getSaldo();
                saldo = saldo - ingresoDTO.getImporte();
                esteUsrEmpl.setSaldo(saldo);
                serviceUser.saveUserEmpleado(esteUsrEmpl);
            }else {
                System.out.println("No tiene reserva");
            }
        }
        return tieneReserva;
    }

    @GetMapping("/prueba5")
    public List<ReservaDTO> pueba5(){
        List<Reserva> reservas = serviceReserva.obtenerReservasPorFechaYCanchaYEmail("empl1@gmail.com", LocalDate.of(2022,11,18), "Cancha 1", "Centro4");
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (int i= 0;i< reservas.size();i++){
            Reserva current = reservas.get(i);
            ReservaDTO nueva = new ReservaDTO(current.getFecha(), current.getHoraInicio(),current.getHoraFin(),current.getCancha().getKey().getNombre(), current.getCancha().getKey().getCentroDeportivo(), null);
            reservasDTO.add(nueva);
        }
        return reservasDTO;
    }
    @PostMapping("/guardarFoto")
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
}

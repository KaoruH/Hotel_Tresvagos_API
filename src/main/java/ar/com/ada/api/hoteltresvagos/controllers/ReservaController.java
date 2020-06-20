package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.hoteltresvagos.entities.Reserva;
import ar.com.ada.api.hoteltresvagos.services.ReservaService;

@Controller
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @GetMapping("/reservas")
    public List<Reserva> getReserva(@RequestParam(value = "nombre", required = false) String nombre){

        List<Reserva> reservas;

        if (nombre == null) {
            reservas = reservaService.getReservas();

        } else {
            reservas = reservaService.buscarReservasPorNombre(nombre);
        }

        return reservas;
        
    }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable int reservaId){

        Reserva reserva = reservaService.buscarPorId(reservaId);

        if(reserva == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(reserva);
    }

    // @PostMapping("/reservas")
    // public ResponseEntity<?> postReserva(@RequestBody Reserva reserva){

    //     GenericResponse genericRespo = new GenericResponse();

    //     boolean respuesta = reservaService.



        
    // }


    
}
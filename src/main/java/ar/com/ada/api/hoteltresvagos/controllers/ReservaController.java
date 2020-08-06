package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.hoteltresvagos.entities.Reserva;
import ar.com.ada.api.hoteltresvagos.services.ReservaService;

@RestController
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

    @GetMapping("/reservas/{dni}")
    public ResponseEntity<List<Reserva>> getReservaById(@PathVariable Integer dni){

        List<Reserva> listaReservas = reservaService.buscarPorDni(dni);

        if(listaReservas == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(listaReservas);
    }




    
}
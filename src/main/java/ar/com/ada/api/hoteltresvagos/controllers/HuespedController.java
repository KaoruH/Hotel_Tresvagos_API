package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.hoteltresvagos.entities.Huesped;
import ar.com.ada.api.hoteltresvagos.models.request.PutHuespedRequest;
import ar.com.ada.api.hoteltresvagos.models.response.GenericResponse;
import ar.com.ada.api.hoteltresvagos.services.HuespedService;

@RestController
public class HuespedController {

    @Autowired
    HuespedService huespedService;

    @GetMapping("/huespedes")
    public List<Huesped> getHuespedes(@RequestParam(value = "nombre", required = false) String nombre) {

        List<Huesped> listaHuesped;

        if (nombre == null) {
            listaHuesped = huespedService.buscarTodosOrdenadoPorNombre();
        } else {
            listaHuesped = huespedService.buscarTodosPorNombre(nombre);
        }

        return listaHuesped;
    }

    @GetMapping("/huespedes/{dni}")
    public ResponseEntity<Huesped> getHuespedById(@PathVariable Integer dni) {

        Huesped huesped = huespedService.buscarPorDni(dni);

        if (huesped != null) {
            return ResponseEntity.ok(huesped);
        
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/huespedes")
    public ResponseEntity<?> postHuesped(@RequestBody Huesped huesped) {

        GenericResponse r = new GenericResponse();

        boolean resultado = huespedService.crearHuesped(huesped);

        if (resultado) {
            r.isOk = true;
            r.id = huesped.getHuespedId();
            r.message = "Creaste una huesped con éxito.";
            return ResponseEntity.ok(r);

        } else {

            r.isOk = false;
            r.message = "No se pudo crear el huesped! Ya existe alguién con ese DNI.";

            return ResponseEntity.badRequest().body(r);
        }
    }

    @PutMapping("/huespedes/{id}")
    public ResponseEntity<?> putHuesped(@PathVariable Integer id, @RequestBody PutHuespedRequest req) {

        Huesped huespedOriginal = huespedService.buscarPorId(id);

        if (huespedOriginal != null) {

            huespedService.actualizarHuesped(huespedOriginal, req);

            GenericResponse resp = new GenericResponse();

            resp.isOk = true;
            resp.id = huespedOriginal.getHuespedId();
            resp.message = "Huesped actualizado con éxito.";
            return ResponseEntity.ok(resp);
            
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
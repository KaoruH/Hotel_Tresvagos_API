package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.hoteltresvagos.entities.Huesped;
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

    @GetMapping("/huespedes/{id}")
    public ResponseEntity<Huesped> getHuespedById(@PathVariable int huespedId) {

        Huesped huesped = huespedService.buscarPorId(huespedId);

        if (huesped == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(huesped);
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
    public ResponseEntity<?> postHuesped(@PathVariable int id, @RequestBody Huesped req) {

        GenericResponse r = new GenericResponse();

        Huesped huespedOriginal = huespedService.buscarPorId(id);

        if (huespedOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = huespedService.actualizarHuesped(huespedOriginal, req);

        if (resultado) {
            r.isOk = true;
            r.id = req.getHuespedId();
            r.message = "Huesped actualizado con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo actualizra el huesped.";

            return ResponseEntity.badRequest().body(r);
        }
    }

}
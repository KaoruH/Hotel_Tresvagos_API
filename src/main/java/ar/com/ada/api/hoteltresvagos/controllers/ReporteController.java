package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.hoteltresvagos.entities.reportes.ReporteImportesEstado;
import ar.com.ada.api.hoteltresvagos.entities.reportes.ReporteImportesHuesped;
import ar.com.ada.api.hoteltresvagos.services.ReporteService;

@RestController
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("/reportes")
    public ResponseEntity<?> getReportes(@RequestParam(value = "nombre", required = false) String nombre) {

        switch (nombre) {
            case "ResumenReservasPorEstados":

                List<ReporteImportesEstado> re = reporteService.getReporteReservasPorEstado();

                return ResponseEntity.ok(re);

            case "ResumenReservasPorHuespedes":

                List<ReporteImportesHuesped> rh = reporteService.getReporteReservasPorHuesped();

                return ResponseEntity.ok(rh);

            default:

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
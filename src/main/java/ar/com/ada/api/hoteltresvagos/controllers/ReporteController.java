package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.ada.api.hoteltresvagos.entities.reportes.ReporteImportesEstado;
import ar.com.ada.api.hoteltresvagos.entities.reportes.ReporteImportesHuesped;
import ar.com.ada.api.hoteltresvagos.services.ReporteService;

@Controller
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("/reportes")
    public ResponseEntity<?> getReportes(@RequestParam(value = "nombre", required = false) String nombre) {

        switch (nombre) {
            case "ResumenReservasPorEstado":

                List<ReporteImportesEstado> reporteEstado = reporteService.getReporteReservasPorEstado();

                return ResponseEntity.ok(reporteEstado);

            case "ResumenReservasPorHuesped":

                List<ReporteImportesHuesped> reporteHuesped = reporteService.getReporteReservasPorHuesped();

                return ResponseEntity.ok(reporteHuesped);

            default:
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
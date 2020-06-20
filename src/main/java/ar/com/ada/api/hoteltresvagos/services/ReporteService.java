package ar.com.ada.api.hoteltresvagos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.hoteltresvagos.entities.reportes.ReporteImportesEstado;
import ar.com.ada.api.hoteltresvagos.entities.reportes.ReporteImportesHuesped;
import ar.com.ada.api.hoteltresvagos.repos.ReservaRepository;

@Service
public class ReporteService {

    @Autowired
    ReservaRepository reservaRepo;

    public List<ReporteImportesEstado> getReporteReservasPorEstado(){

        return reservaRepo.getReporteReservaPorEstado();
    }

    public List<ReporteImportesHuesped> getReporteReservasPorHuesped(){

        return reservaRepo.getReporteReservasPorHuesped();
    }
    
}
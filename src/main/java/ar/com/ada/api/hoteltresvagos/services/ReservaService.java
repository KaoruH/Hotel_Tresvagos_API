package ar.com.ada.api.hoteltresvagos.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.hoteltresvagos.entities.Huesped;
import ar.com.ada.api.hoteltresvagos.entities.Reserva;
import ar.com.ada.api.hoteltresvagos.repos.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepo;

    @Autowired
    HuespedService huespedService;

    public List<Reserva> buscarReservasPorNombre(String nombre) {

        return reservaRepo.findByNombreHuesped(nombre);
    }

    public void grabar(Reserva reserva) {

        reservaRepo.save(reserva);
    }

    public List<Reserva> getReservas() {

        return reservaRepo.findAll();
    }

    public boolean baja(Reserva reserva) {

        reservaRepo.delete(reserva);
        return true;
    }

    public boolean baja(Integer reservaId){

        Reserva reserva = buscarPorId(reservaId);

        if (reserva == null) {
            return false;
        }
        return baja(reserva);
    }

    public List<Reserva> buscarPorDni(Integer dni) {

        return reservaRepo.findAllByReservaDniHuesped(dni);
    }

    public Reserva buscarPorId(Integer reservaId) {

        Optional<Reserva> reserva = reservaRepo.findById(reservaId);

        if (reserva.isPresent())
            return reserva.get();

        return null;
    }

    public Reserva crearReserva(Integer huespedId, LocalDate fechaIngreso, LocalDate fechaEgreso, BigDecimal importePagado) {

        Reserva reserva = new Reserva();

        reserva.setFechaReserva(LocalDate.now());
        reserva.setFechaIngreso(fechaIngreso);
        reserva.setFechaEgreso(fechaEgreso);
        reserva.setImporteReserva(new BigDecimal(500));
        reserva.setImporteTotal(new BigDecimal(10000));
        reserva.setImportePagado(importePagado);
        reserva.setImporteAdeudado(reserva.getImporteTotal().subtract(reserva.getImportePagado()));

        if (reserva.getImporteAdeudado().doubleValue() == 0) {

            reserva.setEstadoId(10);

        } else {

            reserva.setEstadoId(20);

        }

        Huesped huesped = huespedService.buscarPorId(huespedId);
        huesped.agregarReserva(reserva);

        crearReserva(reserva);

        return reserva;

    }

    public void crearReserva(Reserva reserva) {

        grabar(reserva);

    }

    public boolean actualizarReserva(Reserva original, Reserva nueva) {
        // Aca solo dejamos que se actualize las fechas tanto de reserva como de ingreso y egreso

        original.setFechaReserva(LocalDate.now());
        original.setFechaIngreso(nueva.getFechaIngreso());
        original.setFechaEgreso(nueva.getFechaEgreso());

        grabar(original);

        return true;
        
    }


}
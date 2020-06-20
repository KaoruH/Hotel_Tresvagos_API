package ar.com.ada.api.hoteltresvagos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.hoteltresvagos.entities.Reserva;
import ar.com.ada.api.hoteltresvagos.repos.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepo;

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

    public boolean baja(int reservaId){

        Reserva reserva = buscarPorId(reservaId);

        if (reserva == null) {
            return false;
        }
        return baja(reserva);
    }

    public Reserva buscarPorId(int reservaId) {

        Optional<Reserva> reserva = reservaRepo.findById(reservaId);

        if (reserva.isPresent())
            return reserva.get();

        return null;
    }

    // TODO hacer un crear reserva
    // public boolean crearReserva(Reserva reserva){

    //     if (existe(huesped.getDni()))
    //         return false;

    //     grabar(huesped);
    //     return true;

    // }

    // TODO hacer un actualizar Reserva
    // public boolean actualizarHuesped(Huesped huespedOriginal, Huesped huespedConInfoNueva){

    //     huespedOriginal.setNombre(huespedConInfoNueva.getNombre());
    //     huespedOriginal.setDomicilio(huespedConInfoNueva.getDomicilio());
    //     huespedOriginal.setDomicilioAlternativo(huespedConInfoNueva.getDomicilioAlternativo());

    //     grabar(huespedOriginal);

    //     return true;
    // }

    // TODO hacer existe
    // public boolean existe(int dni){

    //     Reserva reserva = buscarPorId(reservaId);

    //     return huesped != null;
    // }


}
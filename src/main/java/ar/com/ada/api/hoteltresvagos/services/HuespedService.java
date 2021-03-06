package ar.com.ada.api.hoteltresvagos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.hoteltresvagos.entities.Huesped;
import ar.com.ada.api.hoteltresvagos.models.request.PutHuespedRequest;
import ar.com.ada.api.hoteltresvagos.repos.HuespedRepository;

@Service
public class HuespedService {

    @Autowired
    HuespedRepository huespedRepo;

    public void grabar(Huesped huesped){

        huespedRepo.save(huesped);
    }

    public boolean existe(Integer dni){

        Huesped huesped = buscarPorDni(dni);

        return huesped != null;
    }

    public Huesped buscarPorDni(Integer dni){

        return huespedRepo.findByDni(dni);
    }

    public List<Huesped> buscarTodosPorNombre(String nombre){

        return huespedRepo.findAllByNombreContiene(nombre);

    }

    public Huesped buscarPorNombre(String nombreHuesped){

        return huespedRepo.findByNombre(nombreHuesped);

    }

    public List<Huesped> buscarTodosOrdenadoPorNombre(){

        return huespedRepo.findAllOrderByName();
    }

    public List<Huesped> buscarTodos(){

        return huespedRepo.findAll();
    }

    public Huesped buscarPorId(Integer huespedId) {

        Optional<Huesped> b = huespedRepo.findById(huespedId);

        if (b.isPresent())
            return b.get();
        return null;
    }

    public boolean baja(Integer huespedId) {

        Huesped huesped = buscarPorId(huespedId);

        if (huesped == null)
            return false;

        return baja(huesped);
    }

    public boolean baja(Huesped huesped) {

        huespedRepo.delete(huesped);
        return true;
    }

    public boolean bajaPorDni(Integer dni){

        Huesped huesped = huespedRepo.findByDni(dni);

        if (huesped == null)
            return false;
            
        return baja(huesped);
    }

    public boolean crearHuesped(Huesped huesped){

        if (existe(huesped.getDni()))
            return false;

        grabar(huesped);
        return true;

    }

    public void actualizarHuesped(Huesped huespedOriginal, PutHuespedRequest huespedConInfoNueva){

        huespedOriginal.setNombre(huespedConInfoNueva.getNombre());
        huespedOriginal.setDomicilio(huespedConInfoNueva.getDomicilio());
        huespedOriginal.setDomicilioAlternativo(huespedConInfoNueva.getDomicilioAlternativo());

        grabar(huespedOriginal);
    }
    
}
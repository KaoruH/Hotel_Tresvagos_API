package ar.com.ada.api.hoteltresvagos.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.hoteltresvagos.entities.*;
import ar.com.ada.api.hoteltresvagos.entities.reportes.*;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query("FROM Huesped WHERE nombre like CONCAT('%', :nombre,'%')")
    List<Reserva> findByNombreHuesped(@Param("nombre") String nombre);

    @Query(value = "SELECT r.estadopago_id as estadoId, e.descripcion, count(r.reserva_id) as cantidadReservas, sum(r.importe_reserva) as totalImporteReserva, sum(r.importe_pagado) as totalImportePagado, sum(r.importe_total) importeTotal FROM reserva r INNER JOIN estadopago e ON r.estadopago_id = e.estadopago_id GROUP BY e.estadopago_id, e.descripcion", nativeQuery = true)
    List<ReporteImportesEstado> getReporteReservaPorEstado();

    @Query(value = "select h.huesped_id huespedId, h.nombre, sum(r.importe_reserva) importeReserva, sum(r.importe_pagado) importePagado, sum(r.importe_total) importeTotal from huesped h inner join reserva r on h.huesped_id = r.huesped_id group by h.huesped_id, h.nombre", nativeQuery = true)
    List<ReporteImportesHuesped> getReporteReservasPorHuesped();

    
}
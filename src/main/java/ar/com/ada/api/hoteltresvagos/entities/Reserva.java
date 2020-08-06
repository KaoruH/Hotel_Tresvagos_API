package ar.com.ada.api.hoteltresvagos.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;


@Entity
@Table(name="reserva")
public class Reserva {

    @Id
    @Column(name="reserva_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservaId;
    @Column(name="fecha_reserva")
    private LocalDate fechaReserva;
    @Column(name="fecha_ingreso")
    private LocalDate fechaIngreso;
    @Column(name="fecha_egreso")
    private LocalDate fechaEgreso;
    private Integer habitacion;
    @Column(name="importe_reserva")
    private BigDecimal importeReserva;
    @Column(name="importe_total")
    private BigDecimal importeTotal;
    @Column(name="importe_pagado")
    private BigDecimal importePagado;
    @Column(name="estadopago_id")
    private Integer estadoId;
    @ManyToOne
    @JoinColumn(name="huesped_id", referencedColumnName = "huesped_id")
    private Huesped huesped;
    @Column(name = "total_adeudado")
    private BigDecimal importeAdeudado;

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Integer getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Integer habitacion) {
        this.habitacion = habitacion;
    }

    public BigDecimal getImporteReserva() {
        return importeReserva;
    }

    public void setImporteReserva(BigDecimal importeReserva) {
        this.importeReserva = importeReserva;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getImportePagado() {
        return importePagado;
    }

    public void setImportePagado(BigDecimal importePagado) {
        this.importePagado = importePagado;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;

        
        this.huesped.getReservas().add(this);
    }

    @Override
    public String toString() {
        
        return " [reservaid = "+ this.getReservaId() + ", importe total = " + this.getImporteTotal() + "] ";
    }

    public BigDecimal getImporteAdeudado() {
        return importeAdeudado;
    }

    public void setImporteAdeudado(BigDecimal importeAdeudado) {
        this.importeAdeudado = importeAdeudado;
    }


    
}
package ar.com.ada.api.hoteltresvagos.entities.reportes;

import java.math.BigDecimal;

public interface ReporteImportesEstado {

    Integer getEstadoId();

    Integer getCantidadReservas();

    BigDecimal getTotalImporteReserva();

    BigDecimal getTotalImportePagado();

    BigDecimal getImporteTotal();

    String getDescripcion();
}
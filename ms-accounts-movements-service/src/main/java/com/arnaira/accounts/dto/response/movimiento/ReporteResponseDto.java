package com.arnaira.accounts.dto.response.movimiento;

import java.util.List;

public class ReporteResponseDto {
    private String codMensaje;
    private String mensaje;
    private String detalleError;
    private List<MovimientoDto> movimiento;

    public ReporteResponseDto(String codMensaje, String mensaje, String detalleError, List<MovimientoDto> movimiento) {
        this.codMensaje = codMensaje;
        this.mensaje = mensaje;
        this.detalleError = detalleError;
        this.movimiento = movimiento;
    }

    public ReporteResponseDto() {
    }

    public String getDetalleError() {
        return detalleError;
    }

    public void setDetalleError(String detalleError) {
        this.detalleError = detalleError;
    }

    public String getCodMensaje() {
        return codMensaje;
    }

    public void setCodMensaje(String codMensaje) {
        this.codMensaje = codMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<MovimientoDto> getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(List<MovimientoDto> movimiento) {
        this.movimiento = movimiento;
    }
}

package com.arnaira.accounts.dto.response.movimiento;

import com.arnaira.accounts.dto.response.common.MensajeDto;
import lombok.Data;

@Data
public class MovimientoResponseDto {
    private MensajeDto mensaje;
    private MovimientoDto movimiento;

    public MovimientoResponseDto(MensajeDto mensajeDto, MovimientoDto movimientoDto) {
    }

    public MovimientoResponseDto() {
    }

    public MensajeDto getMensaje() {
        return mensaje;
    }

    public void setMensaje(MensajeDto mensaje) {
        this.mensaje = mensaje;
    }

    public MovimientoDto getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoDto movimiento) {
        this.movimiento = movimiento;
    }
}

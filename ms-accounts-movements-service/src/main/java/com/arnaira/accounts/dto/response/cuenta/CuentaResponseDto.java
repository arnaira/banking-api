package com.arnaira.accounts.dto.response.cuenta;

import com.arnaira.accounts.dto.response.common.MensajeDto;
import lombok.Data;

@Data
public class CuentaResponseDto {
private MensajeDto mensaje;
private CuentaDto cuenta;

    public CuentaResponseDto(MensajeDto mensajeDto, CuentaDto cuentaDto) {
    }

    public CuentaResponseDto() {
    }

    public MensajeDto getMensaje() {
        return mensaje;
    }

    public void setMensaje(MensajeDto mensaje) {
        this.mensaje = mensaje;
    }

    public CuentaDto getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDto cuenta) {
        this.cuenta = cuenta;
    }
}

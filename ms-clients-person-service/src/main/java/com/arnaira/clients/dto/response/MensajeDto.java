package com.arnaira.clients.dto.response;

import lombok.Data;

@Data
public class MensajeDto {
    private String codMensaje;
    private String mensaje;
    private String detalleError;

    public MensajeDto() {
    }

    public MensajeDto(String codMensaje, String mensaje, String detalleError) {
        this.codMensaje = codMensaje;
        this.mensaje = mensaje;
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

    public String getDetalleError() {
        return detalleError;
    }

    public void setDetalleError(String detalleError) {
        this.detalleError = detalleError;
    }
}

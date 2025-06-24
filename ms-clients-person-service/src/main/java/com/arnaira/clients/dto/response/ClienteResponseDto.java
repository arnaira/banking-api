package com.arnaira.clients.dto.response;

import lombok.Data;


@Data

public class ClienteResponseDto {
    private MensajeDto mensaje;
    private ClienteDto cliente;

    public ClienteResponseDto() {
    }

    public ClienteResponseDto(MensajeDto mensaje, ClienteDto cliente) {
        this.mensaje = mensaje;
        this.cliente = cliente;
    }

    public MensajeDto getMensaje() {
        return mensaje;
    }

    public void setMensaje(MensajeDto mensaje) {
        this.mensaje = mensaje;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }
}

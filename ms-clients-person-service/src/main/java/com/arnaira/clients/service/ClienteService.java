package com.arnaira.clients.service;

import com.arnaira.clients.dto.request.ClienteRequestDto;
import com.arnaira.clients.dto.response.ClienteResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ClienteService {
    ClienteResponseDto crearCliente(ClienteRequestDto dto) throws JsonProcessingException;
    ClienteResponseDto obtenerClientePorId(Long id)  ;

    ClienteResponseDto actualizarCliente (Long id, ClienteRequestDto dto)  ;

    ClienteResponseDto eliminarCliente(Long id) ;
}

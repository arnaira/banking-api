package com.arnaira.clients.mapper;

import com.arnaira.clients.dto.request.ClienteRequestDto;
import com.arnaira.clients.dto.response.ClienteDto;
import com.arnaira.clients.dto.response.ClienteResponseDto;
import com.arnaira.clients.dto.response.MensajeDto;
import com.arnaira.clients.model.Cliente;
import com.arnaira.clients.model.Persona;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.arnaira.clients.util.ResponseCode.getMessage;


@Configuration
public class ClienteMapper {

    private final PasswordEncoder passwordEncoder;

    public ClienteMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    public Cliente toEntity(ClienteRequestDto request, Persona persona){
        persona.setId(request.getPersonaId());

        Cliente cliente = new Cliente();
        cliente.setClienteId(request.getClienteId());
        cliente.setContrasena(passwordEncoder.encode(request.getContrasena()));
        cliente.setPersona(persona);

        return cliente;
    }

    public ClienteResponseDto toResponse(Cliente cliente, String code){
      ClienteResponseDto response = new ClienteResponseDto();
        ClienteDto clienteDto = new ClienteDto();
        MensajeDto msj = new MensajeDto();

        msj.setCodMensaje(code);
        msj.setMensaje(getMessage(code));
        msj.setDetalleError("");

        clienteDto.setClienteId(cliente.getClienteId());
        clienteDto.setPersonaId(cliente.getPersona() != null ? cliente.getPersona().getId() : null);
        clienteDto.setContrasena(cliente.getContrasena());

        response.setMensaje(msj);
        response.setCliente(clienteDto);

      return response;

    }

}

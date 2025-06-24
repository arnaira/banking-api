package com.arnaira.clients.service.impl;

import com.arnaira.clients.dto.request.ClienteRequestDto;
import com.arnaira.clients.dto.response.ClienteResponseDto;
import com.arnaira.clients.mapper.ClienteMapper;
import com.arnaira.clients.model.Cliente;
import com.arnaira.clients.model.Persona;
import com.arnaira.clients.respository.ClienteRepository;
import com.arnaira.clients.respository.PersonaRepository;
import com.arnaira.clients.service.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final PersonaRepository personaRepository;

    private final ClienteMapper clienteMapper;


    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public ClienteServiceImpl(ObjectMapper objectMapper,RabbitTemplate rabbitTemplate,ClienteRepository clienteRepository, PersonaRepository personaRepository, ClienteMapper clienteMapper) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional
    public ClienteResponseDto crearCliente(ClienteRequestDto requestDto) throws JsonProcessingException {
        Optional<Persona> persona = personaRepository.findById(requestDto.getPersonaId());
        if (persona.isEmpty()) {
            return clienteMapper.toResponse(new Cliente(),"01");

        }
        Cliente cliente = clienteMapper.toEntity(requestDto, persona.get());

        if (clienteRepository.existsByClienteId(requestDto.getClienteId())){
            return clienteMapper.toResponse(cliente,"02");
        }

       Cliente savedCliente = clienteRepository.save(cliente);
        notificarNuevoCliente(savedCliente);
       return clienteMapper.toResponse(savedCliente,"00");
    }

    @Override
    public ClienteResponseDto obtenerClientePorId(Long id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return clienteMapper.toResponse(new Cliente(),"03");

        }

        return clienteMapper.toResponse(cliente.get(), "00");


    }




    @Override
    public ClienteResponseDto actualizarCliente(Long id, ClienteRequestDto requestDto) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        Optional<Persona> persona = personaRepository.findById(requestDto.getPersonaId());
        if (persona.isEmpty()) {
            return clienteMapper.toResponse(new Cliente(),"01");

        }
        if (clienteExistente.isEmpty()){
            return clienteMapper.toResponse(new Cliente(),"03");
        }

        if (!Objects.equals(clienteExistente.get().getPersona().getId(), persona.get().getId())){
            return clienteMapper.toResponse(new Cliente(),"04");
        }


        Cliente cliente = clienteMapper.toEntity(requestDto,new Persona());

        clienteExistente.get().setClienteId(cliente.getClienteId());
        clienteExistente.get().setContrasena(cliente.getContrasena());
        Cliente clienteActualizado = clienteRepository.save(clienteExistente.get());
        return clienteMapper.toResponse(clienteActualizado,"00");
    }

    @Override
    public ClienteResponseDto eliminarCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()){
            return clienteMapper.toResponse(new Cliente(),"03");
        }
        clienteRepository.delete(cliente.get());
            return  clienteMapper.toResponse(cliente.get(),"00");
    }

    public void notificarNuevoCliente(Cliente cliente) throws JsonProcessingException {
        String json =objectMapper.writeValueAsString(cliente);
       rabbitTemplate.convertAndSend("clienteExchange","cliente.creado", json);
    }
}

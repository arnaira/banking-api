package com.arnaira.accounts.listener;

import com.arnaira.accounts.dto.ClienteDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClientEventListener {

    private ObjectMapper objectMapper;

    public ClientEventListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "clienteCreadoQueue")
    public void recibirCliente(String mensajeJson) throws JsonProcessingException {
       ClienteDto cliente = objectMapper.readValue(mensajeJson, ClienteDto.class);

        System.out.println("Cliente recibido por RabbitMQ: "+ cliente);
    }
}

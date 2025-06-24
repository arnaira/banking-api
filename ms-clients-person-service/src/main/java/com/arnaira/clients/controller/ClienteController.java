package com.arnaira.clients.controller;

import com.arnaira.clients.dto.request.ClienteRequestDto;
import com.arnaira.clients.dto.response.ClienteDto;
import com.arnaira.clients.dto.response.ClienteResponseDto;
import com.arnaira.clients.dto.response.MensajeDto;
import com.arnaira.clients.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.arnaira.clients.util.ResponseCode.getMessage;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto>crearCliente(@RequestBody ClienteRequestDto request){
        try {
            ClienteResponseDto response = clienteService.crearCliente(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClienteResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new ClienteDto()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto>obtenerCliente(@PathVariable Long id){
        try{
        ClienteResponseDto response = clienteService.obtenerClientePorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClienteResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new ClienteDto()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> actualizarCliente(@PathVariable Long id,
                                                                @RequestBody ClienteRequestDto request){
        try{
            ClienteResponseDto response = clienteService.actualizarCliente(id, request);
        return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClienteResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new ClienteDto()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> eliminarCliente(@PathVariable Long id){
        try{
        ClienteResponseDto response = clienteService.eliminarCliente(id);
        return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClienteResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new ClienteDto()));
        }
    }
}

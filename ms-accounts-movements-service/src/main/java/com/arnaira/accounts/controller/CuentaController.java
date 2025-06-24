package com.arnaira.accounts.controller;

import com.arnaira.accounts.dto.request.cuenta.CuentaRequestDto;
import com.arnaira.accounts.dto.response.common.MensajeDto;
import com.arnaira.accounts.dto.response.cuenta.CuentaDto;
import com.arnaira.accounts.dto.response.cuenta.CuentaResponseDto;
import com.arnaira.accounts.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.arnaira.accounts.util.ResponseCode.getMessage;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<CuentaResponseDto> crearCliente(@RequestBody CuentaRequestDto request){
        try {
            CuentaResponseDto response = cuentaService.crearCuenta(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CuentaResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new CuentaDto()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDto>obtenerCliente(@PathVariable Long id){
        try{
            CuentaResponseDto response = cuentaService.obtenerCuentaPorId(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CuentaResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new CuentaDto()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDto> actualizarCliente(@PathVariable Long id,
                                                                @RequestBody CuentaRequestDto request){
        try{
            CuentaResponseDto response = cuentaService.actualizarCuenta(id, request);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CuentaResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new CuentaDto()));
        }
    }
}

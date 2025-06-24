package com.arnaira.accounts.controller;

import com.arnaira.accounts.dto.request.movimiento.MovimientoRequestDto;
import com.arnaira.accounts.dto.response.common.MensajeDto;
import com.arnaira.accounts.dto.response.movimiento.MovimientoDto;
import com.arnaira.accounts.dto.response.movimiento.MovimientoResponseDto;
import com.arnaira.accounts.dto.response.movimiento.ReporteResponseDto;
import com.arnaira.accounts.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.arnaira.accounts.util.ResponseCode.getMessage;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoResponseDto> crearCliente(@RequestBody MovimientoRequestDto request){
        try {
            MovimientoResponseDto response = movimientoService.crearMovimiento(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MovimientoResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new MovimientoDto()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDto>obtenerCliente(@PathVariable Long id){
        try{
            MovimientoResponseDto response = movimientoService.obtenerMovimientoPorId(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MovimientoResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new MovimientoDto()));
        }
    }

    @GetMapping("/reportes")
    public ResponseEntity<ReporteResponseDto> obtenerReporte
            (@RequestParam String numeroCuenta,
            @RequestParam LocalDate fechaInicio,
             @RequestParam LocalDate fechaFin
             ){
        try{

            ReporteResponseDto response = movimientoService.obtenerReporte(numeroCuenta,fechaInicio,fechaFin);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            List<MovimientoDto> lista = new ArrayList<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ReporteResponseDto("99", getMessage("99"), e.getMessage(), lista));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDto> actualizarCliente(@PathVariable Long id,
                                                               @RequestBody MovimientoRequestDto request){
        try{
            MovimientoResponseDto response = movimientoService.actualizarMovimiento(id, request);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MovimientoResponseDto(
                            new MensajeDto("99", getMessage("99"),e.getMessage()),
                            new MovimientoDto()));
        }
    }
}

package com.arnaira.accounts.service;

import com.arnaira.accounts.dto.request.movimiento.MovimientoRequestDto;
import com.arnaira.accounts.dto.response.movimiento.MovimientoResponseDto;
import com.arnaira.accounts.dto.response.movimiento.ReporteResponseDto;

import java.time.LocalDate;

public interface MovimientoService {
    MovimientoResponseDto crearMovimiento(MovimientoRequestDto request);
    MovimientoResponseDto obtenerMovimientoPorId(Long Id);

    ReporteResponseDto obtenerReporte (String numeroCuenta, LocalDate fechaInicio, LocalDate fechaFin);
    MovimientoResponseDto actualizarMovimiento(Long Id, MovimientoRequestDto requestDto);
}

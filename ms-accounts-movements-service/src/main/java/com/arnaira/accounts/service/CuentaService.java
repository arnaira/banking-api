package com.arnaira.accounts.service;

import com.arnaira.accounts.dto.request.cuenta.CuentaRequestDto;
import com.arnaira.accounts.dto.response.cuenta.CuentaResponseDto;

public interface CuentaService {
    CuentaResponseDto crearCuenta(CuentaRequestDto request );
    CuentaResponseDto obtenerCuentaPorId(Long Id);
    CuentaResponseDto actualizarCuenta(Long Id, CuentaRequestDto request);
}

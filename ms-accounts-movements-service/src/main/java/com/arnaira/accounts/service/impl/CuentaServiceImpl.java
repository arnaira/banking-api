package com.arnaira.accounts.service.impl;

import com.arnaira.accounts.dto.request.cuenta.CuentaRequestDto;
import com.arnaira.accounts.dto.response.cuenta.CuentaResponseDto;
import com.arnaira.accounts.mapper.CuentaMapper;
import com.arnaira.accounts.model.Cuenta;
import com.arnaira.accounts.repository.CuentaRepository;
import com.arnaira.accounts.service.CuentaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    public CuentaResponseDto crearCuenta(CuentaRequestDto request) {
        Optional<Cuenta> NumeroCuenta= cuentaRepository.findByNumeroCuenta(request.getNumeroCuenta());
        if (NumeroCuenta.isPresent()){
            return cuentaMapper.toResponse(new Cuenta(),"02");
        }
        Cuenta cuenta = cuentaMapper.toEntity(request);
        Cuenta crearCuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toResponse(crearCuenta,"00");
    }

    @Override
    public CuentaResponseDto obtenerCuentaPorId(Long Id) {
       Optional<Cuenta> cuenta= cuentaRepository.findById(Id);
       if (cuenta.isEmpty()){
           return cuentaMapper.toResponse(new Cuenta(),"01");
       }
       return cuentaMapper.toResponse(cuenta.get(),"00");
    }

    @Override
    public CuentaResponseDto actualizarCuenta(Long id, CuentaRequestDto request) {
        Optional <Cuenta> cuentaExistente = cuentaRepository.findById(id);
        if (cuentaExistente.isEmpty()){
            return cuentaMapper.toResponse(new Cuenta(),"01");
        }

        Cuenta cuenta = cuentaMapper.toEntity(request);

        cuentaExistente.get().setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaExistente.get().setTipoCuenta(cuenta.getTipoCuenta());
        cuentaExistente.get().setSaldo(cuenta.getSaldo());
        cuentaExistente.get().setEstado(cuenta.getEstado());
        Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente.get());

        return cuentaMapper.toResponse(cuentaActualizada,"00");
    }
}

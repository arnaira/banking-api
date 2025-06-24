package com.arnaira.accounts.service.impl;

import com.arnaira.accounts.dto.request.movimiento.MovimientoRequestDto;
import com.arnaira.accounts.dto.response.movimiento.MovimientoResponseDto;
import com.arnaira.accounts.dto.response.movimiento.ReporteResponseDto;
import com.arnaira.accounts.mapper.MovimientoMapper;
import com.arnaira.accounts.model.Cuenta;
import com.arnaira.accounts.model.Movimiento;
import com.arnaira.accounts.repository.CuentaRepository;
import com.arnaira.accounts.repository.MovimientoRepository;
import com.arnaira.accounts.service.MovimientoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,CuentaRepository cuentaRepository,  MovimientoMapper movimientoMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    public MovimientoResponseDto crearMovimiento(MovimientoRequestDto request) {
        Optional<Cuenta> cuenta= cuentaRepository.findByNumeroCuenta(request.getNumeroCuenta());

        if (cuenta.isEmpty()){
            return movimientoMapper.toResponse(new Movimiento(),"01");
        }
        if (cuenta.get().getEstado()==0){
            return movimientoMapper.toResponse(new Movimiento(),"08");
        }
        if (request.getTipoMovimiento().equals("DEB")) {
            BigDecimal saldo = cuenta.get().getSaldo().subtract(request.getMonto());
            if (saldo.compareTo(BigDecimal.ZERO) < 0) {
                return movimientoMapper.toResponse(new Movimiento(), "06");
            }
        }
        if (!request.getTipoMovimiento().trim().equals("DEB")&&!request.getTipoMovimiento().trim().equals("CRE")){
            return movimientoMapper.toResponse(new Movimiento(),"05");
        }

        Movimiento movimiento = movimientoMapper.toEntity(request,cuenta.get());
        cuenta.get().setSaldo(movimiento.getSaldo());
        cuentaRepository.save(cuenta.get());
        Movimiento savedMovimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toResponse(savedMovimiento,"00");
    }

    @Override
    public MovimientoResponseDto obtenerMovimientoPorId(Long Id) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(Id);
        if (movimiento.isEmpty()) {
            return movimientoMapper.toResponse(new Movimiento(),"03");
        }
        return movimientoMapper.toResponse(movimiento.get(),"00");
    }

    @Override
    public ReporteResponseDto obtenerReporte(String numeroCuenta, LocalDate fechaInicio, LocalDate fechaFin) {


        List<Movimiento> movimientos =movimientoRepository.findByNumeroCuentaAndFechaBetween(numeroCuenta,fechaInicio,fechaFin);

        if (movimientos.isEmpty()){
            return movimientoMapper.toResponse(movimientos,"07");
        }
        return movimientoMapper.toResponse(movimientos,"00");
    }

    @Override
    public MovimientoResponseDto actualizarMovimiento(Long Id, MovimientoRequestDto request) {
        Optional<Cuenta> cuenta= cuentaRepository.findByNumeroCuenta(request.getNumeroCuenta());
        if (cuenta.isEmpty()){
            return movimientoMapper.toResponse(new Movimiento(),"01");
        }
        Optional<Movimiento> movimientoExistente = movimientoRepository.findById(Id);
        if (movimientoExistente.isEmpty()) {
            return movimientoMapper.toResponse(new Movimiento(),"03");
        }
        Movimiento movimiento = movimientoMapper.toEntity(request,cuenta.get());
        cuenta.get().setSaldo(movimiento.getSaldo());
        cuentaRepository.save(cuenta.get());
        movimientoExistente.get().setNumeroCuenta(movimiento.getNumeroCuenta());
        movimientoExistente.get().setFecha(movimiento.getFecha());
        movimientoExistente.get().setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoExistente.get().setMonto(movimiento.getMonto());
        movimientoExistente.get().setSaldo(movimiento.getSaldo());
        Movimiento movimientoActualizado = movimientoRepository.save(movimientoExistente.get());
        return movimientoMapper.toResponse(movimientoActualizado,"00");
    }
}

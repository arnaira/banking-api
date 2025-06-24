package com.arnaira.accounts.mapper;

import com.arnaira.accounts.dto.request.movimiento.MovimientoRequestDto;
import com.arnaira.accounts.dto.response.common.MensajeDto;
import com.arnaira.accounts.dto.response.movimiento.MovimientoDto;
import com.arnaira.accounts.dto.response.movimiento.MovimientoResponseDto;
import com.arnaira.accounts.dto.response.movimiento.ReporteResponseDto;
import com.arnaira.accounts.model.Cuenta;
import com.arnaira.accounts.model.Movimiento;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.arnaira.accounts.util.ResponseCode.getMessage;

@Configuration
public class MovimientoMapper {

    public Movimiento toEntity(MovimientoRequestDto request, Cuenta cuenta){
        BigDecimal saldo = new BigDecimal(0);
        BigDecimal monto = new BigDecimal(0);
        if (request.getTipoMovimiento().equals("DEB")){
            saldo= cuenta.getSaldo().subtract(request.getMonto());
            monto= request.getMonto().negate();
        } else if (request.getTipoMovimiento().equals("CRE")){
            saldo=  cuenta.getSaldo().add(request.getMonto());
            monto= request.getMonto();
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setCuentaId(cuenta);
        movimiento.setNumeroCuenta(request.getNumeroCuenta());
        movimiento.setFecha(LocalDate.now());
        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        movimiento.setMonto(monto);
        movimiento.setSaldo(saldo);
    return  movimiento;
    }

    public MovimientoResponseDto toResponse(Movimiento movimiento, String code){
        MovimientoResponseDto response = new MovimientoResponseDto();
        MovimientoDto movimientoDto = new MovimientoDto();
        MensajeDto msj = new MensajeDto();

        msj.setCodMensaje(code);
        msj.setMensaje(getMessage(code));
        msj.setDetalleError("");

        movimientoDto.setCuenta(movimiento.getNumeroCuenta());
        movimientoDto.setFecha(movimiento.getFecha());
        movimientoDto.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDto.setMonto(movimiento.getMonto());
        movimientoDto.setSaldo(movimiento.getSaldo());

        response.setMensaje(msj);
        response.setMovimiento(movimientoDto);

        return response;

    }

    public ReporteResponseDto toResponse(List<Movimiento> movimientos, String code){
        ReporteResponseDto response = new ReporteResponseDto();

        List<MovimientoDto> listMovimiento = new ArrayList<>();
        MensajeDto msj = new MensajeDto();

        response.setCodMensaje(code);
        response.setMensaje(getMessage(code));
        response.setDetalleError("");

    for (Movimiento movimiento:movimientos) {
        MovimientoDto movimientoDto = new MovimientoDto();
        movimientoDto.setCuenta(movimiento.getNumeroCuenta());
        movimientoDto.setFecha(movimiento.getFecha());
        movimientoDto.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDto.setMonto(movimiento.getMonto());
        movimientoDto.setSaldo(movimiento.getSaldo());
        listMovimiento.add(movimientoDto);
    }
        response.setMovimiento(listMovimiento);



        return response;

    }



}

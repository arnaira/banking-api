package com.arnaira.accounts.mapper;

import com.arnaira.accounts.dto.request.cuenta.CuentaRequestDto;
import com.arnaira.accounts.dto.response.common.MensajeDto;
import com.arnaira.accounts.dto.response.cuenta.CuentaDto;
import com.arnaira.accounts.dto.response.cuenta.CuentaResponseDto;
import com.arnaira.accounts.model.Cuenta;
import org.springframework.context.annotation.Configuration;

import static com.arnaira.accounts.util.ResponseCode.getMessage;

@Configuration
public class CuentaMapper {
    public Cuenta toEntity(CuentaRequestDto request){

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setEstado(request.getEstado());
        cuenta.setClienteId(request.getClienteId());

        return cuenta;

    }


    public CuentaResponseDto toResponse(Cuenta cuenta, String code){
        CuentaResponseDto response = new CuentaResponseDto();
        CuentaDto cuentaDto = new CuentaDto();
        MensajeDto msj = new MensajeDto();

        msj.setCodMensaje(code);
        msj.setMensaje(getMessage(code));
        msj.setDetalleError("");

        cuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDto.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDto.setSaldo(cuenta.getSaldo());
        cuentaDto.setEstado(cuenta.getEstado());
        cuentaDto.setClienteId(cuenta.getClienteId());

        response.setMensaje(msj);
        response.setCuenta(cuentaDto);

        return response;

    }
}

package com.arnaira.accounts.dto.request.movimiento;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimientoRequestDto {


    private String numeroCuenta;
    private String tipoMovimiento;
    private BigDecimal monto;


    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }


}

package com.arnaira.accounts.util;

public enum ResponseCode {
    SUCCESS("00","Procesamiento Exitoso"),
    CUENTA_NOT_FOUND("01","Cuenta no encontrada"),
    CUENTA_ALREADY_EXISTS("02","La cuenta que intenta crear ya existe"),
    MOVIMIENTO_NOT_FOUND_ERROR("03","Movimiento no encontrado"),

    CUENTA_MOVIMIENTO_NOT_MATCH("04","Cuenta no tiene ese moviento"),

    INVALID_MOVEMENT_TYPE("05","Tipo de movimiento invalido"),

    NOT_ENOUGH_BALANCE("06","Saldo No Disponible para realizar este movimiento"),
    NO_MOVEMENTS("07","Cuenta no tiene movimientos, estado de cuenta vacio"),

    ACC_DOWN("08","Cuenta se encuentra inactiva, no se realizo nuevo movimiento"),

    ERROR("99","Error interno del servidor");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        for (ResponseCode rc:values()){
            if (rc.code.equals(code)){
                return rc.message;
            }
        }
        return "Mensaje de error no parametrizado";
    }
}

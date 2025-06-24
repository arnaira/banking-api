package com.arnaira.clients.util;

public enum ResponseCode {
    SUCCESS("00","Procesamiento Exitoso"),
    PERSON_NOT_FOUND("01","Persona no encontrada"),
    CLIENT_ALREADY_EXISTS("02","El cliente que intenta crear ya existe"),
    CLIENT_NOT_FOUND_ERROR("03","Cliente no encontrado"),

    CLIENT_PERSON_NOT_MATCH("04","Cliente no esta registrado con esa persona"),
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

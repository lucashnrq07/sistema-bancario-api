package com.lucas.sistema_bancario_api.exceptions;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String message) {
        super(message);
    }
}

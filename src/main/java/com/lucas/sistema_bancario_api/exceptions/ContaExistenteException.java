package com.lucas.sistema_bancario_api.exceptions;

public class ContaExistenteException extends RuntimeException {
    public ContaExistenteException(String message) {
        super(message);
    }
}

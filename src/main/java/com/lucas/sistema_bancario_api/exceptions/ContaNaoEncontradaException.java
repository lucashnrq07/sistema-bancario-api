package com.lucas.sistema_bancario_api.exceptions;

public class ContaNaoEncontradaException extends RuntimeException {
    public ContaNaoEncontradaException(String message) {
        super(message);
    }
}

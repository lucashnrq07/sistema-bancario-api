package com.lucas.sistema_bancario_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.sistema_bancario_api.entities.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaRespostaDTO(
        String de,
        String para,
        BigDecimal valor,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
        LocalDateTime momento){
}

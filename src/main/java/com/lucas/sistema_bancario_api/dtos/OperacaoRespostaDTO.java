package com.lucas.sistema_bancario_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OperacaoRespostaDTO(
        String cpf,
        BigDecimal valor,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
        LocalDateTime moment) {
}
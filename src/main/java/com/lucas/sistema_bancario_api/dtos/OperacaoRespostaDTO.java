package com.lucas.sistema_bancario_api.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OperacaoRespostaDTO(String cpf, BigDecimal valor, LocalDateTime moment) {
}
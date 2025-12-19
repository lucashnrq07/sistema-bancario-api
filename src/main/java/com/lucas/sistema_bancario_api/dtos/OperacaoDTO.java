package com.lucas.sistema_bancario_api.dtos;

import java.math.BigDecimal;

public record OperacaoDTO(String cpf, BigDecimal valor) {
}

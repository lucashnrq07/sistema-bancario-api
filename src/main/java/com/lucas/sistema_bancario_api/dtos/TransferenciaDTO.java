package com.lucas.sistema_bancario_api.dtos;

import java.math.BigDecimal;

public record TransferenciaDTO(String cpf1, String cpf2, BigDecimal valor) {
}

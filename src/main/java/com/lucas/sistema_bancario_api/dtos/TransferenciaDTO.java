package com.lucas.sistema_bancario_api.dtos;

import com.lucas.sistema_bancario_api.entities.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaDTO (Conta de, Conta para, BigDecimal valor, LocalDateTime momento){
}

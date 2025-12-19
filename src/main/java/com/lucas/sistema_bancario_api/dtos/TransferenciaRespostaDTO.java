package com.lucas.sistema_bancario_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.sistema_bancario_api.entities.Conta;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Resposta de uma transferência")
public record TransferenciaRespostaDTO(
        @Schema(description = "CPF que enviou o valor", example = "000000000-01")
        String de,

        @Schema(description = "CPF que recebeu o valor", example = "000000000-02")
        String para,

        @Schema(description = "Valor da transferência", example = "30.00")
        BigDecimal valor,

        @Schema(description = "Momento em que foi realizada a transferência")
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
        LocalDateTime momento
){}

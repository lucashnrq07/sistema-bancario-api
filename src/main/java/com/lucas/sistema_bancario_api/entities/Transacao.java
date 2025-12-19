package com.lucas.sistema_bancario_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quem_enviou_id", nullable = false)
    @NotNull
    private Conta quemEnviou;

    @ManyToOne
    @JoinColumn(name = "quem_recebeu_id", nullable = false)
    @NotNull
    private Conta quemRecebeu;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime momento;
}

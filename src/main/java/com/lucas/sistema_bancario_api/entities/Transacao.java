package com.lucas.sistema_bancario_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

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
    @JoinColumn(name = "quem_enviou_id")
    private Conta quemEnviou;

    @ManyToOne
    @JoinColumn(name = "quem_recebeu_id")
    private Conta quemRecebeu;

    @NotNull
    private Instant instante;
}

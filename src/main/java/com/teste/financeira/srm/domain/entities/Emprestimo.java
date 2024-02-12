package com.teste.financeira.srm.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorEmprestimo;
    private Integer numeroParcelas;
    private String statusPagamento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}

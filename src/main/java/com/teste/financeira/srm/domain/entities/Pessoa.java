package com.teste.financeira.srm.domain.entities;

import com.teste.financeira.srm.domain.enums.TipoIdentificador;
import jakarta.persistence.*;

import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String identificador;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private TipoIdentificador tipoIdentificador;

    private Double valorMinimoParcela;
    private Double valorMaximoEmprestimo;
}

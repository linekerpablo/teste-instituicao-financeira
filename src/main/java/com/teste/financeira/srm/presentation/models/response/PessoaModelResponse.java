package com.teste.financeira.srm.presentation.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PessoaModelResponse {

    private Long id;
    private String nome;
    private String identificador;
    private Date dataNascimento;
    private String tipoIdentificador;
    private Double valorMinimoParcela;
    private Double valorMaximoEmprestimo;
}

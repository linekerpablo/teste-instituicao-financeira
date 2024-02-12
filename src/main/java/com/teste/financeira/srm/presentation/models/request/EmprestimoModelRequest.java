package com.teste.financeira.srm.presentation.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmprestimoModelRequest {

    private Double valorEmprestimo;
    private Integer numeroParcelas;
    private String identificadorPessoa;
}

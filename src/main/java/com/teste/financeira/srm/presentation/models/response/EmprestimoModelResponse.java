package com.teste.financeira.srm.presentation.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EmprestimoModelResponse {

    private Long id;
    private Double valorEmprestimo;
    private Integer numeroParcelas;
    private String statusPagamento;
    private Date dataCriacao;
}
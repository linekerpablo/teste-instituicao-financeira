package com.teste.financeira.srm.presentation.translators.response;

import com.teste.financeira.srm.domain.entities.Emprestimo;
import com.teste.financeira.srm.presentation.models.response.EmprestimoModelResponse;

public class EmprestimoToEmprestimoResponseTranslator {

    public static EmprestimoModelResponse translate(Emprestimo emprestimo) {
        return EmprestimoModelResponse.builder()
                .id(emprestimo.getId())
                .valorEmprestimo(emprestimo.getValorEmprestimo())
                .numeroParcelas(emprestimo.getNumeroParcelas())
                .statusPagamento(emprestimo.getStatusPagamento())
                .dataCriacao(emprestimo.getDataCriacao())
                .build();
    }
}
package com.teste.financeira.srm.presentation.translators.request;

import com.teste.financeira.srm.domain.entities.Emprestimo;
import com.teste.financeira.srm.presentation.models.request.EmprestimoModelRequest;

public class EmprestimoRequestToEmprestimoTranslator {

    public static Emprestimo translate(EmprestimoModelRequest request) {
        return Emprestimo.builder()
                .valorEmprestimo(request.getValorEmprestimo())
                .numeroParcelas(request.getNumeroParcelas())
                .build();
    }
}

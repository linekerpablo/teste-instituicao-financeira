package com.teste.financeira.srm.domain.gateways;

import com.teste.financeira.srm.domain.entities.Emprestimo;

public interface EmprestimoGateway {
    void save(Emprestimo emprestimo);
    void atualizarStatus(Long emprestimoId, String statusPagamento);
    Double somaEmprestimosPorPessoa(Long pessoaId);
}

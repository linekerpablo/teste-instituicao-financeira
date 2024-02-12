package com.teste.financeira.srm.domain.usecases.emprestimo.interfaces;

public interface AtualizarStatusEmprestimoUseCase {
    void executar(Long emprestimoId, String statusPagamento);
}

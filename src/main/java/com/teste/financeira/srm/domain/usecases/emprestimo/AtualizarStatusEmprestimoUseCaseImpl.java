package com.teste.financeira.srm.domain.usecases.emprestimo;

import com.teste.financeira.srm.domain.gateways.EmprestimoGateway;
import com.teste.financeira.srm.domain.usecases.emprestimo.interfaces.AtualizarStatusEmprestimoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusEmprestimoUseCaseImpl implements AtualizarStatusEmprestimoUseCase {

    @Autowired
    private EmprestimoGateway emprestimoGateway;

    @Override
    public void executar(Long emprestimoId, String statusPagamento) {
        emprestimoGateway.atualizarStatus(emprestimoId, statusPagamento);
    }
}
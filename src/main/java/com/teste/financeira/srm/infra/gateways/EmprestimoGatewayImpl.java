package com.teste.financeira.srm.infra.gateways;

import com.teste.financeira.srm.domain.entities.Emprestimo;
import com.teste.financeira.srm.domain.gateways.EmprestimoGateway;
import com.teste.financeira.srm.infra.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoGatewayImpl implements EmprestimoGateway {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public void save(Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
    }

    @Override
    public void atualizarStatus(Long emprestimoId, String statusPagamento) {
        emprestimoRepository.findById(emprestimoId).ifPresent(emprestimo -> {
            emprestimo.setStatusPagamento(statusPagamento);
            emprestimoRepository.save(emprestimo);
        });
    }

    @Override
    public Double somaEmprestimosPorPessoa(Long pessoaId) {
        return emprestimoRepository.somaEmprestimosPorPessoa(pessoaId);
    }
}

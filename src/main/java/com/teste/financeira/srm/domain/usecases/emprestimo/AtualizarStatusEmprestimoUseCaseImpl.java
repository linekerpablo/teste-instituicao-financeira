package com.teste.financeira.srm.domain.usecases.emprestimo;

import com.teste.financeira.srm.domain.usecases.emprestimo.interfaces.AtualizarStatusEmprestimoUseCase;
import com.teste.financeira.srm.infra.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusEmprestimoUseCaseImpl implements AtualizarStatusEmprestimoUseCase {

    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public AtualizarStatusEmprestimoUseCaseImpl(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    @Override
    public void executar(Long emprestimoId, String statusPagamento) {
        emprestimoRepository.findById(emprestimoId).ifPresent(emprestimo -> {
            emprestimo.setStatusPagamento(statusPagamento);
            emprestimoRepository.save(emprestimo);
        });
    }
}
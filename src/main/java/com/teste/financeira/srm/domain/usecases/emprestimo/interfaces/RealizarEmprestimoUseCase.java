package com.teste.financeira.srm.domain.usecases.emprestimo.interfaces;

import com.teste.financeira.srm.domain.entities.Emprestimo;

public interface RealizarEmprestimoUseCase {
    Emprestimo executar(Emprestimo emprestimo, String identificadorPessoa);
}

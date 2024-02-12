package com.teste.financeira.srm.domain.usecases.pessoa.interfaces;

import com.teste.financeira.srm.domain.entities.Pessoa;

public interface CadastrarPessoaUseCase {
    Pessoa executar(Pessoa pessoa);
}

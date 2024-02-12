package com.teste.financeira.srm.domain.gateways;

import com.teste.financeira.srm.domain.entities.Pessoa;

public interface PessoaGateway {
    Pessoa save(Pessoa pessoa);
    Pessoa findByIdentificador(String identificador);
    boolean existsByIdentificador(String identificador);
}

package com.teste.financeira.srm.domain.services.interfaces;

import com.teste.financeira.srm.domain.entities.Pessoa;

public interface ValidacaoPessoaService {
    void validarIdentificador(Pessoa pessoa);
}

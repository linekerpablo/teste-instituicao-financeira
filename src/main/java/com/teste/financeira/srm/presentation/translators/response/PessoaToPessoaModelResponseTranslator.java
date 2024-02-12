package com.teste.financeira.srm.presentation.translators.response;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.presentation.models.response.PessoaModelResponse;

public class PessoaToPessoaModelResponseTranslator {

    public static PessoaModelResponse translate(Pessoa pessoa) {
        return PessoaModelResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .identificador(pessoa.getIdentificador())
                .dataNascimento(pessoa.getDataNascimento())
                .tipoIdentificador(pessoa.getIdentificador())
                .valorMaximoEmprestimo(pessoa.getValorMaximoEmprestimo())
                .valorMinimoParcela(pessoa.getValorMinimoParcela())
                .build();
    }
}

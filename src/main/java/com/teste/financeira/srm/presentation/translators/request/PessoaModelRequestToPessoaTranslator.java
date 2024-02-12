package com.teste.financeira.srm.presentation.translators.request;


import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.presentation.models.request.PessoaModelRequest;

public class PessoaModelRequestToPessoaTranslator {

    public static Pessoa translate(PessoaModelRequest modelRequest) {
        return Pessoa.builder()
                .nome(modelRequest.getNome())
                .identificador(modelRequest.getIdentificador())
                .dataNascimento(modelRequest.getDataNascimento())
                .build();
    }
}

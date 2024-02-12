package com.teste.financeira.srm.domain.services;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.services.interfaces.ValidacaoPessoaService;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoPessoaServiceImpl implements ValidacaoPessoaService {

    public void validarIdentificador(Pessoa pessoa) {
        switch (pessoa.getTipoIdentificador()) {
            case PESSOA_FISICA:
                validarCPF(pessoa.getIdentificador());
                break;
            case PESSOA_JURIDICA:
                validarCNPJ(pessoa.getIdentificador());
                break;
            case ESTUDANTE_UNIVERSITARIO:
                validarEstudante(pessoa.getIdentificador());
                break;
            case APOSENTADO:
                validarAposentado(pessoa.getIdentificador());
                break;
            default:
                throw new DomainException("Tipo de identificador desconhecido.");
        }
    }

    private void validarCPF(String cpf) {
        if (!DocumentosHelper.isCPFValido(cpf)) {
            throw new DomainException("CPF inválido.");
        }
    }

    private void validarCNPJ(String cnpj) {
        if (!DocumentosHelper.isCNPJValido(cnpj)) {
            throw new DomainException("CNPJ inválido.");
        }
    }

    private void validarEstudante(String identificador) {
        if (!DocumentosHelper.isEstudante(identificador)) {
            throw new DomainException("Identificador de estudante inválido.");
        }
    }

    private void validarAposentado(String identificador) {
        if (!DocumentosHelper.isAposentado(identificador)) {
            throw new DomainException("Identificador de aposentado inválido.");
        }
    }
}
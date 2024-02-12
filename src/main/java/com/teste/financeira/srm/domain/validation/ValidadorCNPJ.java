package com.teste.financeira.srm.domain.validation;

import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.validation.interfaces.ValidadorIdentificador;

import java.util.Optional;

public class ValidadorCNPJ implements ValidadorIdentificador {
    @Override
    public void validar(String cnpj) {
        Optional.ofNullable(cnpj)
                .filter(DocumentosHelper::isCNPJValido)
                .orElseThrow(() -> new DomainException("CNPJ inválido."));
    }
}

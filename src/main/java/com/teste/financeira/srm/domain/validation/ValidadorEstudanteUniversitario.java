package com.teste.financeira.srm.domain.validation;

import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.validation.interfaces.ValidadorIdentificador;

import java.util.Optional;

public class ValidadorEstudanteUniversitario implements ValidadorIdentificador {
    @Override
    public void validar(String identificador) {
        Optional.ofNullable(identificador)
                .filter(DocumentosHelper::isEstudante)
                .orElseThrow(() -> new DomainException("Identificador de estudante inválido."));
    }
}

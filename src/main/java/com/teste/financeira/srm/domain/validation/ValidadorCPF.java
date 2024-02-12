package com.teste.financeira.srm.domain.validation;

import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.validation.interfaces.ValidadorIdentificador;

import java.util.Optional;

public class ValidadorCPF implements ValidadorIdentificador {
    @Override
    public void validar(String cpf) {
        Optional.ofNullable(cpf)
                .filter(DocumentosHelper::isCPFValido)
                .orElseThrow(() -> new DomainException("CPF inválido."));
    }
}

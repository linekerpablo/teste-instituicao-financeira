package com.teste.financeira.srm.domain.services;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.enums.TipoIdentificador;
import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.services.interfaces.ValidacaoPessoaService;
import com.teste.financeira.srm.domain.validation.ValidadorAposentado;
import com.teste.financeira.srm.domain.validation.ValidadorCNPJ;
import com.teste.financeira.srm.domain.validation.ValidadorCPF;
import com.teste.financeira.srm.domain.validation.ValidadorEstudanteUniversitario;
import com.teste.financeira.srm.domain.validation.interfaces.ValidadorIdentificador;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ValidacaoPessoaServiceImpl implements ValidacaoPessoaService {

    private final Map<TipoIdentificador, ValidadorIdentificador> estrategias;

    public ValidacaoPessoaServiceImpl() {
        estrategias = new HashMap<>();
        estrategias.put(TipoIdentificador.PESSOA_FISICA, new ValidadorCPF());
        estrategias.put(TipoIdentificador.PESSOA_JURIDICA, new ValidadorCNPJ());
        estrategias.put(TipoIdentificador.ESTUDANTE_UNIVERSITARIO, new ValidadorEstudanteUniversitario());
        estrategias.put(TipoIdentificador.APOSENTADO, new ValidadorAposentado());
    }

    @Override
    public void validarIdentificador(Pessoa pessoa) {
        Optional.ofNullable(estrategias.get(pessoa.getTipoIdentificador()))
                .orElseThrow(() -> new DomainException("Tipo de identificador desconhecido."))
                .validar(pessoa.getIdentificador());
    }
}
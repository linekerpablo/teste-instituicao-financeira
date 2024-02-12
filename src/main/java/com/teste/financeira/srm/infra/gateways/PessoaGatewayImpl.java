package com.teste.financeira.srm.infra.gateways;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.gateways.PessoaGateway;
import com.teste.financeira.srm.infra.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaGatewayImpl implements PessoaGateway {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findByIdentificador(String identificador) {
        return pessoaRepository.findByIdentificador(identificador)
                .orElseThrow(() -> new DomainException("Identificador inválido ou não encontrado."));
    }

    @Override
    public boolean existsByIdentificador(String identificador) {
        return pessoaRepository.existsByIdentificador(identificador);
    }
}

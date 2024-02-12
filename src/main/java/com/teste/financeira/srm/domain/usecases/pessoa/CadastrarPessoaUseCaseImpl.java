package com.teste.financeira.srm.domain.usecases.pessoa;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.enums.TipoIdentificador;
import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.gateways.PessoaGateway;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.services.interfaces.ValidacaoPessoaService;
import com.teste.financeira.srm.domain.usecases.pessoa.interfaces.CadastrarPessoaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPessoaUseCaseImpl implements CadastrarPessoaUseCase {

    @Autowired
    private PessoaGateway pessoaGateway;

    @Autowired
    private ValidacaoPessoaService validacaoPessoaService;

    public Pessoa executar(Pessoa pessoa) throws DomainException {
        if (pessoaGateway.existsByIdentificador(pessoa.getIdentificador())) {
            throw new DomainException("Identificador já existe.");
        }

        definirTipoIdentificadorEValores(pessoa);
        validacaoPessoaService.validarIdentificador(pessoa);

        return pessoaGateway.save(pessoa);
    }

    private void definirTipoIdentificadorEValores(Pessoa pessoa) {
        String identificador = pessoa.getIdentificador();
        int tamanho = identificador.length();

        switch (tamanho) {
            case 11:
                pessoa.setTipoIdentificador(TipoIdentificador.PESSOA_FISICA);
                pessoa.setValorMinimoParcela(300.00);
                pessoa.setValorMaximoEmprestimo(10000.00);
                break;
            case 14:
                pessoa.setTipoIdentificador(TipoIdentificador.PESSOA_JURIDICA);
                pessoa.setValorMinimoParcela(1000.00);
                pessoa.setValorMaximoEmprestimo(100000.00);
                break;
            case 8:
                pessoa.setTipoIdentificador(TipoIdentificador.ESTUDANTE_UNIVERSITARIO);
                pessoa.setValorMinimoParcela(100.00);
                pessoa.setValorMaximoEmprestimo(10000.00);
                break;
            case 10:
                pessoa.setTipoIdentificador(TipoIdentificador.APOSENTADO);
                pessoa.setValorMinimoParcela(400.00);
                pessoa.setValorMaximoEmprestimo(25000.00);
                break;
            default:
                throw new DomainException("Identificador inválido.");
        }
    }
}
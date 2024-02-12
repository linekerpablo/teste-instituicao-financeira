package com.teste.financeira.srm.domain.usecases.pessoa;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.enums.TipoIdentificador;
import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.gateways.PessoaGateway;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.usecases.pessoa.interfaces.CadastrarPessoaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPessoaUseCaseImpl implements CadastrarPessoaUseCase {

    @Autowired
    private PessoaGateway pessoaGateway;

    public Pessoa executar(Pessoa pessoa) throws DomainException {
        definirTipoIdentificadorEValores(pessoa);
        validarPessoa(pessoa);
        return pessoaGateway.save(pessoa);
    }

    private void definirTipoIdentificadorEValores(Pessoa pessoa) {
        String identificador = pessoa.getIdentificador();
        int tamanho = identificador.length();

        switch (tamanho) {
            case 11:
                pessoa.setTipoIdentificador(TipoIdentificador.PF);
                pessoa.setValorMinimoParcela(300.00);
                pessoa.setValorMaximoEmprestimo(10000.00);
                break;
            case 14:
                pessoa.setTipoIdentificador(TipoIdentificador.PJ);
                pessoa.setValorMinimoParcela(1000.00);
                pessoa.setValorMaximoEmprestimo(100000.00);
                break;
            case 8:
                pessoa.setTipoIdentificador(TipoIdentificador.EU);
                pessoa.setValorMinimoParcela(100.00);
                pessoa.setValorMaximoEmprestimo(10000.00);
                break;
            case 10:
                pessoa.setTipoIdentificador(TipoIdentificador.AP);
                pessoa.setValorMinimoParcela(400.00);
                pessoa.setValorMaximoEmprestimo(25000.00);
                break;
            default:
                throw new DomainException("Identificador inválido.");
        }
    }

    private void validarPessoa(Pessoa pessoa) {
        if (pessoaGateway.existsByIdentificador(pessoa.getIdentificador())) {
            throw new DomainException("Identificador já existe.");
        }

        switch (pessoa.getTipoIdentificador()) {
            case PF:
                if (!DocumentosHelper.isCPFValido(pessoa.getIdentificador())) {
                    throw new DomainException("CPF inválido.");
                }
                break;
            case PJ:
                if (!DocumentosHelper.isCNPJValido(pessoa.getIdentificador())) {
                    throw new DomainException("CNPJ inválido.");
                }
                break;
            case EU:
                if (!DocumentosHelper.isEstudante(pessoa.getIdentificador())) {
                    throw new DomainException("CNPJ inválido.");
                }
                break;
            case AP:
                if (!DocumentosHelper.isAposentado(pessoa.getIdentificador())) {
                    throw new DomainException("CNPJ inválido.");
                }
                break;
            default:
                throw new DomainException("Tipo de identificador desconhecido.");
        }
    }
}
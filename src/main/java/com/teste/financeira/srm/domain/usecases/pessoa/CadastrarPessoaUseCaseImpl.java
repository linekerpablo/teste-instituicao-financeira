package com.teste.financeira.srm.domain.usecases.pessoa;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.enums.TipoIdentificador;
import com.teste.financeira.srm.domain.exceptions.CustomException;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.usecases.pessoa.interfaces.CadastrarPessoaUseCase;
import com.teste.financeira.srm.infra.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPessoaUseCaseImpl implements CadastrarPessoaUseCase {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa executar(Pessoa pessoa) throws CustomException {
        definirTipoIdentificadorEValores(pessoa);
        validarPessoa(pessoa);
        return pessoaRepository.save(pessoa);
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
                throw new CustomException("Identificador inválido.");
        }
    }

    private void validarPessoa(Pessoa pessoa) {
        if (pessoaRepository.existsByIdentificador(pessoa.getIdentificador())) {
            throw new CustomException("Identificador já existe.");
        }

        switch (pessoa.getTipoIdentificador()) {
            case PF:
                if (!DocumentosHelper.isCPFValido(pessoa.getIdentificador())) {
                    throw new CustomException("CPF inválido.");
                }
                break;
            case PJ:
                if (!DocumentosHelper.isCNPJValido(pessoa.getIdentificador())) {
                    throw new CustomException("CNPJ inválido.");
                }
                break;
            case EU:
                if (!DocumentosHelper.isEstudante(pessoa.getIdentificador())) {
                    throw new CustomException("CNPJ inválido.");
                }
                break;
            case AP:
                if (!DocumentosHelper.isAposentado(pessoa.getIdentificador())) {
                    throw new CustomException("CNPJ inválido.");
                }
                break;
            default:
                throw new CustomException("Tipo de identificador desconhecido.");
        }
    }
}
package com.teste.financeira.srm.domain.usecases.emprestimo;

import com.teste.financeira.srm.domain.entities.Emprestimo;
import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.exceptions.DomainException;
import com.teste.financeira.srm.domain.gateways.EmprestimoGateway;
import com.teste.financeira.srm.domain.gateways.PessoaGateway;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.usecases.emprestimo.interfaces.RealizarEmprestimoUseCase;
import com.teste.financeira.srm.infra.messaging.interfaces.EnviarEmprestimoFilaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RealizarEmprestimoUseCaseImpl implements RealizarEmprestimoUseCase {

    @Autowired
    private EmprestimoGateway emprestimoGateway;

    @Autowired
    private PessoaGateway pessoaGateway;

    @Autowired
    private EnviarEmprestimoFilaPagamento enviarEmprestimoFilaPagamento;

    public Emprestimo executar(Emprestimo emprestimo, String identificadorPessoa) {
        Pessoa pessoa = pessoaGateway.findByIdentificador(identificadorPessoa);

        validarIdentificador(pessoa);
        validarEmprestimo(emprestimo, pessoa);

        emprestimo.setPessoa(pessoa);
        emprestimo.setDataCriacao(new Date());

        emprestimoGateway.save(emprestimo);
        enviarEmprestimoFilaPagamento.executar(emprestimo.getId());

        return emprestimo;
    }

    private void validarIdentificador(Pessoa pessoa) {
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

    private void validarEmprestimo(Emprestimo emprestimo, Pessoa pessoa) {
        Double somaEmprestimosExistente = emprestimoGateway.somaEmprestimosPorPessoa(pessoa.getId());
        if (somaEmprestimosExistente == null) {
            somaEmprestimosExistente = 0.0;
        }

        double somaTotal = somaEmprestimosExistente + emprestimo.getValorEmprestimo();
        if (somaTotal > pessoa.getValorMaximoEmprestimo()) {
            throw new DomainException("A soma dos empréstimos excede o limite máximo permitido.");
        }

        if (emprestimo.getValorEmprestimo() > pessoa.getValorMaximoEmprestimo()) {
            throw new DomainException("O valor do empréstimo excede o limite máximo permitido.");
        }

        if (emprestimo.getNumeroParcelas() > 24) {
            throw new DomainException("O número de parcelas excede o limite permitido de 24.");
        }

        if (emprestimo.getValorEmprestimo() / emprestimo.getNumeroParcelas() < pessoa.getValorMinimoParcela()) {
            throw new DomainException("O valor das parcelas é inferior ao mínimo permitido.");
        }
    }
}

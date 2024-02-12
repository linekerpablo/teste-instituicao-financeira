package com.teste.financeira.srm.domain.usecases.emprestimo;

import com.teste.financeira.srm.domain.entities.Emprestimo;
import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.exceptions.CustomException;
import com.teste.financeira.srm.domain.helpers.DocumentosHelper;
import com.teste.financeira.srm.domain.usecases.emprestimo.interfaces.RealizarEmprestimoUseCase;
import com.teste.financeira.srm.infra.messaging.interfaces.EnviarEmprestimoFilaPagamento;
import com.teste.financeira.srm.infra.repositories.EmprestimoRepository;
import com.teste.financeira.srm.infra.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealizarEmprestimoUseCaseImpl implements RealizarEmprestimoUseCase {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnviarEmprestimoFilaPagamento enviarEmprestimoFilaPagamento;

    public Emprestimo executar(Emprestimo emprestimo, String identificadorPessoa) {
        Pessoa pessoa = pessoaRepository.findByIdentificador(identificadorPessoa)
                .orElseThrow(() -> new CustomException("Identificador inválido ou não encontrado."));

        validarIdentificador(pessoa);
        validarEmprestimo(emprestimo, pessoa);

        emprestimo.setPessoa(pessoa);

        emprestimoRepository.save(emprestimo);
        enviarEmprestimoFilaPagamento.executar(emprestimo.getId());

        return emprestimo;
    }

    private void validarIdentificador(Pessoa pessoa) {
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

    private void validarEmprestimo(Emprestimo emprestimo, Pessoa pessoa) {
        Double somaEmprestimosExistente = emprestimoRepository.somaEmprestimosPorPessoa(pessoa.getId());
        if (somaEmprestimosExistente == null) {
            somaEmprestimosExistente = 0.0;
        }

        double somaTotal = somaEmprestimosExistente + emprestimo.getValorEmprestimo();
        if (somaTotal > pessoa.getValorMaximoEmprestimo()) {
            throw new CustomException("A soma dos empréstimos excede o limite máximo permitido.");
        }

        if (emprestimo.getValorEmprestimo() > pessoa.getValorMaximoEmprestimo()) {
            throw new CustomException("O valor do empréstimo excede o limite máximo permitido.");
        }

        if (emprestimo.getNumeroParcelas() > 24) {
            throw new CustomException("O número de parcelas excede o limite permitido de 24.");
        }

        if (emprestimo.getValorEmprestimo() / emprestimo.getNumeroParcelas() < pessoa.getValorMinimoParcela()) {
            throw new CustomException("O valor das parcelas é inferior ao mínimo permitido.");
        }
    }
}

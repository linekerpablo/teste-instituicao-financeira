package com.teste.financeira.srm.presentation.restcontrollers;

import com.teste.financeira.srm.domain.entities.Pessoa;
import com.teste.financeira.srm.domain.usecases.pessoa.interfaces.CadastrarPessoaUseCase;
import com.teste.financeira.srm.presentation.models.request.PessoaModelRequest;
import com.teste.financeira.srm.presentation.models.response.PessoaModelResponse;
import com.teste.financeira.srm.presentation.translators.request.PessoaModelRequestToPessoaTranslator;
import com.teste.financeira.srm.presentation.translators.response.PessoaToPessoaModelResponseTranslator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaRestController {

    @Autowired
    private CadastrarPessoaUseCase cadastrarPessoaUseCase;

    @PostMapping
    public ResponseEntity<PessoaModelResponse> cadastrarPessoa(@Valid @RequestBody PessoaModelRequest modelRequest) {
        Pessoa pessoa = PessoaModelRequestToPessoaTranslator.translate(modelRequest);
        Pessoa pessoaCadastrada = cadastrarPessoaUseCase.executar(pessoa);
        return ResponseEntity.ok(PessoaToPessoaModelResponseTranslator.translate(pessoaCadastrada));
    }
}

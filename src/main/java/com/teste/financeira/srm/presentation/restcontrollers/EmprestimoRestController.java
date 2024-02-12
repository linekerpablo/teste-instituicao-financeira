package com.teste.financeira.srm.presentation.restcontrollers;

import com.teste.financeira.srm.domain.entities.Emprestimo;
import com.teste.financeira.srm.domain.usecases.emprestimo.interfaces.RealizarEmprestimoUseCase;
import com.teste.financeira.srm.presentation.models.request.EmprestimoModelRequest;
import com.teste.financeira.srm.presentation.models.response.EmprestimoModelResponse;
import com.teste.financeira.srm.presentation.translators.request.EmprestimoRequestToEmprestimoTranslator;
import com.teste.financeira.srm.presentation.translators.response.EmprestimoToEmprestimoResponseTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/emprestimos")
public class EmprestimoRestController {

    @Autowired
    private RealizarEmprestimoUseCase realizarEmprestimoUseCase;

    @PostMapping
    public ResponseEntity<EmprestimoModelResponse> realizarEmprestimo(@RequestBody EmprestimoModelRequest modelRequest) {
        Emprestimo emprestimo = EmprestimoRequestToEmprestimoTranslator.translate(modelRequest);
        Emprestimo novoEmprestimo = realizarEmprestimoUseCase.executar(emprestimo, modelRequest.getIdentificadorPessoa());
        EmprestimoModelResponse response = EmprestimoToEmprestimoResponseTranslator.translate(novoEmprestimo);
        return ResponseEntity.ok(response);
    }
}
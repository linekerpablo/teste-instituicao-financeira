package com.teste.financeira.srm.presentation.models.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PessoaModelRequest {

    @NotEmpty(message = "O nome não pode ser vazio")
    private String nome;

    @NotEmpty(message = "O identificador não pode ser vazio")
    private String identificador;

    @NotNull(message = "A data de nascimento não pode ser nula")
    private Date dataNascimento;
}

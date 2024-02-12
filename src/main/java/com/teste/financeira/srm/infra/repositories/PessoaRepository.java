package com.teste.financeira.srm.infra.repositories;

import com.teste.financeira.srm.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByIdentificador(String identificador);
}
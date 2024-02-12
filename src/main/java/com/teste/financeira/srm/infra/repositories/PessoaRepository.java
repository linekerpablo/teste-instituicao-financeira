package com.teste.financeira.srm.infra.repositories;

import com.teste.financeira.srm.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByIdentificador(String identificador);
    Optional<Pessoa> findByIdentificador(String identificador);
}
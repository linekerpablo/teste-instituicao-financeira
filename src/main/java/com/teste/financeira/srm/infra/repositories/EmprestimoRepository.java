package com.teste.financeira.srm.infra.repositories;

import com.teste.financeira.srm.domain.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    @Query("SELECT SUM(e.valorEmprestimo) FROM Emprestimo e WHERE e.pessoa.id = :pessoaId")
    Double somaEmprestimosPorPessoa(Long pessoaId);
}

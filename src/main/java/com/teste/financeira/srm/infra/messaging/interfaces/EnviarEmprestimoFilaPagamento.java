package com.teste.financeira.srm.infra.messaging.interfaces;

public interface EnviarEmprestimoFilaPagamento {
    void executar(Long emprestimoId);
}

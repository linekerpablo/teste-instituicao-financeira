package com.teste.financeira.srm.infra.messaging;

import com.teste.financeira.srm.infra.messaging.interfaces.EnviarEmprestimoFilaPagamento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class EnviarEmprestimoFilaPagamentoImpl implements EnviarEmprestimoFilaPagamento {

    @Value("${fila.nome}")
    private String nomeFila;

    private final RabbitTemplate rabbitTemplate;

    public EnviarEmprestimoFilaPagamentoImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void executar(Long emprestimoid) {
        rabbitTemplate.convertAndSend(nomeFila, emprestimoid);
        System.out.println("Mensagem enviada para a fila " + nomeFila + ": " + emprestimoid);
    }
}
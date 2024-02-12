package com.teste.financeira.srm.domain.enums;

public enum TipoIdentificador {
    PESSOA_FISICA("PF"),
    PESSOA_JURIDICA("PJ"),
    ESTUDANTE_UNIVERSITARIO("EU"),
    APOSENTADO("AP");

    private final String codigo;

    TipoIdentificador(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}

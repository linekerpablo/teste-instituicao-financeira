package com.teste.financeira.srm.domain.helpers;

public class DocumentosHelper {
    public static boolean isCPFValido(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

        if (cpf.length() != 11 || todosDigitosIguais(cpf)) return false;

        String base = cpf.substring(0, 9);
        String digitos = cpf.substring(9);

        String dv1 = calcularDV(base);
        String dv2 = calcularDV(base + dv1);

        return digitos.equals(dv1 + dv2);
    }

    private static String calcularDV(String base) {
        int peso = base.length() + 1;
        int soma = 0;

        for (char c : base.toCharArray()) {
            soma += (c - '0') * peso--;
        }

        int resto = soma % 11;
        return (resto < 2) ? "0" : Integer.toString(11 - resto);
    }

    private static boolean todosDigitosIguais(String valor) {
        char primeiro = valor.charAt(0);
        for (char c : valor.toCharArray()) {
            if (c != primeiro) return false;
        }
        return true;
    }

    public static boolean isCNPJValido(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

        if (cnpj.length() != 14 || todosDigitosIguais(cnpj)) return false;

        String base = cnpj.substring(0, 12);
        String digitos = cnpj.substring(12);

        String dv1 = calcularDVCNPJ(base);
        String dv2 = calcularDVCNPJ(base + dv1);

        return digitos.equals(dv1 + dv2);
    }

    private static String calcularDVCNPJ(String base) {
        int[] pesos = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        int indicePeso = base.length();

        for (char c : base.toCharArray()) {
            soma += (c - '0') * pesos[pesos.length - 1 - indicePeso--];
        }

        int resto = soma % 11;
        return (resto < 2) ? "0" : Integer.toString(11 - resto);
    }
}

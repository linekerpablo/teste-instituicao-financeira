package com.teste.financeira.srm.domain.helpers;

import com.teste.financeira.srm.domain.exceptions.DomainException;

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
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches(cnpj.charAt(0) + "{14}")) return false;

        try {
            Long.parseLong(cnpj);
        } catch (NumberFormatException e) {
            return false;
        }

        int soma = 0;
        String cnpjCalc = cnpj.substring(0, 12);
        char[] charCnpj = cnpj.toCharArray();
        for (int i = 0; i < 4; i++) {
            if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9) {
                soma += (charCnpj[i] - 48) * (6 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (charCnpj[i + 4] - 48 >= 0 && charCnpj[i + 4] - 48 <= 9) {
                soma += (charCnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }
        int dig = 11 - (soma % 11);
        cnpjCalc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9) {
                soma += (charCnpj[i] - 48) * (7 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (charCnpj[i + 5] - 48 >= 0 && charCnpj[i + 5] - 48 <= 9) {
                soma += (charCnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);
        cnpjCalc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        return cnpj.equals(cnpjCalc);
    }

    public static boolean isEstudante(String identificador) {
        if (identificador.length() != 8) {
            throw new DomainException("Identificador de estudante universitário inválido.");
        }

        int primeiroDigito = Character.getNumericValue(identificador.charAt(0));
        int ultimoDigito = Character.getNumericValue(identificador.charAt(identificador.length() - 1));

        return primeiroDigito + ultimoDigito == 9;
    }

    public static boolean isAposentado(String identificador) {
        if (identificador.length() != 10) {
            throw new DomainException("Identificador de aposentado inválido.");
        }

        char ultimoDigito = identificador.charAt(identificador.length() - 1);

        for (int i = 0; i < identificador.length() - 1; i++) {
            if (identificador.charAt(i) == ultimoDigito) {
                return false;
            }
        }

        return true;
    }
}

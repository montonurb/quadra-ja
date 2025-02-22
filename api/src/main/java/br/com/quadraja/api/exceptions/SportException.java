package br.com.quadraja.api.exceptions;

public class SportException extends RuntimeException {
    public SportException(String mensagem) {
        super(mensagem);
    }
}

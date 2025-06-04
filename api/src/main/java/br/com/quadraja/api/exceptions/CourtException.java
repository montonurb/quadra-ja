package br.com.quadraja.api.exceptions;

public class CourtException extends RuntimeException {
    public CourtException(String mensagem) {
        super(mensagem);
    }
}

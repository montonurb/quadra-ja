package br.com.quadraja.api.exceptions;

public class GenericException extends RuntimeException {
    public GenericException(String mensagem) {
        super(mensagem);
    }
}

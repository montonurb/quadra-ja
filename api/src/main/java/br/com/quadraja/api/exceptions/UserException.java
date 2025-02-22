package br.com.quadraja.api.exceptions;

public class UserException extends RuntimeException {
    public UserException(String mensagem) {
        super(mensagem);
    }
}

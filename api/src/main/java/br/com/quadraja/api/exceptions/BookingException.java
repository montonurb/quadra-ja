package br.com.quadraja.api.exceptions;

public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
}

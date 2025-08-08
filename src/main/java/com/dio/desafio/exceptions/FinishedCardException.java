package com.dio.desafio.exceptions;

public class FinishedCardException extends RuntimeException {
    public FinishedCardException(final String message) {
        super(message);
    }
}

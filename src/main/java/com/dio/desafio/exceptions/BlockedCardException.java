package com.dio.desafio.exceptions;

public class BlockedCardException extends RuntimeException{

    public BlockedCardException(final String message) {
        super(message);
    }
}
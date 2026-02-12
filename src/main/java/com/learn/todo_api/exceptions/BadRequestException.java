package com.learn.todo_api.exceptions;

// Excepción para peticiones inválidas del cliente
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
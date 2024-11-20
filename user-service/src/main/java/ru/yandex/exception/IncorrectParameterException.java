package ru.yandex.exception;

public class IncorrectParameterException extends RuntimeException {
    public IncorrectParameterException(String message) {
        super(message);
    }
}

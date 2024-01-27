package ru.alfabank.aston.exceptions.registration;

public class PasswordMatchException extends RuntimeException{
    public PasswordMatchException(String message) {
        super(message);
    }

    public PasswordMatchException() {
        super();
    }
}

package ru.alfabank.aston.exceptions.registration;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }

    public UserExistException() {
        super();
    }
}

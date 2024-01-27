package ru.alfabank.aston.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alfabank.aston.exceptions.changePassword.OldNewPassSameException;
import ru.alfabank.aston.exceptions.logIn.PasswordNotCorrException;
import ru.alfabank.aston.exceptions.logIn.UserNotExistException;
import ru.alfabank.aston.exceptions.registration.UserExistException;
import ru.alfabank.aston.exceptions.registration.PasswordMatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({PasswordMatchException.class, UserExistException.class, UserNotExistException.class, PasswordNotCorrException.class, OldNewPassSameException.class})
    public ResponseEntity<String> ExceptionsHandler(RuntimeException ex) {
        if(ex instanceof PasswordMatchException) {
            return ResponseEntity.badRequest().body("Пароли не совпадают");
        }
        if(ex instanceof UserExistException) {
            return ResponseEntity.badRequest().body("пользователь с таким логином уже есть");
        }
        if(ex instanceof PasswordNotCorrException) {
            return ResponseEntity.badRequest().body("Пароль не подходит");
        }
        if(ex instanceof UserNotExistException) {
            return ResponseEntity.badRequest().body("Логин не верный");
        }
        if(ex instanceof OldNewPassSameException) {
            return ResponseEntity.badRequest().body("Старый и новый пароль сопадают");
        }
        return ResponseEntity.ok("success");
    }
}


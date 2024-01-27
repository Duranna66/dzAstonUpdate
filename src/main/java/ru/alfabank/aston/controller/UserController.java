package ru.alfabank.aston.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alfabank.aston.dto.UserDTO;
import ru.alfabank.aston.entites.UserApp;
import ru.alfabank.aston.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/registration")
    public ResponseEntity<String> registrateUser(@RequestBody UserDTO userDTO) { //регистрация пользаков
        log.info("request on reg");
        userService.register(userDTO);
        return ResponseEntity.ok(userDTO.getLogin() + " was successfully registered)");
    }
    @PatchMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UserDTO userDTO) { //обновка пароля приходит json'ом 3 поле по счету
        userService.updatePassword(userDTO); //обновляем
        return ResponseEntity.ok("success");
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<String> getAllUsers() { //просто показываю всех пользаков
        log.info("start to show userApps");
        List<UserApp> userApps = userService.showAllUsers();
        return ResponseEntity.ok(userApps.toString());
    }
    @GetMapping("/login")
    public ResponseEntity<String> logIn(@RequestParam String login, @RequestParam String password) { //чел логинится, логин и пароль приходит в параметрах
        log.info("user with login {} and password{} ", login, password);
        userService.logIn(login, password);
        return ResponseEntity.ok("Добро пожаловать " + login);
    }
}


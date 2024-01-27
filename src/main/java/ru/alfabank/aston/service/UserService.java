package ru.alfabank.aston.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.aston.dto.UserDTO;
import ru.alfabank.aston.entites.UserApp;
import ru.alfabank.aston.exceptions.changePassword.OldNewPassSameException;
import ru.alfabank.aston.exceptions.logIn.PasswordNotCorrException;
import ru.alfabank.aston.exceptions.logIn.UserNotExistException;
import ru.alfabank.aston.exceptions.registration.PasswordMatchException;
import ru.alfabank.aston.exceptions.registration.UserExistException;
import ru.alfabank.aston.mappers.UserMapper;
import ru.alfabank.aston.repo.UserRepository;

import java.util.List;
import java.util.Objects;


@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;



    public UserApp register(UserDTO userDTO) {
        if(!checkEqualsPasswords(userDTO)) {
            throw new PasswordMatchException();
        }
        if(checkExistUserOrNot(userDTO, showAllUsers()) != null) {
            throw new UserExistException();
        }
        UserApp userApp = userMapper.mapFromUserDTOtoUser(userDTO); //маплю
        userRepository.save(userApp);
        return userApp;
    }
    public UserApp updatePassword(UserDTO userDTO) {
        UserApp userApp = checkExistUserOrNot(userDTO, showAllUsers());//проверка на зареган или нет пользак
        if(userApp == null) {
            throw new UserNotExistException();
        }
        if(userApp.getPassword().equals(userDTO.getCheckPassword())) {
            throw new OldNewPassSameException();
        }
        userApp.setPassword(userDTO.getCheckPassword());
        userRepository.save(userApp);
        return userApp;
    }
    public List<UserApp> showAllUsers() {
        return userRepository.findAll();
    }
    public UserApp logIn(String login, String password) {
        UserDTO userDTO = UserDTO.builder().login(login)
                .password(password).build();
        UserApp userApp = checkExistUserOrNot(userDTO, showAllUsers()); //есtь или нет в бд
        if(userApp == null) {
            throw new UserNotExistException();
        }
        if(!Objects.equals(userApp.getPassword(), userDTO.getPassword())) {
            throw new PasswordNotCorrException();
        }
        return userApp;
    }
    public UserApp checkExistUserOrNot(UserDTO userDTO, List<UserApp> userApps) {
        for(UserApp u : userApps) { //ищу пользака если уже есть
            if(u.getLogin().equals(userDTO.getLogin())) {
                return u;
            }
        }
        return null;
    }
    public boolean checkEqualsPasswords(UserDTO userDTO) {
        return userDTO.getPassword().equals(userDTO.getCheckPassword()); //проверка на  совпадение паролей
    }

}

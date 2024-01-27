package ru.alfabank.aston.mappers;

import org.springframework.stereotype.Component;
import ru.alfabank.aston.dto.UserDTO;
import ru.alfabank.aston.entites.UserApp;

@Component
public class UserMapper {
    public UserApp mapFromUserDTOtoUser(UserDTO userDTO) { //обычный мапер
        UserApp userApp = UserApp.builder()
                .login(userDTO.getLogin())
                .password(userDTO.getPassword()).build();
        return userApp;
    }
}

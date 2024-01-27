package ru.alfabank.aston;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alfabank.aston.dto.UserDTO;
import ru.alfabank.aston.entites.UserApp;
import ru.alfabank.aston.mappers.UserMapper;
import ru.alfabank.aston.repo.UserRepository;
import ru.alfabank.aston.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@ExtendWith(SpringExtension.class)
class AstonApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private UserMapper userMapper;
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;
	private UserApp createUserApp(String login, String password) {
		return UserApp.builder()
				.login(login)
				.password(password).build();
	}

	private UserDTO createUserAppDTO(String login, String password, String checkPassword) {
		return UserDTO.builder()
				.login(login)
				.password(password)
				.checkPassword(checkPassword).build();
	}
	@Test
	public void registerCheck() {
		UserDTO userDTO = createUserAppDTO("cr7", "cr7", "cr7");
		UserApp userApp = createUserApp("cr7", "cr7");
		when(userService.register(userDTO)).thenReturn(userApp);
		when(userMapper.mapFromUserDTOtoUser(userDTO)).thenReturn(userApp);
		UserApp userAppRes =  userService.register(userDTO);
		Assertions.assertEquals(userAppRes.getLogin(),userDTO.getLogin());

	}
	@Test
	public void showAllTest() {
		List<UserApp> list = new ArrayList<>();
		list.add(createUserApp("qwe2390", "QweQwe2390"));
		when(userService.showAllUsers()).thenReturn(list);
		List<UserApp> res = userService.showAllUsers();
		System.out.println(res);
		Assertions.assertFalse(res.isEmpty());
	}


}

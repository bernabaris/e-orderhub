package com.github.bernabaris.userservice;

import com.github.bernabaris.userservice.entity.Role;
import com.github.bernabaris.userservice.model.User;
import com.github.bernabaris.userservice.repository.UserRepository;
import com.github.bernabaris.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private User testUser;


	@BeforeEach
	void setUp() {
		testUser = new User();
		testUser.setUsername("test");
		testUser.setEmail("test@test.com");
		testUser.setPassword(passwordEncoder.encode("test"));
		testUser.setRole(Role.USER);
	}

	@Test
	void shouldRegisterUser() {
		User user = userService.registerUser(testUser);
		assert user != null;
		assert user.getUsername().equals(testUser.getUsername());
		assert user.getEmail().equals(testUser.getEmail());
		assert user.getPassword().equals(testUser.getPassword());
		assert user.getRole().equals(Role.USER);
	}

	@Test
	void shouldNotRegisterUserIfEmailAlreadyExists() {
		User user = userService.registerUser(testUser);
		User duplicatedUser = new User();
		duplicatedUser.setUsername("test2");
		duplicatedUser.setEmail("test@test.com");
		duplicatedUser.setPassword(passwordEncoder.encode("test"));
		duplicatedUser.setRole(Role.USER);

		assertThrows(IllegalArgumentException.class, () -> userService.registerUser(duplicatedUser));
	}

	@Test
	void shouldNotRegisterUsernameIsEmpty() {
		testUser.setUsername("");
		assertThrows(IllegalArgumentException.class, () -> userService.registerUser(testUser));
	}

	@Test
	void shouldRetrieveUserByUsername() {
		User user = userService.registerUser(testUser);
		Optional<User> registeredUser = userService.getUserByUsername(user.getUsername());
		assert registeredUser.isPresent();
		assertThat(registeredUser.get()).isEqualTo(user);
	}

	@Test
	void shouldRetrieveUserByEmail(){
		User user = userService.registerUser(testUser);
		Optional<User> registeredUser = userService.getUserByEmail(user.getEmail());
		assert registeredUser.isPresent();
		assertThat(registeredUser.get()).isEqualTo(user);
	}

	@Test
	void shouldReturnFalseIfUsernameNotExists(){
		assertThat(userService.existsByUsername("nonexistentuser")).isFalse();
	}

	@Test
	void shouldReturnFalseIfEmailNotExists(){
		assertThat(userService.existsByEmail("nonexistent@example.com")).isFalse();
	}





}

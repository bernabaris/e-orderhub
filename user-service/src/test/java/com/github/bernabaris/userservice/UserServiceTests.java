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
		testUser = User.builder()
				.username("testuser")
				.email("test@example.com")
				.password("securePassword")
				.role(Role.USER)
				.build();
	}

	@Test
	void shouldRegisterUserSuccessfully() {
		User registeredUser = userService.registerUser(testUser);
		assertThat(registeredUser).isNotNull();
		assertThat(registeredUser.getId()).isNotNull();
		assertThat(userRepository.existsByUsername("testuser")).isTrue();
	}

	@Test
	void shouldNotRegisterUserWithExistingEmail() {
		userService.registerUser(testUser);

		User duplicateUser = User.builder()
				.username("newuser")
				.email("test@example.com") // same email
				.password("securePassword2")
				.role(Role.USER)
				.build();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.registerUser(duplicateUser));
		assertThat(exception.getMessage()).isEqualTo("Username or email address already in use");
	}

	@Test
	void shouldRetrieveUserByUsername() {
		User registeredUser = userService.registerUser(testUser);
		assertThat(userService.getUserByUsername("testuser")).isPresent();
	}

	@Test
	void shouldRetrieveUserByEmail() {
		User registeredUser = userService.registerUser(testUser);
		assertThat(userService.getUserByEmail("test@example.com")).isPresent();
	}

	@Test
	void shouldReturnFalseIfUsernameNotExists() {
		assertThat(userService.existsByUsername("nonexistentuser")).isFalse();
	}

	@Test
	void shouldReturnFalseIfEmailNotExists() {
		assertThat(userService.existsByEmail("nonexistent@example.com")).isFalse();
	}



}

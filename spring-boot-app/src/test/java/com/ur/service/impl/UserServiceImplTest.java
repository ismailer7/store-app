package com.ur.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.ur.domain.User;
import com.ur.pojo.UserDTO;
import com.ur.repository.UserRepository;
import com.ur.service.transformer.UserTransformer;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository mockUserRepository;

	@Spy
	private UserTransformer userTransformer;

	@InjectMocks
	private UserServiceImpl userService;
	
	private User user = null;
	
	@BeforeEach
	public void setUp() {
		user = new User(1L, "username", "password", "email@email", true, "ROLE_USER", 'M', null);
		Optional<User> optionalUser = Optional.of(user);
		when(mockUserRepository.findById(1L)).thenReturn(optionalUser);
		when(mockUserRepository.save(user)).thenReturn(user);
//		when(userTransformer.toDTO(user)).thenReturn(new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
//				user.getPassword(), user.isActive(), user.getGender(), null));
		
		when(userTransformer.toDTO(user)).thenCallRealMethod();
		when(userTransformer.toEntity(userTransformer.toDTO(user))).thenCallRealMethod();
		
	}
	
	@Test
	public void getTest() {
		UserDTO userDto = userService.get(1L);
		assertNotNull(userDto);
		assertEquals(1L, userDto.getId().longValue());
		assertEquals("username", userDto.getUsername());
		assertEquals("password", userDto.getPassword());
		assertEquals("email@email", userDto.getEmail());
		assertEquals("ROLE_USER", userDto.getRoles());
		assertTrue(userDto.isActive());
	}
	
	@Test
	public void registerTest() {
		UserDTO userDto = userService.register(userTransformer.toDTO(user));
		assertNotNull(userDto);
		assertEquals(1L, userDto.getId().longValue());
		assertEquals("username", userDto.getUsername());
		assertEquals("password", userDto.getPassword());
		assertEquals("email@email", userDto.getEmail());
		assertEquals("ROLE_USER", userDto.getRoles());
		assertTrue(userDto.isActive());
	}
	
}

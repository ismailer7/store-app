package com.ur.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.ur.domain.Store;
import com.ur.domain.User;
import com.ur.pojo.StoreDTO;
import com.ur.pojo.UserDTO;
import com.ur.repository.UserRepository;
import com.ur.service.transformer.StoreTransformer;
import com.ur.service.transformer.UserTransformer;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository mockUserRepository;

	@Spy
	private UserTransformer userTransformer;

	@Spy
	private StoreTransformer storeTransformer;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	private User user = null;
	
	@BeforeEach
	public void setUp() {
		user = new User(1L, "username", "password", "email@email", true, "ROLE_USER", 'M', null);
		Optional<User> optionalUser = Optional.of(user);
		when(mockUserRepository.findById(1L)).thenReturn(optionalUser);
		when(mockUserRepository.save(user)).thenReturn(user);
	}
	
	public void initializeFilledStoreUser() {
		List<Store> storesTest = new ArrayList<Store>();
		storesTest.add(new Store(1L, "storeTest", "larache", "type1,type2,type3", "", true, 7));
		user = new User(2L, "username", "password", "email@email", true, "ROLE_USER", 'M',storesTest);
		when(mockUserRepository.findById(2L)).thenReturn(Optional.of(user));
		when(mockUserRepository.save(user)).thenReturn(user);
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
	
	@Test
	public void findUserByCredentialsTest() {
		when(mockUserRepository.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(user);
		UserDTO userDto = userService.findUserByCredentials("test", "test");
		assertEquals(1L, userDto.getId().longValue());
		assertEquals("username", userDto.getUsername());
		assertEquals("password", userDto.getPassword());
		assertEquals("email@email", userDto.getEmail());
		assertEquals("ROLE_USER", userDto.getRoles());
		assertTrue(userDto.isActive());
	}
	
	@Test
	public void findUserByCredentialsNullTest() {
		when(mockUserRepository.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(null);
		UserDTO userDto = userService.findUserByCredentials("test", "test");
		assertNull(userDto);
	}
	
	@Test
	public void getAllPrefferedStoresTestEmpty() {
		List<StoreDTO> stores = userService.getAllPrefferedStores(1L);
		assertNotNull(stores);
		assertTrue(stores.isEmpty());
	}
	
	@Test
	public void getAllPrefferedStoresTestNotEmpty() {
		initializeFilledStoreUser();
		List<StoreDTO> stores = userService.getAllPrefferedStores(2L);
		assertNotNull(stores);
		assertFalse(stores.isEmpty());
		assertEquals(1, stores.size());
	}
	
	@Test
	public void addToPrefferedListWithEmptyStoresTest() {
		// it will use the user define in before.
		List<String> types = new ArrayList<>();
		types.add("type1");
		UserDTO userDto = userService.addToPrefferedList(1L, new StoreDTO("aszae", "storetest", types, "", true, 5, "larache"));
		assertNotNull(userDto);
		assertEquals(1, userDto.getStoreDTOList().size());
	}
	
	@Test
	public void addToPrefferedListWithExistingStoresTest() {
		initializeFilledStoreUser();
		List<String> types = new ArrayList<>();
		types.add("type1");
		UserDTO userDto = userService.addToPrefferedList(2L, new StoreDTO("isqtore", "storetesttest", types, "", true, 5, "larache"));
		assertNotNull(userDto);
		assertEquals(2, userDto.getStoreDTOList().size());
	}
}

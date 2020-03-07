package com.ur.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ur.domain.Store;
import com.ur.domain.User;
import com.ur.pojo.StoreDTO;
import com.ur.pojo.UserDTO;
import com.ur.repository.StoreRepository;
import com.ur.repository.UserRepository;
import com.ur.service.IUserService;
import com.ur.service.transformer.Transformer;
import com.ur.service.transformer.UserTransformer;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private UserTransformer userTransformer;
	
	@Autowired
	private Transformer<Store, StoreDTO> storeTransformer;
	
	/**
	 * register a new user in our database
	 * @param UserDTO The user in which we want to register
	 * @return UserDTO
	 */
	@Override
	public UserDTO register(UserDTO userDTO) {
		User user1 = userRepository.save(userTransformer.toEntity(userDTO));
		return userTransformer.toDTO(user1);
	}

	
	/**
	 * Get the user information
	 * @param id the user Id
	 * @return UserDTO
	 */
	@Override
	public UserDTO get(Long id) {
		Optional<User> user = userRepository.findById(id);
		return userTransformer.toDTO(user.get());
	}
	
	/**
	 * remove the user from DB
	 * @param userDTO The user that we want to remove
	 * @return void
	 */
	@Override
	public void remove(UserDTO userDTO) {
		User user = userTransformer.toEntity(userDTO);
		userRepository.delete(user);
	}

	/**
	 * update the user information
	 * @param id The user id that we want to update
	 * @param userDto The new information that will replace the old one  
	 * @return void
	 */
	@Override
	public void update(Long id, UserDTO userDto) {
		User old = userRepository.getOne(id);
		old.setEmail(userDto.getEmail());
		old.setUsername(userDto.getUsername());
		userRepository.flush();
	}

	/**
	 * Find the user by his user name and password
	 * @param username The user name
	 * @param password The user password
	 * @return UserDTO The user information 
	 */
	@Override
	public UserDTO findUserByCredentials(String username, String password) {
		User user = userRepository.findUserByUsernameAndPassword(username, password);
		return user == null ? null : userTransformer.toDTO(user);
	}

	/**
	 * Get all stores that a specific user likes
	 * @param id the user Id
	 * @return the List of Stores
	 */
	@Override
	public List<StoreDTO> getAllPrefferedStores(Long id) {
//		User user = userRepository.findById(id).get();
		List<Store> preferredStores = userRepository.getAllPreferredStores(id);
		List<StoreDTO> storesDto = new ArrayList<>();
		if(preferredStores != null && !preferredStores.isEmpty()) {
			storesDto = storeTransformer.toDTOList(preferredStores);
		}
		return storesDto;
	}
	
	/**
	 * Add a specific store to preferred list of specific user
	 * @param userId The user id
	 * @param storeDto the store to be added to preferred list
	 * @return the user DTO
	 * @throws ParseException 
	 */
	@Override
	public StoreDTO addToPrefferedList(Long userId, Long storeId) {
		Store store = userRepository.getStoreById(userId, storeId);
		
//		Store store = storeTransformer.toEntity(storeDto);
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		store.setLastAction(
//				simpleDateFormat
//				    .parse(simpleDateFormat.format(new Date())));
		store.setLastAction(new Date());
		store.setStatus(1);
//		if(user.getStores() != null) {
//			user.getStores().add(store);
//		} else {
//			List<Store> stores = new ArrayList<Store>();
//			stores.add(store);
//			user.setStores(stores);
//		}
		storeRepository.save(store);
		return storeTransformer.toDTO(store);
	}
	
	/**
	 * Remove a specific Store from a preferred list for some user
	 * @param userId the user ID
	 * @param store The store we want to remove
	 * @return the status of the remove action
	 */
	@Override
	public StoreDTO removeFromPreferredList(Long userId, Long storeId) {
		Store store = userRepository.getStoreById(userId, storeId);
		store.setLastAction(new Date());
		store.setStatus(0);
		storeRepository.save(store);
		return storeTransformer.toDTO(store);
	}


	@Override
	public List<StoreDTO> getAllAvailableStores(Long userId) {
		User user = userRepository.findById(userId).get();
		List<Store> stores = user.getStores();
		return storeTransformer.toDTOList(stores);
	}


	@Override
	public void addToAvailableStores(Long userId, StoreDTO storeDto) {
		User user = userRepository.findById(userId).get();
		Store store = storeTransformer.toEntity(storeDto);
		if (user.getStores() != null) {
			user.getStores().add(store);
		} else {
			List<Store> stores = new ArrayList<Store>();
			stores.add(store);
			user.setStores(stores);
		}
		userRepository.save(user);
	}


	@Override
	public StoreDTO removeFromNearby(Long userId, Long storeId) {
		Store store = userRepository.getStoreById(userId, storeId);
		store.setLastAction(new Date());
		store.setStatus(-1);
		storeRepository.save(store);
		return storeTransformer.toDTO(store);
	}
	
}

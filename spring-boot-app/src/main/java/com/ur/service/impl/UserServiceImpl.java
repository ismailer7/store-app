package com.ur.service.impl;

import java.util.ArrayList;
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

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private Transformer<User, UserDTO> userTransformer;
	
	@Autowired
	private Transformer<Store, StoreDTO> storeTransformer;
	
	@Override
	public UserDTO register(UserDTO userDTO) {
		User user = userRepository.save(userTransformer.toEntity(userDTO));
		return userTransformer.toDTO(user);
	}

	@Override
	public UserDTO get(Long id) {
		Optional<User> user = userRepository.findById(id);
		return userTransformer.toDTO(user.get());
	}

	@Override
	public void remove(UserDTO userDTO) {
		User user = userTransformer.toEntity(userDTO);
		userRepository.delete(user);
	}

	@Override
	public void update(Long id, UserDTO t) {
		User old = userRepository.getOne(id);
		old.setEmail(t.getEmail());
		old.setUsername(t.getUsername());
		userRepository.flush();
	}

	@Override
	public UserDTO findUserByCredentials(String username, String password) {
		User user = userRepository.findUserByUsernameAndPassword(username, password);
		return user == null ? null : userTransformer.toDTO(user);
	}

	@Override
	public List<StoreDTO> getAllPrefferedStores(Long id) {
		User user = userRepository.findById(id).get();
		List<StoreDTO> storesDto = new ArrayList<>();
		if(user.getStores() != null && !user.getStores().isEmpty()) {
			storesDto = storeTransformer.toDTOList(user.getStores());
		}
		return storesDto;
	}

	@Override
	public void addToPrefferedList(Long userId, StoreDTO storeDto) {
		User user = userRepository.findById(userId).get();
		Store store = storeTransformer.toEntity(storeDto);
		user.getStores().add(store);
		userRepository.save(user);
	}
	
}

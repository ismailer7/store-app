package com.ur.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ur.domain.User;
import com.ur.pojo.UserDTO;
import com.ur.repository.UserRepository;
import com.ur.service.IUserService;
import com.ur.service.transformer.Transformer;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Transformer<User, UserDTO> userTransformer;
	
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
		return userTransformer.toDTO(user);
	}
	
	
	
}

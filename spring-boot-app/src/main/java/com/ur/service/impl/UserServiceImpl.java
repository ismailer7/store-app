package com.ur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ur.domain.User;
import com.ur.repository.UserRepository;
import com.ur.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(User user) {
		return userRepository.save(user);
	}

	@Override
	public User get(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public void remove(User user) {
		userRepository.delete(user);
	}

	@Override
	public void update(Long id, User t) {
		User old = userRepository.getOne(id);
		old.setEmail(t.getEmail());
		old.setPassword(t.getPassword());
		old.setUsername(t.getUsername());
		userRepository.flush();
	}

	@Override
	public User findUserByCredentials(String username, String password) {
		return userRepository.findUserByUsernameAndPassword(username, password);
	}
	
	
	
}

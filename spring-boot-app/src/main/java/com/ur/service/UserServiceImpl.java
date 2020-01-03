package com.ur.service;

import org.springframework.stereotype.Service;

import com.ur.domain.User;

@Service
public class UserServiceImpl implements IUserService<User, Long> {

	@Override
	public boolean register(User user) {
		return false;
	}

	@Override
	public User getUser(Long id) {
		return null;
	}

	@Override
	public boolean removeUser(User user) {
		return false;
	}

	@Override
	public boolean updateUser(Long id, User newUser) {
		return false;
	}

	
	
}

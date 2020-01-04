package com.ur.service;

import com.ur.domain.User;

public interface IUserService extends CRUDService<User, Long> {
	
	User findUserByCredentials(String username, String password);
	
}

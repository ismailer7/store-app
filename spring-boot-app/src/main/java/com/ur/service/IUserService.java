package com.ur.service;

import com.ur.pojo.UserDTO;

public interface IUserService extends CRUDService<UserDTO, Long> {
	
	UserDTO findUserByCredentials(String username, String password);
	
}

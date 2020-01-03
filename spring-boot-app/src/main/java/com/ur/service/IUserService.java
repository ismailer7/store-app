package com.ur.service;

import com.ur.domain.User;

public interface IUserService<T, D> {
	
	boolean register(T t);
	
	User getUser(D d);
	
	boolean removeUser(T t);
	
	boolean updateUser(D d, T t);
	
}

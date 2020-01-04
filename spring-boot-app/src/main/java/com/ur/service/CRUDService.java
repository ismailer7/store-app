package com.ur.service;

import com.ur.domain.User;

public interface CRUDService<T, D> {
	
	T register(T t);
	
	User get(D d);
	
	void remove(T t);
	
	void update(D d, T t);
	
}

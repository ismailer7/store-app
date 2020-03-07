package com.ur.service;

public interface CRUDService<T, D> {
	
	T register(T t);
	
	T get(D d);
	
	void remove(T t);
	
	void update(D d, T t);
	
}

package com.ur.service;

import java.util.List;

import com.ur.pojo.StoreDTO;
import com.ur.pojo.UserDTO;

public interface IUserService extends CRUDService<UserDTO, Long> {
	
	UserDTO findUserByCredentials(String username, String password);
	
	List<StoreDTO> getAllPrefferedStores(Long id);
	
	void addToPrefferedList(Long userId, StoreDTO store);
	
	void removeFromPreferredList(Long userId, StoreDTO store);
	
}

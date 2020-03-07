package com.ur.service;

import java.util.List;

import com.ur.pojo.StoreDTO;
import com.ur.pojo.UserDTO;

public interface IUserService extends CRUDService<UserDTO, Long> {
	
	UserDTO findUserByCredentials(String username, String password);
	
	List<StoreDTO> getAllAvailableStores(Long userID);
	
	void addToAvailableStores(Long userID, StoreDTO store);
	
	List<StoreDTO> getAllPrefferedStores(Long id);
	
	StoreDTO addToPrefferedList(Long userId, Long storeId);
	
	StoreDTO removeFromPreferredList(Long userId, Long storeId);
	
	StoreDTO removeFromNearby(Long userId, Long storeId);
}

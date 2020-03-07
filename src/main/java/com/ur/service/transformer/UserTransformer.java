package com.ur.service.transformer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ur.domain.Store;
import com.ur.domain.User;
import com.ur.pojo.StoreDTO;
import com.ur.pojo.UserDTO;

@Component
public class UserTransformer extends Transformer<User, UserDTO> {
	
	@Autowired
	Transformer<Store, StoreDTO> storeTransformer;
	
	/**
	 * Convert User Entity to user DTO
	 * @param e a user entity
	 * @return UserDTO
	 */
	@Override
	public UserDTO toDTO(User e) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(e.getId());
		userDTO.setUsername(e.getUsername());
		userDTO.setEmail(e.getEmail());
		userDTO.setActive(e.isActive());
		userDTO.setGender(e.getGender());
		userDTO.setPassword(e.getPassword());
		userDTO.setRoles(e.getRoles());
		if(e.getStores() != null && !e.getStores().isEmpty()) {
			List<StoreDTO> dtoStores = storeTransformer.toDTOList(e.getStores());
			userDTO.setStoreDTOList(dtoStores);
		}
		return userDTO;
	}
	
	/**
	 * Convert User DTO to user Entity
	 */
	public UserDTO toDTO(User e, Transformer<Store, StoreDTO> storeTransformer) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(e.getId());
		userDTO.setUsername(e.getUsername());
		userDTO.setEmail(e.getEmail());
		userDTO.setActive(e.isActive());
		userDTO.setGender(e.getGender());
		userDTO.setPassword(e.getPassword());
		userDTO.setRoles(e.getRoles());
		if(e.getStores() != null && !e.getStores().isEmpty()) {
			List<StoreDTO> dtoStores = storeTransformer.toDTOList(e.getStores());
			userDTO.setStoreDTOList(dtoStores);
		}
		return userDTO;
	}
	
	

	@Override
	public User toEntity(UserDTO d) {
		User user = new User();
		user.setId(d.getId());
		user.setUsername(d.getUsername());
		user.setEmail(d.getEmail());
		user.setPassword(d.getPassword());
		user.setActive(d.isActive());
		user.setGender(d.getGender());
		user.setRoles(d.getRoles());
//		user.setStores(storeTransformer.toEntityList(d.getStoreDTOList()));
		return user;
	}



	

}

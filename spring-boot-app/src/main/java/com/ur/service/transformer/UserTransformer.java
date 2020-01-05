package com.ur.service.transformer;

import org.springframework.stereotype.Component;

import com.ur.domain.Store;
import com.ur.domain.User;
import com.ur.pojo.StoreDTO;
import com.ur.pojo.UserDTO;

@Component
public class UserTransformer extends Transformer<User, UserDTO> {
	
	Transformer<Store, StoreDTO> storeTransformer;
	
	@Override
	public UserDTO toDTO(User e) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(e.getId());
		userDTO.setUsername(e.getUsername());
		userDTO.setEmail(e.getEmail());
		userDTO.setActive(e.isActive());
		userDTO.setStoreDTOList(storeTransformer.toDTOList(e.getStores()));
		return userDTO;
	}

	@Override
	public User toEntity(UserDTO d) {
		User user = new User();
		user.setId(d.getId());
		user.setUsername(d.getUsername());
		user.setEmail(d.getEmail());
		user.setActive(d.isActive());
		user.setStores(storeTransformer.toEntityList(d.getStoreDTOList()));
		return user;
	}

}

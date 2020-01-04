package com.ur.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	
	private Long id;
	
	private String username;
	
	private String email;
	
	private boolean active;
	
	private List<StoreDTO> storeDTOList;
}

package com.ur.pojo;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * response for multiples stores
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {
	
	private String next_page_token;
	
	private List<StoreDTO> results;
	
	
	public void addStore(StoreDTO store) {
		this.getResults().add(store);
	}
}

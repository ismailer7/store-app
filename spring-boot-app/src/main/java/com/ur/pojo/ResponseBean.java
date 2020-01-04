package com.ur.pojo;

import java.util.List;

import lombok.Data;

/**
 * 
 * response for multiples stores
 *
 */

@Data
public class ResponseBean {
	
	private String next_page_token;
	
	private List<StoreBean> results;
	
	
	public void addStore(StoreBean store) {
		this.getResults().add(store);
	}
}

package com.ur.pojo;

import java.util.List;

import lombok.Data;

@Data
public class ResponseBean {
	
	private String next_page_token;
	
	private String previous_page_token;
	
	private List<StoreBean> results;
	
}

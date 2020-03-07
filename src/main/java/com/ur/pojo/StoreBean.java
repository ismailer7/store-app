package com.ur.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreBean {
	
	private String id;
	
	private String icon;
	
	private String name;
	
	private List<String> types;
	
	private String vicinity;
	
	private String place_id;
}

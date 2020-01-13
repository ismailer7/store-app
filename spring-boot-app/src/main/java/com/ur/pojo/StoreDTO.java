package com.ur.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDTO {
	
	private String id;
	
	private String name;
	
	private List<String> types;
	
	private String icon;
	
	private boolean open;
	
	private int rate;
	
	private String vicinity;
	
}

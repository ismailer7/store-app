package com.ur.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDTO {

	private Long id;
	
	private String name;
	
	private String types;
	
	private String icon;
	
	private boolean open;
	
	private int rate;
	
	private String vicinity;
}

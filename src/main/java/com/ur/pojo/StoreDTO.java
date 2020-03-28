package com.ur.pojo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDTO {

	private Long storeId;
	
	private String place_id;
	
	private String name;
	
	private List<String> types;
	
	private String icon;
	
	private boolean open;
	
	private int rating;
	
	private String vicinity;
	
	private Date lastAction;
	
}

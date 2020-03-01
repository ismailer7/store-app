package com.ur.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class RemovedStore {
	
	private String storeID;
	
	private Long userID;
	
	private String removedAt;
	
}

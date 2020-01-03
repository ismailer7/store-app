package com.ur.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "store_id", nullable = false, unique = true)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false)
	private String vicinity;
	
	@Column
	private String types;
	
	@Column
	private String icon;
	
	@Column
	private boolean isOpen;
	
	@ColumnDefault("0")
	private int rating;
	
	@Transient
	public List<String> getTypes() {
		return Arrays.asList(this.types.split(","));
	}

}
package com.ur.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id", nullable = false, unique = true)
	private Long id;

	@Column
	private String placeId;
	
	@Column(nullable = false, length = 200)
	private String name;

	@Column(nullable = false)
	private String vicinity;
	
	@Column
	private String types;
	
	@Column
	private String icon;
	
	@Column
	private boolean open;
	
	@ColumnDefault("0")
	private int rating;
	
	@Column(nullable = false)
	private int status;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAction;
	
	@Transient
	public List<String> getTypesList() {
		return Arrays.asList(this.types.split(","));
	}
	
}
package com.ur.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false, unique = true)
	private Long id;

	@Column(nullable = false, length = 30)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Column
	private boolean active;
	
	@Column
	private String roles;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_store", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "store_id") })
	private List<Store> stores;
	
	@Transient
	public List<String> getRolesList() {
		return Arrays.asList(getRoles().split(","));
	}
	
}

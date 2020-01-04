package com.ur.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo {
	
	String username;
	String password;
	
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + "]";
	}
	
}

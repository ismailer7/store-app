package com.ur.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ur.pojo.ResponseBean;
import com.ur.pojo.SingleResponseBean;
import com.ur.pojo.UserDTO;
import com.ur.service.IPlacesService;
import com.ur.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HomeController {
	
	@Autowired
	private IPlacesService placeService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/hi")
	public String sayHi() {
		return "Hello over there!";
	}
	
	@GetMapping("/places")
	public ResponseBean fetchData() {	
		return placeService.fetchPlaces();
	}
	
	@GetMapping("/place")
	public SingleResponseBean getStoreBeanByPlace(@RequestParam String placeID) {
		return placeService.fetchOnePlace(placeID);
	}
	
	@GetMapping("/something")
	public String unauthorizedPoint() {
		return "this need to be authorized";
	}
	
	@RequestMapping(value = "/retrieve", method = RequestMethod.POST)
	public UserDTO login(@RequestBody String username, @RequestBody String password) {
		UserDTO user = userService.findUserByCredentials(username, password);
		return user;
	}
	
	
}

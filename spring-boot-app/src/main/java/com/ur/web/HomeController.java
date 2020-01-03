package com.ur.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ur.pojo.ResponseBean;
import com.ur.pojo.StoreBean;
import com.ur.service.IPlacesService;

@RestController
public class HomeController {
	
	@Autowired
	private IPlacesService<StoreBean, Long> placeService;
	
	@GetMapping("/hi")
	public String sayHi() {
		return "Hello over there!";
	}
	
	@GetMapping("/getPlaces")
	public ResponseBean fetchData() {	
		// add previous track of page logic in service.
		return placeService.fetchPlaces();
	}
	
}

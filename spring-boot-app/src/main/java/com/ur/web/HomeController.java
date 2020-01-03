package com.ur.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ur.pojo.ResponseBean;
import com.ur.pojo.SingleResponseBean;
import com.ur.service.IPlacesService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HomeController {
	
	@Autowired
	private IPlacesService placeService;
	
	@GetMapping("/hi")
	public String sayHi() {
		return "Hello over there!";
	}
	
	
	@GetMapping("/places")
	public ResponseBean fetchData() {	
		// add previous track of page logic in service.
		return placeService.fetchPlaces();
	}
	
	@GetMapping("/place")
	public SingleResponseBean getStoreBeanByPlace(@RequestParam String placeID) {
		return placeService.fetchOnePlace(placeID);
	}
	
}

package com.ur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ur.pojo.ResponseBean;
import com.ur.pojo.SingleResponseBean;
import com.ur.service.IPlacesService;

@Service
public class PlacesServiceImpl implements IPlacesService {

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;

	
	@Value("${googleapis.places.key}")
	private String apiKey;

	
	/**
	 * this method fetch places from the google api
	 * @return an object that present the data gathered from the api
	 */
	@Override
	public ResponseBean fetchPlaces() {
		
		ResponseEntity<ResponseBean> response = null;
		ResponseBean responseBean = null;
		
		response = restTemplate.getForEntity(
				"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=35.1952327,-6.152913&rankby=distance&type=store&key="+apiKey,
				ResponseBean.class);
		responseBean = response.getBody();
		return responseBean;
	}
	
	/**
	 * fetch only one place based on his id
	 * @param placeID the ID of the place
	 * @return the object that represent the place
	 */
	@Override
	public SingleResponseBean fetchOnePlace(String placeID) {
		ResponseEntity<SingleResponseBean> response = restTemplate.getForEntity("https://maps.googleapis.com/maps/api/place/details/json?place_id="+placeID+"&key=AIzaSyD73W9tikguXooD_kM_VhGmh0TDMSuagHY", SingleResponseBean.class);
		return response.getBody();
	}
	
}

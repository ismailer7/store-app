package com.ur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ur.pojo.ResponseBean;
import com.ur.pojo.StoreBean;
import com.ur.repository.PlaceRepository;

@Service
public class PlacesServiceImpl implements IPlacesService<StoreBean, Long> {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PlaceRepository placeRepository;

	@Override
	public ResponseBean fetchPlaces() {
		ResponseEntity<ResponseBean> response = restTemplate.getForEntity(
				"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=35.1952327,-6.152913&rankby=distance&type=store&key=AIzaSyD73W9tikguXooD_kM_VhGmh0TDMSuagHY",
				ResponseBean.class);
		ResponseBean responseBean = response.getBody();
		responseBean.setPrevious_page_token("previous");
//		List<StoreBean> stores = responseBean.getResults();
		return responseBean;
	}

	@Override
	public boolean addPlaceToPrefferedList(StoreBean storeBean, Long id) {
		// add this store to store table and link it with user with this id.
		return false;
	}

}

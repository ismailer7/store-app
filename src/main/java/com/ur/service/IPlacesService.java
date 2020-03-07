package com.ur.service;

import java.text.ParseException;

import com.ur.pojo.ResponseBean;
import com.ur.pojo.SingleResponseBean;

public interface IPlacesService {
	
	ResponseBean fetchPlaces();
	
//	boolean addPlaceToPrefferedList(StoreBean store);
	
	SingleResponseBean fetchOnePlace(String placeID);
	
	ResponseBean fetchUnremovedPlaces(Long id) throws ParseException;
}

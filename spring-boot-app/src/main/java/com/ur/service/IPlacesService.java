package com.ur.service;

import com.ur.pojo.ResponseBean;
import com.ur.pojo.SingleResponseBean;

public interface IPlacesService {
	
	ResponseBean fetchPlaces();
	
//	boolean addPlaceToPrefferedList(T t, D d);
	
	SingleResponseBean fetchOnePlace(String placeID);
}

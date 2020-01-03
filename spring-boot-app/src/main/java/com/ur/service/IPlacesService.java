package com.ur.service;

import com.ur.pojo.ResponseBean;

public interface IPlacesService<T, D> {
	
	ResponseBean fetchPlaces();
	
	boolean addPlaceToPrefferedList(T t, D d);
}

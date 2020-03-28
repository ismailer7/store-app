package com.ur.service.transformer;

import org.springframework.stereotype.Component;

import com.ur.domain.Store;
import com.ur.pojo.StoreDTO;

@Component
public class StoreTransformer extends Transformer<Store, StoreDTO>{

	@Override
	public StoreDTO toDTO(Store e) {
		StoreDTO dtoStore = new StoreDTO();
		dtoStore.setStoreId(e.getId());
		dtoStore.setPlace_id(e.getPlaceId());
		dtoStore.setIcon(e.getIcon());
		dtoStore.setName(e.getName());
		dtoStore.setTypes(e.getTypesList());
		dtoStore.setOpen(e.isOpen());
		dtoStore.setRating(e.getRating());
		dtoStore.setVicinity(e.getVicinity());
		dtoStore.setLastAction(e.getLastAction());
		return dtoStore;
	}

	@Override
	public Store toEntity(StoreDTO d) {
		Store store = new Store();
		store.setName(d.getName());
		store.setPlaceId(d.getPlace_id());
		store.setIcon(d.getIcon());
		store.setTypes(String.join(",", d.getTypes()));
		store.setOpen(d.isOpen());
		store.setRating(d.getRating());
		store.setVicinity(d.getVicinity());
		store.setLastAction(d.getLastAction());
		return store;
	}

	
}
